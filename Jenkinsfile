pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'   // MUST match exactly what you configured in Jenkins
    }

    environment {
    DOCKER_IMAGE = "vinodnitharwal9/firstd:${BUILD_NUMBER}"
}

    stages {

        //  Jenkins will already checkout automatically (Pipeline from SCM)
        // But if you want explicit GitHub checkout, keep this:

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/vinodnitharwal-e/firstd.git'
            }
        }

        stage('Build Application') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    docker push %DOCKER_IMAGE%
                    '''
                }
            }
        }
    }
}
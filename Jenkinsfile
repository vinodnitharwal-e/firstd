pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    environment {
        DOCKER_IMAGE = "vinodnitharwal9/firstd:${BUILD_NUMBER}"
    }

    stages {

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

        stage('Verify Docker Image') {
            steps {
                bat 'docker images'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                    docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                    docker push %DOCKER_IMAGE%
                    """
                }
            }
        }

        stage('Deploy') {
      steps {
        
        bat "docker stop springboot-app || exit 0"
        bat "docker rm springboot-app || exit 0"
        bat "docker run -d -p 9090:8080 --name springboot-app %DOCKER_IMAGE%:latest"
      }
    }
   }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs above.'
        }
    }
}
# Use official Java image
FROM eclipse-temurin:17-jdk-jammy

# Add jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port
EXPOSE 8082

# Run application
ENTRYPOINT ["java","-jar","/app.jar"]
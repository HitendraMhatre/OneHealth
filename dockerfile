# FROM openjdk:8-jdk-alpine
FROM openjdk:17-jdk-slim

COPY target/blog2-0.0.1-SNAPSHOT.jar app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]

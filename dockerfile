# FROM openjdk:8-jdk-alpine
FROM openjdk:17-jdk-slim
# FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY target/blog2-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]


# FROM openjdk:8-jdk-alpine

FROM openjdk:17-jdk-slim
# FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app
COPY target/review-0.0.1-SNAPSHOT.jar review.jar
CMD ["java", "-jar", "review.jar"]
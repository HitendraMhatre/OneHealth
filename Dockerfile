
# FROM openjdk:8-jdk-alpine

FROM openjdk:17-jdk-slim
# FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app
COPY target/discount-0.0.1-SNAPSHOT.jar discount.jar
CMD ["java", "-jar", "discount.jar"]
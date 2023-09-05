# FROM openjdk:17-jdk-alpine

# COPY target/Hospital-0.0.1-SNAPSHOT.jar /app/hospital.jar

# WORKDIR /app

# CMD ["java", "-jar", "hospital.jar"]
# FROM openjdk:8-jdk-alpine

FROM openjdk:17-jdk-slim
# FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app
COPY target/Hospital-0.0.1-SNAPSHOT.jar hospital.jar
CMD ["java", "-jar", "hospital.jar"]
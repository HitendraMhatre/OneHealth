FROM openjdk:17-jdk-alpine

COPY target/Hospital-0.0.1-SNAPSHOT.jar /app/hospital.jar

WORKDIR /app

CMD ["java", "-jar", "hospital.jar"]

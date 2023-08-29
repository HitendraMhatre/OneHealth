FROM openjdk:8-jdk-alpine

COPY target/blog2-0.0.1-SNAPSHOT.jar /app/adminservice.jar

WORKDIR /app

CMD ["java", "-jar", "adminservice.jar"]

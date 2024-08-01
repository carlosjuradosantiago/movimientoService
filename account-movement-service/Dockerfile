FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/account-movement-service-0.0.1-SNAPSHOT.jar account-movement-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/account-movement-service.jar"]

FROM openjdk:17

WORKDIR /app

COPY spring-boot-application.jar /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar","spring-boot-application.jar"]


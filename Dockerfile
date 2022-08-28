FROM gradle:7.4 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

COPY ./gradle /home/gradle/src
COPY ./build.gradle /home/gradle/src
COPY ./settings.gradle /home/gradle/src
COPY ./src /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:17
MAINTAINER shakmr

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar","spring-boot-application.jar"]


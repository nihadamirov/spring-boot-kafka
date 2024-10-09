FROM gradle:8.10-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:17-slim

EXPOSE 8801

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/user-service.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:MaxRAMPercentage=75.0", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/user-service.jar"]
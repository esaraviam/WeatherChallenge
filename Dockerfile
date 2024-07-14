FROM gradle:8.9-jdk-21-and-22-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build -Dorg.gradle.vfs.watch=false --no-daemon

FROM openjdk:21-ea-1-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
FROM gradle:8.9-jdk-21-and-22-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build -Dorg.gradle.vfs.watch=false -Dkotlin.compiler.execution.strategy="in-process" --no-daemon

FROM openjdk:21-ea-1-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM gradle:8.9-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build -Dorg.gradle.vfs.watch=false --no-daemon
FROM ubuntu:latest AS build
LABEL authors="giovanileitevitor"

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/demo-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]


##
## Build stage
##
##FROM maven:3.8.3-openjdk-17 AS build
#FROM openjdk:17-jdk-slim AS build
#WORKDIR /app
#COPY . /app/
##RUN mvn clean package
#
##
## Package stage
##
#FROM openjdk:17-alpine
#WORKDIR /app
#COPY --from=build /app/target/*.jar /app/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]
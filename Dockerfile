# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests
#
# FROM openjdk:17-jdk-oracle
# COPY --from=build /target/movies-0.0.1-SNAPSHOT.jar movies.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","movies.jar"]

FROM maven:3.9.3-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /target/movies-0.0.1-SNAPSHOT.jar movies.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","movies.jar"]
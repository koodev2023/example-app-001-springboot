# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests
#
# FROM openjdk:17-jdk-oracle
# COPY --from=build /target/movies-0.0.1-SNAPSHOT.jar movies.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","movies.jar"]

FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-oracle
COPY --from=build /target/movies-0.0.1-SNAPSHOT.jar movies.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","movies.jar"]
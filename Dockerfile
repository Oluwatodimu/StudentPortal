FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dspring.config.location=src/main/resources/application-dev.yml

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/StudentPortal-0.0.1-SNAPSHOT.jar StudentPortal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "StudentPortal.jar"]


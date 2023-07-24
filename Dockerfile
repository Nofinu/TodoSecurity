FROM maven:3.9.3-eclipse-temurin-17 as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM openjdk:20-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
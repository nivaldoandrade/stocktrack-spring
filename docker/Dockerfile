FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml /app/pom.xml

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn -Pprod clean package

FROM amazoncorretto:21-alpine-jdk

COPY --from=build /app/target/*.jar app.jar

CMD apt-get update -y

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
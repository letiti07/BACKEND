FROM  maven:3-eclipse-temurin-17-alpine as build
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -U -DskipTests

FROM openjdk:18
COPY --from=build /opt/app/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.awt.headless=true", "-jar", "app.jar"]
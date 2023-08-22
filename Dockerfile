FROM openjdk:18
ARG JAR_FILE=target/projet-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.awt.headless=true", "-jar", "app.jar"]



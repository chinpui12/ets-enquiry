FROM amazoncorretto:11
COPY ./target/*.jar /app.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "/app.jar"]
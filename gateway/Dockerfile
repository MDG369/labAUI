FROM bellsoft/liberica-openjdk-alpine:17
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

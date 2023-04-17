FROM bellsoft/liberica-openjdk-alpine:17
EXPOSE 8082
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

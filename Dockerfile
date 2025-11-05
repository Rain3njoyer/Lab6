FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/devops-0.1.0.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]

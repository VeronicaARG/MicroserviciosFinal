FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/eureka-server-0.0.1.jar
COPY ${JAR_FILE} eureka_app.jar
ENTRYPOINT ["java", "-jar", "eureka_app.jar"]
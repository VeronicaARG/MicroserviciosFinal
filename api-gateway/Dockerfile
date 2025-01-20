FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway_app.jar
ENTRYPOINT ["java","-jar","gateway_app.jar"]
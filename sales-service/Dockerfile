FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sales-service-0.0.1.jar
COPY ${JAR_FILE} sales_app.jar
ENTRYPOINT ["java", "-jar", "sales_app.jar"]
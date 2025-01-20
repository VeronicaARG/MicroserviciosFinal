FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/shopping_cart-service-0.0.1.jar
COPY ${JAR_FILE} shopping_cart_app.jar
ENTRYPOINT ["java","-jar","shopping_cart_app.jar"]
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

COPY src/main/resources/application.yml /app/src/main/resources/application.yml

COPY build/libs/*.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]
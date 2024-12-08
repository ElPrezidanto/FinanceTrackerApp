FROM maven:3.9.6-eclipse-temurin-22 AS builder

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests


FROM oracle/graalvm-ce:22.3.3

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

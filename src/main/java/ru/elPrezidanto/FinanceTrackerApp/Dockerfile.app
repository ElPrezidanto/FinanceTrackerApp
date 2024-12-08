
FROM maven:3.9.6-eclipse-temurin-22 as builder
WORKDIR /app


COPY . .

RUN mvn clean package -DskipTests


FROM oraclelinux:8-slim as runner
WORKDIR /app


RUN dnf install -y curl \
    && curl -L -o /tmp/jdk.tar.gz https://download.oracle.com/java/22/latest/jdk-22_linux-x64_bin.tar.gz \
    && mkdir -p /usr/lib/jvm \
    && tar -xzf /tmp/jdk.tar.gz -C /usr/lib/jvm \
    && rm /tmp/jdk.tar.gz \
    && ln -s /usr/lib/jvm/jdk-22.0.3/bin/java /usr/bin/java

# Копируем скомпилированный jar из этапа сборки
COPY --from=builder /app/target/*.jar app.jar

# Открываем порты
EXPOSE 8080

# Устанавливаем JAVA_TOOL_OPTIONS для отладки
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]

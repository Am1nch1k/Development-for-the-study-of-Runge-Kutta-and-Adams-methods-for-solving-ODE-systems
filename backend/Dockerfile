# Этап сборки
FROM gradle:8.12-jdk17 AS build

# Установка рабочей директории
WORKDIR /app

# Копирование только файлов, необходимых для сборки
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Сборка приложения (отключаем демона для Docker)
RUN gradle build --no-daemon -x test

# Этап выполнения
FROM eclipse-temurin:17-jre

# Установка рабочей директории
WORKDIR /app

# Копирование собранного JAR-файла
COPY --from=build /app/build/libs/*.jar app.jar

# Открытие порта
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
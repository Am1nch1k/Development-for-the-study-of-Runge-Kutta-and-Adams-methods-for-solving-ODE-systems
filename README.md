# 🧮 ODE Solver - Веб-приложение для решения систем ОДУ

- Java
- Spring Boot
- Docker
- Nginx
- Postman
- Java Script
- HTML/CSS
- REST API
## 📌 Содержание
- [О проекте](#-о-проекте)
- [Технологии](#-технологии)
- [Установка](#-установка)
- [Запуск](#-Запуск)

## 📝 О проекте

Веб-приложение для численного решения систем обыкновенных дифференциальных уравнений (ОДУ) с визуализацией результатов.

**Поддерживаемые методы**:
- Метод Рунге-Кутты 4-го порядка
- Метод Адамса-Башфорта 4-го порядка

## 🛠 Технологии

### Бэкенд
| Технология | Версия | Назначение |
|------------|--------|------------|
| Spring Boot | 3.2.0 | Backend-фреймворк |
| Java | 17 | Основной язык |
| Gradle | 8.5 | Система сборки |

### Фронтенд
| Технология | Назначение |
|------------|------------|
| HTML5 | Структура страницы |
| CSS3 | Стилизация |
| Chart.js | Визуализация графиков |
| Fetch API | HTTP-запросы |


## 🚀 Установка

### Требования
- Docker 20.10+
- Docker Compose 2.0+
- 4GB+ оперативной памяти


### Запуск
- git clone https://github.com/Am1nch1k/Development-for-the-study-of-Runge-Kutta-and-Adams-methods-for-solving-ODE-systems.git
- cd Development-for-the-study-of-Runge-Kutta-and-Adams-methods-for-solving-ODE-systems
- docker-compose up --build -d
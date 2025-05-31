FROM nginx:1.25-alpine

# Удаляем дефолтный конфиг (теперь он создается при запуске)
RUN rm /etc/nginx/conf.d/default.conf

# Копируем статические файлы
COPY . /usr/share/nginx/html/

# Открываем порт
EXPOSE 80

# Команда запуска (уже прописана в compose)
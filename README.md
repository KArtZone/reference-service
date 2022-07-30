# Сервис reference-service

Сервис нормативно-справочной информации.

## Сборка и запуск приложения

### Для сборки проекта необходимо в корневой директории выполнить команду:

```bash
mvn clean package
```

### Запуск

Для запуска собранного проекта, находясь в директории target, необходимо выполнить команду:

```bash
java -jar reference-service*.jar
```

По выполнению данной команды приложение будет запущено на порту _8080_ (значение по умолчанию)

Для изменения параметров приложения необходимо изменить значения в файле `src/main/resources/application.yml`.

### Swagger

Для локального теста приложения, необходимо перейти по следующему url: `http://localhost:8080/swagger-ui.html`.

### Docker

Для сборки контейнера выполнить команду:

```bash
docker build -t reference-service .
```

Для запуска контейнера можно воспользоваться командой:

```bash
docker run --name reference-service --rm -d -p 8080:8080 reference-service
```

## IntelliJ IDEA

### Запуск проекта

`Run` -> `Edit Configurations...` -> `Add New Configuration` -> `Spring Boot`

- `Main Class` - выбрать `ReferenceServiceApplication`.
- Сохранить конфигурацию.
- Запустить сохраненную конфигурацию.

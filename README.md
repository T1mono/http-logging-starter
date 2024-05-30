# http-logging-starter

## Описание

Spring Boot Starter для логирования HTTP запросов

## Сборка backend части

```cmd
cd http-logging-starter && mvn clean package
```
Linux (bash):
```bash
(cd http-logging-starter && mvn clean package)
```

## Запуск

### Простой запуск:
```bash
java -jar target/http-logging-starter-0.0.1-SNAPSHOT.jar
```

## Использование для проверки работы логирования:

Если добавить зависимость swagger в pom.xml -

URL с swagger документацией: http://localhost:8888/swagger-ui/index.html

## Запуск браузера с отключённым CORS

chrome.exe --user-data-dir="C://chrome-dev-disabled-security" --disable-web-security --disable-site-isolation-trials

## Использование http-logging-starter в другом проекте:

1. Создайте JAR-файл из http-logging-starter.

### для Maven:
```cmd
cd http-logging-starter && mvn clean package
```

### для  Gradle:
```cmd
cd http-logging-starter && gradle clean build
```

2. Добавьте этот JAR-файл в зависимости вашего другого проекта.
### для Maven:
#### Добавьте следующую зависимость в ваш pom.xml:
```
<dependency>
    <groupId>com.example</groupId>
    <artifactId>http-logging-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### для Gradle:
#### Добавьте следующую зависимость в ваш build.gradle:
```
dependencies {
    implementation 'com.example:http-logging-starter:0.0.1-SNAPSHOT'
}
```

### Когда вы добавите эту зависимость в ваш другой проект, он автоматически включит ваш http-logging-starter и начнет логировать HTTP запросы.

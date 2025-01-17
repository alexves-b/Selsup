# Тестовое задание для компании Selsup

Выполнено тестовое задание для компании Selsup на языке Java. Задача заключалась в создании класса для взаимодействия с API Честного знака.

## Описание задания

Необходимо было реализовать класс `CrptApi`, который принимает в конструкторе количество запросов (`requestLimit`), которое может быть отправлено за определенный период времени (`timeUnit`) в формате `TimeUnit`.

### Структура задания

```java
public CrptApi(TimeUnit timeUnit, int requestLimit)
```

## Решение

### Описание класса

Собственно сам класс `CrptApi` поддерживает ограничение на количество запросов к API Честного знака. Класс реализован с учетом потокобезопасности и обработки ограничений на количество запросов.

### Пример использования

```java
CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 10);
```

При создании экземпляра класса `CrptApi`, указывается период времени и количество запросов, которое можно отправить за этот период.

## Дополнительная информация

Для более подробной информации о решении задания, смотрите исходный код класса `CrptApi.java`.

# Project-manager
### Веб приложение со Spring Security, Spring Jpa и базой данных H2
--------
## Авторизация и разделение по ролям
### Доступ к структуре проектов и задачи для них доступны всем пользователям. Изменение структуры, такие как удаление, изменение, добавление проектов и подпроектов доступны только админам.
### Изменения задач, такие как создать задачу для любого уровня проекта\подпроекта, изменить статус, удалить свою задачу, доступны всем пользователям. Но только пользователь может задать задачу к проекту и подпроекту.
--------
# Инструкция к запуску
## Проект собран с помощью maven поэтому запуск осуществляется через консоль
1. Открываем папку проекта переходим в /project-manager/target
2. Открываем консоль в этой папке, вводим java -jar project-manager.jar
3. Приложение запущенно на порте :8080
4. Для начала заносим все тестовые данные через консоль H2,
 доступна она по адресу http://localhost:8080/h2-console/
 + URL базы jdbc:h2:mem:managerDB
 + User Name 1
 + Password 1
5. Заносим данные(При добавлении пользователя, нужно учитывать то, что пароли хранятся в закодированном виде
Spring security самостоятельно кодирует и декодирует пароли, которые через него проходят, 
пользователей при входе это не беспокоит) (SQL скрипты для структуры бд и тестовых данных находятся в папке resources)

6. Далее через postman необходимо отправить пароль и login(Для авторизации). Приложение готово к использованию.
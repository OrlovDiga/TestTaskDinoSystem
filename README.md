# Dino System

Тестовое задание для DINO System
<details><summary>ТЗ</summary>
1. Написать программу, удовлетворяющую указанным требованиям.

2. Написать юнит тесты на написанный код.

3. Должны быть использованы следующие технологии: Java (8 версии или выше), Spring Boot, JUnit. Не должны быть использованы базы данных.

4. Должны быть предоставлены: исходный код программы, юнит тесты, инструкция по запуску программы, примеры вызова REST методов программы.

5. Стоит уделить особое внимание REST спецификации и организации REST методов.



Требования к программе.

Нужно написать серверную часть приложения (без UI части) по работе с пользователями и их телефонной книжкой.

Программа должна предоставлять REST API для:

* получения списка всех пользователей (владельцев телефонных книжек)

* создания, получения (по id), удаления, редактирования пользователя

* создания, получения (по id), удаления, редактирования записи в телефонной книжке

* получения списка всех записей в телефонной книжке пользователя

* поиск пользователей по имени (или его части)

* поиск телефонной записи по номеру телефона
</details>

### Example Request
`POST /api/user`

```javascript
{
	"name" : "dima",
	"surname" : "privaloc"
}
```

### Example Response
`200 OK`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
    "name": "dima",
    "surname": "privaloc",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```
----


### Example Request
`PUT /api/user`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
	"name" : "max",
	"surname" : "shizov",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```

### Example Response
`200 OK`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
	"name" : "max",
	"surname" : "shizov",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```
----


### Example Request
`GET /api/user/19cfe3f9-8bc0-41cc-8dba-27b37efd9b74

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
	"name" : "max",
	"surname" : "shizov",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```
----


### Example Request
`GET /api/user`

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
	"name" : "max",
	"surname" : "shizov",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```
----


### Example Request
`DELETE /api/user`

```javascript
{
    "id": "19cfe3f9-8bc0-41cc-8dba-27b37efd9b74",
	"name" : "max",
	"surname" : "shizov",
    "phone_book_id": "89c56e87-2a37-4b11-963a-117f33b8c48b"
}
```

### Example Response
`204 No Content`
----


Перед этим добавим еще пару пользователей с именами, начинающимися на di
### Example Request
`GET /api/user/search?inText=di`

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
[
    {
        "id": "5b44697d-c7ad-4351-bcb1-78075f1cf972",
        "name": "dim",
        "surname": "privaloc",
        "phone_book_id": "9b182ab9-3265-4101-8ec6-689b3013a074"
    },
    {
        "id": "a64c286a-44b0-4ab3-bd6e-42c3704e71c4",
        "name": "dinar",
        "surname": "gazizulin",
        "phone_book_id": "327ca53f-afe9-40dd-919c-9300b689ea4e"
    },
    {
        "id": "1c958506-fb7f-4042-8a1d-aaaf2e3b7cba",
        "name": "dima",
        "surname": "orlov",
        "phone_book_id": "faef406f-689f-46ec-8f04-5ff8a01c45a0"
    },
    {
        "id": "23dc8520-1017-4229-a2ee-16c177f1f990",
        "name": "digit",
        "surname": "medvedevius",
        "phone_book_id": "bf654657-7f48-472c-a22d-c2668c38f4a0"
    }
]
```
----





### Example Request
`POST /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b`

```javascript
{
	"name" : "Dasha",
	"value" : "893214513228"
}
```

### Example Response
`200 OK`

```javascript
{
    "id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
    "name": "Dasha",
    "value": "893214513228",
}
```
----


### Example Request
`PUT /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b`

```javascript
{
	"id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
	"name" : "Dashenka",
	"value" : "893214513228"
}
```

### Example Response
`200 OK`

```javascript
{
	"id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
	"name" : "Dashenka",
	"value" : "893214513228"
}
```
----


### Example Request
`GET /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b/340f187b-6d18-4cfc-9239-a89c02f45bc9

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
{
	"id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
	"name" : "Dashenka",
	"value" : "893214513228"
}
```
----


### Example Request
`GET /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b`

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
{
	"id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
	"name" : "Dashenka",
	"value" : "893214513228"
}
```
----


### Example Request
`DELETE /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b`

```javascript
{
	"id": "340f187b-6d18-4cfc-9239-a89c02f45bc9",
	"name" : "Dashenka",
	"value" : "893214513228"
}
```

### Example Response
`204 No Content`
----


Перед этим добавим еще пару пользователей с именами, начинающимися на 89
### Example Request
`GET /api/pb/89c56e87-2a37-4b11-963a-117f33b8c48b/search?inText=89`

```javascript
{

}
```

### Example Response
`200 OK`

```javascript
[
    {
        "id": "f9b6a1cf-b51d-45fb-b6ed-3e1157042121",
        "name": "Dashenka",
        "value": "8799321432513228",
    },
    {
        "id": "b0a4e161-3936-44dd-865b-4e659d64685d",
        "name": "Dashenka",
        "value": "879932143251443228",
    },
    {
        "id": "7dc4d204-e6ee-45ab-bb3f-3d5711c7ffe9",
        "name": "Dashenka",
        "value": "879932143251443228",
    },
    {
        "id": "1c71078f-c86b-40d0-af34-2d56615e0698",
        "name": "Dashenka",
        "value": "87993214513228"
    }
]
```
----




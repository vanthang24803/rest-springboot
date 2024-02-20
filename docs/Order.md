<h2 align="center">ORDER</h2>

### Endpoint:

```
/api/v1/order/**
```

### GET Orders

```http
GET /api/v1/orders
```

#### Response

```json
[
    {
        "id": "08f8dbc3-83ce-4d99-be2a-bd7d9640222a",
        "email": "mail@mail.com",
        "customer": "May Nguyen",
        "numberPhone": "0123456789",
        "address": "HN",
        "payment": "COD",
        "status": "SHIPPING",
        "shipping": true,
        "quantity": 1,
        "totalPrice": 65800,
        "details": [
            {
                "id": "dfa6102c-792b-4288-93f2-3a6aa69b538e",
                "productId": "3be101f8-b929-46e5-8d77-5bd7a8f83577",
                "optionId": "3413e2e9-0c34-478c-b935-67d1ee505ea4",
                "name": "Người Là Ai Giữa Tâm Tư Này - Tập 1",
                "thumbnail": "https://utfs.io/f/0dce4f3c-f91e-4237-b357-185511985e74-ihb14u.webp",
                "option": "Thường",
                "price": 80000.0,
                "sale": 18,
                "quantity": 1
            }
        ],
        "createdAt": "2024-02-18T09:33:26.650649",
        "updatedAt": "2024-02-18T09:33:58.464103"
    }
]
```

### GET Product by id

```http
GET /api/v1/order/{id}
```

```json
{
        "id": "08f8dbc3-83ce-4d99-be2a-bd7d9640222a",
        "email": "mail@mail.com",
        "customer": "May Nguyen",
        "numberPhone": "0123456789",
        "address": "HN",
        "payment": "COD",
        "status": "SHIPPING",
        "shipping": true,
        "quantity": 1,
        "totalPrice": 65800,
        "details": [
            {
                "id": "dfa6102c-792b-4288-93f2-3a6aa69b538e",
                "productId": "3be101f8-b929-46e5-8d77-5bd7a8f83577",
                "optionId": "3413e2e9-0c34-478c-b935-67d1ee505ea4",
                "name": "Người Là Ai Giữa Tâm Tư Này - Tập 1",
                "thumbnail": "https://utfs.io/f/0dce4f3c-f91e-4237-b357-185511985e74-ihb14u.webp",
                "option": "Thường",
                "price": 80000.0,
                "sale": 18,
                "quantity": 1
            }
        ],
        "createdAt": "2024-02-18T09:33:26.650649",
        "updatedAt": "2024-02-18T09:33:58.464103"
    }
```

### Create Order

```http
POST /api/v1/order
```

```json
{
        "id": "08f8dbc3-83ce-4d99-be2a-bd7d9640222a",
        "email": "mail@mail.com",
        "customer": "May Nguyen",
        "numberPhone": "0123456789",
        "address": "HN",
        "payment": "COD",
        "status": "SHIPPING",
        "shipping": true,
        "quantity": 1,
        "totalPrice": 65800,
        "details": [
            {
                "id": "dfa6102c-792b-4288-93f2-3a6aa69b538e",
                "productId": "3be101f8-b929-46e5-8d77-5bd7a8f83577",
                "optionId": "3413e2e9-0c34-478c-b935-67d1ee505ea4",
                "name": "Người Là Ai Giữa Tâm Tư Này - Tập 1",
                "thumbnail": "https://utfs.io/f/0dce4f3c-f91e-4237-b357-185511985e74-ihb14u.webp",
                "option": "Thường",
                "price": 80000.0,
                "sale": 18,
                "quantity": 1
            }
        ],
        "createdAt": "2024-02-18T09:33:26.650649",
        "updatedAt": "2024-02-18T09:33:58.464103"
    }
```

### Update Status Order

```http
PUT /api/v1/order/{id}/status
```

```json
{
    "status" : "CREATE"
}
```
### Update Status Order

```http
PUT /api/v1/order/{id}
```

```json
{
    "customer": "May Nguyen",
    "email": "mail@mail.com",
    "numberPhone": "0987654321",
    "address": "Thanh Xuân, HN",
    "payment": "COD",
    "shipping": true
}
```


### Delete Product

```http
DELETE /api/v1/order/{id}
```

```json
{
    "Order deleted success!"
}
```

</br>

## Chapters
  1. [Overview](../README.md)
  2. [Installation](./Installation.md)
  3. [Configuration](./Configuration.md)
  4. [Authentication](./Auth.md)
  5. [Profile](./Profile.md)
  6. [Product](./Product.md)
  7. [Order](./Order.md)
    

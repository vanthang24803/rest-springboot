<h2 align="center">PRODUCT</h2>

### Endpoint:

```
/api/v1/product/**
```

### GET Products

```http
GET /api/v1/products
```

#### Response

```json
[
  {
    "id": "string",
    "name": "string",
    "brand": "string",
    "options": [],
    "images": [],
    "categories": [],
    "createdAt": "string",
    "updatedAt": "string"
  }
]
```

| Parameter  | Type     | Description                                                         |
| :--------- | :------- | :------------------------------------------------------------------ |
| `category` | `string` | Sort by Category                                                    |
| `sortBy`   | `string` | Sort By Price                                                       |
| `limit`    | `int`    | Limit product default = 20                                          |
| `page`     | `int`    | Pagination                                                          |
| `filter`   | `string` | Alphabet, ReverseAlphabet , HighToLow , LowToHigh , Lasted , Oldest |
| `filter`   | `string` | Sale, Stocking , Inventory, SoldOut                                 |

### GET Product by id

```http
GET /api/v1/product/{id}
```

```json
[
  {
    "id": "string",
    "name": "string",
    "brand": "string",
    "options": [],
    "images": [],
    "categories": [],
    "createdAt": "string",
    "updatedAt": "string"
  }
]
```

### Create Product

```http
POST /api/v1/product
```

```json
{
  "name": "string",
  "brand": "string",
  "thumbnail": "string",
  "categoryId": "string",
  "options": [
    {
      "name": "string",
      "sale": 0,
      "quantity": 0,
      "price": 0
    }
  ]
}
```

### Update Product

```http
PUT /api/v1/product/{id}
```

```json
{
  "name": "string",
  "brand": "string",
  "thumbnail": "string"
}
```

### Delete Product

```http
DELETE /api/v1/product/{id}
```

```json
{
    "Product deleted success!"
}
```

### GET All Categories

```http
GET /api/v1/product/categories
```

```json
[
  {
    "id": "string",
    "name": "string",
    "createdAt": "string",
    "updatedAt": "string"
  }
]
```

### GET Category By Id

```http
GET /api/v1/product/category/{id}
```

```json
{
  "id": "string",
  "name": "string",
  "createdAt": "string",
  "updatedAt": "string"
}
```

### Create Category

```http
POST /api/v1/product/category
```

```json
{
  "name": "string"
}
```

### Update Category

```http
PUT /api/v1/product/category
```

```json
{
  "name": "string"
}
```

### Update Category

```http
PUT /api/v1/product/category/{id}
```

### Add Category to Product

```http
POST api/v1/product/${productId}/category/${categoryId}
```

### Remove Category to Product

```http
DELETE api/v1/product/${productId}/category/${categoryId}
```

### Create Option

```http
POST  api/v1/product/${productId}/option
```

```json
{
  "name": "Đặc biệt",
  "sale": 2,
  "quantity": 100,
  "price": 100000
}
```

### Get Option By Product ID

```http
GET api/v1/product/${productId}/options
```

```json
[
  {
    "id": "b1af00e0-e0b8-44fd-acf8-2e140207b91e",
    "name": "Đặc biệt",
    "sale": 0,
    "quantity": 0,
    "price": 125000.0,
    "status": false,
    "createdAt": "2024-02-14T21:16:58.785464",
    "updatedAt": "2024-02-17T13:55:03.670903"
  },
  {
    "id": "3413e2e9-0c34-478c-b935-67d1ee505ea4",
    "name": "Thường",
    "sale": 10,
    "quantity": 15,
    "price": 100000.0,
    "status": true,
    "createdAt": "2024-02-14T21:07:23.843364",
    "updatedAt": "2024-02-18T09:33:26.753125"
  }
]
```

### Update Option

```http
PUT  api/v1/product/${productId}/option/{optionId}
```

```json
{
  "name": "Thường",
  "sale": 10,
  "quantity": 100,
  "price": 100000.0
}
```

### Delete Option 


```http
DELETE  api/v1/product/${productId}/option/{optionId}
```


### Create Information

```http
POST  api/v1/product/${productId}/information
```

```json
{
    "author": "string",
    "translator": "string",
    "category": "string",
    "format": "String",
    "numberOfPage": "string",
    "isbn": "string",
    "publisher": "string",
    "company": "string",
    "gift": "string",
    "price": "string",
    "released": "string",
    "introduce": "string"
}

```

### GET Information

```http
GET  api/v1/product/${productId}/information
```

```json
{
    "author": "string",
    "translator": "string",
    "category": "string",
    "format": "String",
    "numberOfPage": "string",
    "isbn": "string",
    "publisher": "string",
    "company": "string",
    "gift": "string",
    "price": "string",
    "released": "string",
    "introduce": "string"
}

```


### Update Information

```http
PUT  api/v1/product/${productId}/information
```

```json
{
    "author": "string",
    "translator": "string",
    "category": "string",
    "format": "String",
    "numberOfPage": "string",
    "isbn": "string",
    "publisher": "string",
    "company": "string",
    "gift": "string",
    "price": "string",
    "released": "string",
    "introduce": "string"
}

```

### Delete Information

```http
DELETE  api/v1/product/${productId}/information
```


### Upload Image

```http
POST api/v1/product/${productId}/image
```

### Delete Image

```http
DELETE api/v1/product/${productId}/image/{imageId}
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
    

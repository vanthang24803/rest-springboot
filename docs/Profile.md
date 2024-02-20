# Profile

Settings Profile Account API

## Dependencies

- Authotization
- Bearer Token

## Endpoint

```http
/api/auth/profile/**
```

### Profile

```http
GET /api/auth/profile
```

#### Response

- Status : 200

```json
{
  "id": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "avatar": "string",
  "roles": [],
  "addresses": []
}
```

- Status : 400

```json
{
    "Token not found"
}
```

### Update Profile

```http
PUT /api/auth/profile
```

#### Request

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string"
}
```

#### Response

- Status : 200

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string"
}
```

- Status : 400

```json
{
    "Token not found"
}
```

### Update Avatar

```http
PUT /api/auth/profile/avatar
```

#### Request

```formData
{
    "file": file,
}
```

#### Response

- Status : 200

```json
{
  "id": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "avatar": "string",
  "roles": [],
  "addresses": []
}
```

- Status : 400

```json
{
    "Token not found"
}
```

### Update Password

```http
PUT /api/auth/profile/password
```

#### Request

```json
{
  "oldPassword": "string",
  "newPassword": "string"
}
```

#### Response

- Status : 200

```json
{
  "status": true,
  "message": "Password updated successfully"
}
```

- Status : 400

```json
{
    "Token not found"
}
```

### Create Address

```http
POST /api/auth/profile/address
```

#### Request

```json
{
  "name": "string"
}
```

#### Response

- Status : 200

```json
{
  "id": "string",
  "name": "string",
  "createdAt": "string",
  "updatedAt": "string"
}
```

- Status : 400

```json
{
    "Token not found"
}
```

### GET Address

```http
GET /api/auth/profile/address
```

#### Response

- Status : 200

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

- Status : 400

```json
{
    "Token not found"
}
```

### Update Address

```http
PUT /api/auth/profile/address/{id}
```

#### Request

```json
{
  "name": "string"
}
```

#### Response

- Status : 200

```json
{
  "id": "string",
  "name": "string",
  "createdAt": "string",
  "updatedAt": "string"
}
```

- Status : 400

```json
{
    "Token not found"
}
```

- Status : 404

```json
{
    "Address not found"
}
```


### Delete Address

```http
PUT /api/auth/profile/address/{id}
```

#### Response

- Status : 200

```json
{
  "Address deleted successfully"
}
```

- Status : 400

```json
{
    "Token not found"
}
```

- Status : 404

```json
{
    "Address not found"
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

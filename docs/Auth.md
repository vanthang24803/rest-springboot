# Authentication
The simplest way to authenticate users

## Dependencies:
-   Spring Security
-   OAuth2 Client


## Endpoint:
```
/api/auth/** 
```

### Register:

```
POST /api/auth/register 
```

#### Request

```json
{
    "email" : "string",
    "password" : "string"
}
```

#### Response: 

- Status : 200
```json 
{
    "User registered success!" 
}
```
- Status : 400
```json 
{
    "Email is taken!" 
}
```

### Login:

```http 
POST /api/auth/login 
```

#### Request

```json
{
    "email" : "string",
    "password" : "string"
}
```

#### Response: 

- Status : 200
```json 
{
    "email": "string",
    "accessToken": "string"
}
```
- Status : 401
```json 
{
    "timestamp": "string",
    "status": 401,
    "error": "Unauthorized",
    "path": "/api/auth/login"
}
```


### Forgot Password

```http 
POST /api/auth/forgot-password?token=${string} 
```

#### Request

```json
{
    "password" : "string"
}
```

- Status : 200

```json 
{
   "Reset password successfully!"
}
```

- Status : 404
```json 
{
   "Token not found"
}
```

- Status : 400
```json 
{
   "Token out date"
}
```

### Verify Account

```http
GET /api/auth/verify-account?token=${string} 
```


- Status : 200
```json 
{
   "Verify account successfully!"
}
```
- Status : 404
```json 
{
   "Token not found"
}
```

- Status : 400
```json 
{
   "Token out date"
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
    

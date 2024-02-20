# Configuration

The local configuration for API was setup to be as simple as possible for the end-user.

Follow the steps below to get started with API.

## Prerequisites:

It is mandatory to perform the previous steps

- [Installation](./Installation.md)

### Config Environment Variables

```env
API_KEY=
API_SECRET=
CLOUD_NAME=
CLIENT_URL=
DB_PASSWORD=
DB_URL=
DB_USERNAME=
GG_ID=
GG_SECRET=
MAIL_PASSWORD=
MAIL_USER=
```

### STEP 1 — Config Database Postgres

```env
DB_PASSWORD= 
DB_URL= 
```

### STEP 2 — Config Gmail Service

```env
MAIL_USER= 
MAIL_PASSWORD= 
```

### STEP 3 — Config Cloudinary

Click [here](https://cloudinary.com/) create an account .

```env
API_KEY= 
API_SECRET= 
CLOUD_NAME= 
```

### STEP 3 — Config OAuth2 with Google

Click [here](https://console.cloud.google.com/) get attributes.

```env
GG_ID= 
GG_SECRET=
```

### LAST STEP — Config Your Front-end URL

```env
CLIENT_URL=
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

<div align="center">
  <a href="https://github.com/vanthang24803/store-next-14">
    <img src="https://theme.hstatic.net/200000294254/1001077164/14/favicon.png?v=325" alt="logo" width="100" height="100">
  </a>
</div>

<h2 align="center">AMAK Store API</h2>

<p align="center">An E-commerce Application API using <a href="https://spring.io/">Spring boot 3</a>·  <a href="https://www.postgresql.org/">PostgreSQL</a> ·  <a href="https://www.docker.com/">Docker</a></p>


## Options

- <a href="https://github.com/vanthang24803/microservice-store">Microservice with ASP NET 8 .</a>

## Documentation
  - [Installation](./docs/Installation.md)
  - [Configuration](./docs/Configuration.md)
  - [Authentication](./docs/Auth.md)
  - [Profile](./docs/Profile.md)
  - [Product](./docs/Product.md)
  - [Order](./docs/Order.md)


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

```env 
API_KEY=
API_SECRET=
CLIENT_URL=
CLOUD_NAME=
DB_PASSWORD=
DB_URL=
DB_USERNAME=
GG_ID=
GG_SECRET=
MAIL_PASSWORD=
MAIL_USERNAME=
```

## Run Locally

Clone the project (JDK >= 17)

```bash
  git clone https://github.com/vanthang24803/store-spring-monolithic.git
```

Go to the project directory

```bash
  cd store-spring-monolithic
```

Install dependencies

```bash
    mvn dependency:copy-dependencies
```

Start the server

```bash
  mvn spring-boot:run
```

Website using port 8080

## Build with Docker


```bash
  docker-compose up
```



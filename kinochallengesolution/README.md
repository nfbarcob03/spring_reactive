# Kino API

Kino is an API to help managing the Kino Cinema, providing useful endpoints to: create movies, create and update showtimes and getting information about particular movie.

- Create movies
- Create and update show-times and prices for a movie
- Get information about particular movie from OMDb
- Get information about particular showtime
- Rate a movie
- Get the top N movies based on their average rate

## ¿How is built?

Kino API is built on top of Spring Framework and Spring Boot with Java 11.
It uses [neo4j] as NoSQL backend database because its a fast graph database therefore the relationships between movies, rating and showtimes is easier to model and create powerful queries in the future, like movie recommendation based on ratings from other users.

It is developed using Java in a functional way supported by [vavr] library to make the code more readable and easier to maintain.

[maven] is the tool that allows the compilation, building and execution of the application.


The Kino API is developed based on Hexagonal Architecture in a modular fashion. Following are the modules you can find in the application:

- **app-launcher:** This module contains the main class in order to run the application
- **application:** Contains the controllers and endpoints exposed to the clients.
- **domain:** Contains the isolated domain objects, spi (service provider interface) and api to query the domain. 
- **infrastructure:** Contains all that application needs to work, such database configurations and communication with OMDb.
- **omdb-connector:** Connector to allow the communication with OMDb API.
- **rest-client:** Http Client implementation to perform calls to http endpoints.


## Prerequisites:

In order to compile, build and execute the application, you will need to have installed the following software:

- Java 11. You can download from <https://openjdk.java.net/projects/jdk/11/> or <https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html>
- Maven 3: <https://maven.apache.org/download.cgi>
- Docker: <https://www.docker.com/products/docker-desktop>
- Docker Compose: Please, follow the instructions here https://docs.docker.com/compose/install/ to install the docker-compose.
- Git: <https://git-scm.com/>

## Running the application

Please, ensure that the ports **_8080_**, **_7474_** and **_7687_** are free in your machine.

- The port **_8080_** is where the main application will be listening for incoming requests.
- The port **_7474_** is where the Neo4j console will be listening. <http://localhost:7474/browser/>
- The port **_7687_** is where the Neo4j engine will be listening.

After cloning the git repository, you may want to compile, build and run some available tests.

First, you need to export the environment variable with the value of OMDb API key:

```sh
 export OMDB_API_KEY=<YOUR_OMDB_API_KEY>
```

Run the following command:

```sh
 ./mvnw clean install
```


Start the docker compose with Neo4j database:

```sh
 docker-compose up -d
```

Start the application:

```sh
  ./mvnw spring-boot:run -pl app-launcher
```
Wait for the application starts. You will see a message similar to this in your console:

```java
2022-02-17 01:20:42.041  INFO 1945 --- [main] com.fourthwall.kino.Application  : Started Application in 4.516 seconds (JVM running for 4.93)
```
        
Execute the following command to check if the application is running and kicking (health check):

```sh
curl http://localhost:8080/actuator/health
```

If everything is healthy, you will see the following message in your console:

```java
{"status":"UP"}
```

## API Docs

You can access to the API documentation (OpenAPI) in the following URL:

<http://localhost:8080/swagger-ui/index.html>

Here you will find useful documentation about the available endpoints offered by Kino API.

## License

MIT

## ¿Questions?

Don't hesitate to reach me if you find bugs or have questions, please email me to the following address:

[adborja@gmail.com](mailto:adborja@gmail.com)

[//]: #


[neo4j]: <https://neo4j.com/>
[maven]: <https://maven.apache.org/>
[vavr]: <https://www.vavr.io/>

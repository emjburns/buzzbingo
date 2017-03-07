## How to use
Reference tutorial: https://spring.io/guides/gs/spring-boot-docker/  

(1) To build (**Must do before building docker container**):  
`./gradlew build`  

(2) To build docker container after build:  
`./gradlew build buildDocker`  
You can also push docker containers by setting `push = true` in build.gradle, but it will only have tag of 'latest' atm  

To build docker compose:
`docker-compose build`

(3) To run docker container of api:
`docker run -p 8080:8080 emjburns/buzzbingo`

To run docker compose with api and redis: 
`docker-compose up`

To run:  
`java -jar build/libs/buzzbingo-0.0.1-SNAPSHOT.jar`  
OR  
`./gradlew bootRun`


## Info

Application runs on `http://localhost:8080/`
Swagger runs on `http://localhost:8080/swagger-ui.html#/`

DEPENDENCIES  
- Must have a redis up and running and available at localhost if running locally.
  


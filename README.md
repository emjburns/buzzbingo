Reference tutorial: https://spring.io/guides/gs/spring-boot-docker/  


To build:  
`./gradlew build`  

To build docker container after build:  
`./gradlew build buildDocker`  
You can also push docker containers by setting `push = true` in build.gradle, but it will only have tag of 'latest' atm  



To run docker container (redis integration not supported yet):
`docker run -p 8080:8080 emjburns/buzzbingo`

To run:  
`java -jar build/libs/buzzbingo-0.0.1-SNAPSHOT.jar`  
OR  
`./gradlew bootRun`

Application runs on `http://localhost:8080/`
Swagger runs on `http://localhost:8080/swagger-ui.html#/`

DEPENDENCIES  
- Must have a redis up and running and available at localhost 
  


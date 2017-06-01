## To run

Build and Run (Docker)
```bash
./gradlew build
docker-compose build
docker-compose up
```
Clean up (Docker)
```bash
docker-compose down
```

Build and Run (not docker)
```json
# Get Redis running
redis-server   
   
# Build and run api server
./gradlew bootRun
   
# Build and run ui
cd ui/   
npm install
npm start

```

## Prerequisites 
Must have Redis, npm, and Java.


## How to use
Reference tutorial: https://spring.io/guides/gs/spring-boot-docker/  

To build api (**Must do before building docker container**):  
`./gradlew build`  

To run api:  
`java -jar build/libs/buzzbingo-0.0.1-SNAPSHOT.jar`  
OR  
`./gradlew bootRun`

To run ui:   
`cd ui/`   
`npm start`


## Info
 
Application runs on `http://localhost:8080/`   
Swagger runs on `http://localhost:8080/swagger-ui.html#/`    
UI runs on `http://localhost:3000/`   
 
DEPENDENCIES  
- Must have a redis up and running and available at localhost if running locally.
  


#!/bin/bash

# Must be logged into docker
docker login
TAG="$(date +"%Y-%m-%d_%H-%M-%S")"

buildAPI() {
    ./gradlew build
    docker build -t emjburns/buzzbingo-api:$TAG .
}

pushAPI() {
    docker push emjburns/buzzbingo-api:$TAG
}

buildUI() {
    cd ui
    ng build --aot --prod
    docker build -t emjburns/buzzbingo-ui:$TAG .
}

pushUI() {
    docker push emjburns/buzzbingo-ui:$TAG
}


buildAPI
buildUI
pushAPI
pushUI




#!/bin/bash

# Must be logged into docker
#$ docker login
TAG="$(date +"%Y-%m-%d_%H-%M-%S")"
REPO="emjburns"


buildAPI() {
    ./gradlew build
    docker build -t $REPO/buzzbingo-api:$TAG .
}

pushAPI() {
    docker push $REPO/buzzbingo-api:$TAG
}

buildUI() {
    cd ui
    ng build --aot --prod
    docker build -t $REPO/buzzbingo-ui:$TAG .
}

pushUI() {
    docker push $REPO/buzzbingo-ui:$TAG
}

pushLatest(){
    docker tag $REPO/buzzbingo-ui:$TAG $REPO/buzzbingo-ui:latest
    docker push $REPO/buzzbingo-ui:latest

    docker tag $REPO/buzzbingo-api:$TAG $REPO/buzzbingo-api:latest
    docker push $REPO/buzzbingo-api:latest
}

buildAPI
buildUI
pushAPI
pushUI
pushLatest




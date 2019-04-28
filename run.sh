#!/bin/bash
./gradlew clean build -x test
sudo docker-compose build
sudo docker-compose up
#sudo docker build -t poc_project .
#sudo docker run --network="host" -it poc_project
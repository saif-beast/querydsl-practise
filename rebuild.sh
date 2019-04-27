#!/bin/bash
./gradlew clean build -x test
sudo docker-compose build project
sudo docker-compose restart project
#sudo docker build -t poc_project .
#sudo docker run --network="host" -it poc_project
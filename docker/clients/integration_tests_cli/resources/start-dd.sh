#!/bin/bash

mkdir -p ./src/main/resources/
touch ./src/main/resources/server.properties
echo "serverHostName=$server_host" > ./src/main/resources/server.properties
echo "serverPortNumber=$server_port" >>  ./src/main/resources/server.properties

cd ./integration_tests_cli
mvn test

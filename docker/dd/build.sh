#!/bin/bash

#Preparing environment
cd ../../j2e
echo "Compiling the DD system"
mvn -q -DskipTests clean package
echo "Done"
cd ./WebServices
cp ./target/drone-delivery-backend.war ../../docker/dd/.

# building the docker image
cd ../../docker/dd/
docker build -t livrair/dd-int .

# cleaning up the environment
rm -rf drone-delivery-backend.war

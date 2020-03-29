#!/bin/bash

#Preparing environment
cd ../../MappingServiceExterne
./compile.sh
cp ./server.exe ../docker/partners/.

# building the docker image
cd ../docker/partners/
docker build -t livrair/dd-ext .

# cleaning up the environment
rm -rf server.exe

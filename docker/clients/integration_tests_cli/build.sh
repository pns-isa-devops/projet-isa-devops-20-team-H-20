#!/bin/bash

clientName='integration_tests_cli'

#Preparing environment
cd "../../../clients/$clientName"
echo "Compiling the Drone Delivery client $clientName system"
mvn -q -DskipTests clean
echo "Done"
cd ..
cp -ar "./$clientName/" "../docker/clients/$clientName/"

# building the docker image
cd "../docker/clients/$clientName"
docker build -t "livrair/dd-client-$clientName" .

# cleaning up the environment
rm -rf "$clientName/"

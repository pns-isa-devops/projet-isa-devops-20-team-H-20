#!/bin/bash

clientName='integration_cli'

#Preparing environment
cd "../../../clients/$clientName"
echo "Compiling the Drone Delivery client $clientName system"
mvn -q -DskipTests clean package assembly:single
echo "Done"
cp "./target/drone-delivery-client-$clientName-1.0-SNAPSHOT-jar-with-dependencies.jar" "../../docker/clients/$clientName/."

# building the docker image
cd "../../docker/clients/$clientName"
docker build -t "livrair/dd-client-$clientName" .

# cleaning up the environment
rm -rf "drone-delivery-client-$clientName-1.0-SNAPSHOT-jar-with-dependencies.jar"

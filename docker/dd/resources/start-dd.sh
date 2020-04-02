#!/bin/bash

# step #1: configure the map.propoerties file
mkdir -p ./WEB-INF/classes/
touch ./WEB-INF/classes/map.properties
echo "mapHostName=$map_host" >> ./WEB-INF/classes/map.properties
echo "mapPortNumber=$map_port" >> ./WEB-INF/classes/map.properties

# step #2: update the webapp to load the right properties
jar uvf ./webapps/drone-delivery-backend.war ./WEB-INF/classes/map.properties

# step #3: start the TomEE engine
catalina.sh run

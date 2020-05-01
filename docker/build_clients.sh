#!/bin/bash
# Run mvn clean install in j2 then mvn tomee:run in j2e/webservices
# Run mono server.exe in MappingPartners after compilation

cd clients
./build.sh
cd ..

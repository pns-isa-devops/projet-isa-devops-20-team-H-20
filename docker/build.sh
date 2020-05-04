#!/bin/bash
# Be sure that you do not have started mvn tomee:run or mono server.exe

./clients/build.sh
./dd/build.sh
./partners/build.sh

#!/bin/bash
# Be sure that you do not have started mvn tomee:run or mono server.exe

cd clients
./build.sh
cd ..

cd dd
./build.sh
cd ..

cd partners
./build.sh
cd ..
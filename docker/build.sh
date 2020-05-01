#!/bin/bash
# Be sure that you do not have started mvn tomee:run

cd dd
./build.sh
cd ..

cd partners
./build.sh
cd ..
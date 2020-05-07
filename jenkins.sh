#!/bin/bash

cd docker/jenkins
./build.sh
docker-compose up -d
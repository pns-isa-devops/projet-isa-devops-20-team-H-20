#!/bin/bash

cd boss_cli
./build.sh
cd ..

cd chargeur_cli
./build.sh
cd ..

cd garagiste_cli
./build.sh
cd ..

cd gestionnaire_cli
./build.sh
cd ..

cd manutentionnaire_cli
./build.sh
cd ..

cd service_client_cli
./build.sh
cd ..

cd integration_tests_cli
./build.sh
cd ..

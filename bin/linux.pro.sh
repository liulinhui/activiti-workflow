#!/usr/bin/env bash

cd ../
git pull
cd bin/
./package.sh
cd ../target/
java -Xms2048m -Xmx2048m -jar work-flow.jar --spring.profiles.active=pro --server.port=8080

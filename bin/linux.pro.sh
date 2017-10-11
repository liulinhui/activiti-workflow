#!/usr/bin/env bash

cd ../
git pull
cd bin/
./package.sh
cd ../target/
java -Xms1024m -Xmx1024m -jar work-flow.jar --spring.profiles.active=pro --server.port=8080

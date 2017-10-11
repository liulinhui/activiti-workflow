#!/usr/bin/env bash

cd ../target/
java -Xms1024m -Xmx1024m -jar work-flow.jar --spring.profiles.active=pro --server.port=8080

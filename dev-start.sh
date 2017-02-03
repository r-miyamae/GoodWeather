#!/bin/bash

echo "[INFO]Vue.js Compile Start"
cd ./etc/vuefiles/
npm run build

echo "[INFO]Vue.js Compile Finish"

cd ../../
./gradlew bootRun
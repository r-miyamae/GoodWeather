#!/bin/bash

echo "[INFO]Vue.js Compile Start"
npm run build

echo "[INFO]Vue.js Compile Finish"

cd ../../
./gradlew bootRun
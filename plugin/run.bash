#!/usr/bin/env bash

cd "$(dirname "$0")"

set -e

../gradlew dist

docker-compose down

#MODIFY rename template {PROJECT_NAME}
docker build . -t plugin-template-api-debug

docker-compose up -d

#MODIFY rename template {PROJECT_NAME}
docker logs -f plugin-template-api

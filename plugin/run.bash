#!/usr/bin/env bash

cd "$(dirname "$0")"

set -e

../gradlew dist

docker-compose down

###MODIFY replace template {PLUGIN_NAME.under}
docker build . -t mybpm-api-with-template

docker-compose up -d

###MODIFY replace template {PLUGIN_NAME.under}
docker logs -f plugin-template-api

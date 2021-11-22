#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 113

set -e

../gradlew dist

docker-compose down

docker build . -t "$(bash lib/image-base.bash)"

docker-compose up -d

###MODIFY replace template {PLUGIN_NAME.under}
docker logs -f plugin-template-api

#!/usr/bin/env bash

cd "$(dirname "$0")"

docker-compose down

#MODIFY rename template {PROJECT_NAME}
docker run --rm -v "/tmp/plugin-template-volumes:/data" \
       busybox:1.28 \
       find /data -mindepth 1 -maxdepth 1 -exec \
       rm -rf {} \;

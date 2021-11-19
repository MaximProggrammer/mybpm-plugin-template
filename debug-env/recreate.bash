#!/usr/bin/env bash

cd "$(dirname "$0")"

docker-compose down

###MODIFY replace template {PLUGIN_NAME.under}
docker run --rm -v "$HOME/volumes/plugin-template:/data" \
       busybox:1.28 \
       find /data -mindepth 1 -maxdepth 1 -exec \
       rm -rf {} \;

docker-compose up -d

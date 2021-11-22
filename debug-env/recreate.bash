#!/usr/bin/env bash
###PIN m1 MODIFY replace template {PLUGIN_NAME.under}

cd "$(dirname "$0")" || exit 172

docker rm -f plugin-template-web
docker rm -f plugin-template-api

docker-compose down

docker run --rm -v "$HOME/volumes/plugin-template:/data" \
       busybox:1.28 \
       find /data -mindepth 1 -maxdepth 1 -exec \
       rm -rf {} \;

docker-compose up -d

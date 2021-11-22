#!/usr/bin/env bash
###PIN m1 MODIFY replace template {PLUGIN_NAME.under}

cd "$(dirname "$0")" || exit 172

docker-compose down

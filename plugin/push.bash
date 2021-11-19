#!/usr/bin/env bash

cd "$(dirname "$0")"

set -e

../gradlew dist

VERSION="$(cat ../versions/version.txt)"

###MODIFY replace template {PLUGIN_NAME.under}
IMAGE=dockerhub.mybpm.kz/mybpm-api-with-template:$VERSION

docker build . -t "$IMAGE"

docker push "$IMAGE"

echo PUSHED IMAGE "$IMAGE"

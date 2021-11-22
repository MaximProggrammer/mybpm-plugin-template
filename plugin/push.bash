#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 118

set -e

../gradlew dist

docker build . -t "$(bash meta/image.bash)"

docker push "$(bash meta/image.bash)"

echo PUSHED IMAGE "$(bash meta/image.bash)"

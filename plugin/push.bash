#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 118

set -e

../gradlew dist

docker build . -t "$(bash lib/image.bash)"

docker push "$(bash lib/image.bash)"

echo PUSHED IMAGE "$(bash lib/image.bash)"

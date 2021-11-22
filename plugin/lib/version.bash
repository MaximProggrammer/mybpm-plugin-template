#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 113

exec cat ../../versions/version.txt

#!/usr/bin/env bash
cd "$(dirname "$0")" || exit 117

exec git rev-parse --abbrev-ref HEAD

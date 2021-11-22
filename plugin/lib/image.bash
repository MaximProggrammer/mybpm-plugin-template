#!/usr/bin/env bash
cd "$(dirname "$0")" || exit 115

###MODIFY replace template {PLUGIN_NAME.under}
exec echo "$(bash image-base.bash)-$(bash branch.bash):$(bash version.bash)"

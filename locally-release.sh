#!/usr/bin/env bash
set -e

function rollback
{
  mvn release:rollback
}

source ./sourceable-variables.sh
source ./sourceable-ssm-qtest-values.sh

# Validate the release
mvn clean package

# Now that we know it is working, we can skip the tests because they will be run many
# times, redundantly, otherwise. We can now roleback on release failure as well
trap rollback ERR

# Release
mvn release:prepare release:perform
# Push the most recent snapshot
mvn deploy

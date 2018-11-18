#!/usr/bin/env bash
set -e

function rollback
{
  mvn release:rollback
}
trap rollback ERR

source ./sourceable-variables.sh
source ./sourceable-ssm-qtest-values.sh

# We clean, push the most recent snapshot, update and tag then push the release
mvn clean deploy release:prepare release:perform

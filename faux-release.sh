#!/usr/bin/env bash
set -e

function rollback
{
  mvn release:rollback
}
trap rollback ERR

source ./sourceable-variables.sh
source ./sourceable-ssm-qtest-values.sh

mvn clean
mvn release:prepare
mvn release:perform

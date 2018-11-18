#!/usr/bin/env bash
set -e

function rollback
{
  mvn release:rollback
}
trap rollback ERR

mvn release:prepare
mvn release:perform

#!/usr/bin/env bash
set -e
source ./sourceable-variables.sh

function get_param
{
    aws ssm get-parameter --name "${@}" | jq -r '.Parameter.Value'
}


export QTEST_SUBDOMAIN="$(get_param '/qtest-client-library/integration-test/subdomain')"
export QTEST_USER="$(get_param '/qtest-client-library/integration-test/username')"
export QTEST_PASS="$(get_param '/qtest-client-library/integration-test/password')"
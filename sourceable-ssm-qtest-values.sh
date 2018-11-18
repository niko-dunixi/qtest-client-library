#!/usr/bin/env bash
set -e
source ./sourceable-variables.sh

# This function polls for the parameter in SSM. It pipes to JQ instead of
# using the query parameter, because it can unwrap the string with -r
function get_param
{
    aws ssm get-parameter --name "${@}" | jq -r '.Parameter.Value'
}


export QTEST_SUBDOMAIN="$(get_param '/qtest-client-library/integration-test/subdomain')"
export QTEST_USER="$(get_param '/qtest-client-library/integration-test/username')"
export QTEST_PASS="$(get_param '/qtest-client-library/integration-test/password')"
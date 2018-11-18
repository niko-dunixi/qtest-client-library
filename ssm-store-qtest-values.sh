#!/usr/bin/env bash
set -e
source ./sourceable-variables.sh

echo -n "Enter your QTest subdomain: "
read qtest_subdomain
echo -n "Enter your QTest username: "
read qtest_username
echo -n "Enter your QTest password: "
read -s qtest_password

printf "\nUploading values into AWS SSM\n"

aws ssm put-parameter \
  --name "/qtest-client-library/integration-test/subdomain" \
  --description "The subdomain that is used with qtest" \
  --type String \
  --value "${qtest_subdomain}" \
  --overwrite

aws ssm put-parameter \
  --name "/qtest-client-library/integration-test/username" \
  --description "The username that is used with qtest" \
  --type String \
  --value "${qtest_username}" \
  --overwrite

aws ssm put-parameter \
  --name "/qtest-client-library/integration-test/password" \
  --description "The password that is used with qtest" \
  --type String \
  --value "${qtest_password}" \
  --overwrite

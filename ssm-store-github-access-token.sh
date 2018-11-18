#!/usr/bin/env bash
set -e
source ./sourceable-variables.sh

echo -n "Enter your GitHub personal access token: "
read github_token

aws ssm put-parameter \
  --name "/qtest-client-library/github-personal-access-token" \
  --description "The token that allows the us to access the github" \
  --type String \
  --value "${github_token}" \
  --overwrite
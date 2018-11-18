#!/usr/bin/env bash
set -e

echo -n "Enter your GitHub personal access token: "
read github_token

aws --profile paulbaker ssm put-parameter \
  --name "/qtest-client-library/github-personal-access-token" \
  --description "The token that allows the us to access the github" \
  --type String \
  --value "${github_token}" \
  "${@}" # We add this to pass any extra values to aws-cli, eg --overwrite
#!/usr/bin/env bash
source ./sourceable-variables.sh
stack_name="${stack_base_name}-pipeline"
echo "Stack Name: ${stack_name}"

# Validate the cloudformation template
aws cloudformation validate-template --template-body "file://./cloudformation-template.yml"

# We take the timestamp before we even start pushing the stack
deploy_start_time="$(date -u +%Y-%m-%dT%H:%M:%S.000Z)"

# This function will print out the most recent failure in cloudformation for the stack
function describe_stack_failure_events
{
  # Rereshing token so we can get stacktrace, but also dumping to /dev/null so it's not on-screen noise
  aws-token-refresh > /dev/null 2>&1
  # Print out the FIRST error that occurs after our timestamp
  aws cloudformation describe-stack-events \
    --stack-name "${stack_name}" \
    --query "StackEvents[?ResourceStatus == 'CREATE_FAILED' && Timestamp >= '${deploy_start_time}'] | [-1]"
  exit 1
}

# `trap` will cause the `describe_stack_failure_events` method to be called if the
# stack fails to deploy
trap describe_stack_failure_events ERR

# Deploy the stack
aws cloudformation deploy \
  --template-file ./cloudformation-template.yml \
  --stack-name "${stack_name}" \
  --capabilities CAPABILITY_NAMED_IAM
#!/usr/bin/env bash
source ./sourceable-variables.sh
stack_name="${stack_base_name}-pipeline"
echo "Stack Name: ${stack_name}"

# Delete the stack
aws cloudformation delete-stack --stack-name "${stack_name}"

# Small stacks only need a couple seconds before you run a deploy again.
# Larger stacks will not comply.
sleep 5s
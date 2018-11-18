#!/usr/bin/env bash
# Define the profile
export AWS_PROFILE="${AWS_PROFILE:-paulbaker}"
is-command () {
  if hash "${1}" 2>/dev/null; then
    echo "true"
  else
    echo "false"
  fi
}
export stack_base_name="${stack_base_name:-qtest-client-library}"
export ip_relative_to_aws="$(curl -s "http://checkip.amazonaws.com")"
export onetime_debug_printout="${onetime_debug_printout:-0}"
if [[ "${onetime_debug_printout}" != "1" ]]; then
  export onetime_debug_printout="1"

  echo "-DEBUG INFO------------------------------"
  echo "Stack Base Name: ${stack_base_name}"
  echo "IP Relative to AWS: ${ip_relative_to_aws}"
  echo "AWS_PROFILE: ${AWS_PROFILE}"
  echo "Can run 'pip3': $(is-command pip3)"
  echo "Can run 'aws-cli': $(is-command aws)"
  echo "-----------------------------------------"
  echo ""
fi
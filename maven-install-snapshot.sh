#!/usr/bin/env bash
set -e
export GPG_TTY=$(tty)
export GPG_AGENT_INFO
if [ -d ~/.m2/repository/io/paulbaker ]; then
  rm -rfv ~/.m2/repository/io/paulbaker/*
fi
mvn clean install
tree ~/.m2/repository/io/paulbaker

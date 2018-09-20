#!/usr/bin/env bash
set -e
export GPG_TTY=$(tty)
export GPG_AGENT_INFO
mvn clean deploy
cd ~/.m2/repository/io/paulbaker
rm -rfv ./*

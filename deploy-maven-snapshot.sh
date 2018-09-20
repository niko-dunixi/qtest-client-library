#!/usr/bin/env bash
set -e
export GPG_TTY=$(tty)
export GPG_AGENT_INFO
./install-maven-snapshot.sh
mvn deploy

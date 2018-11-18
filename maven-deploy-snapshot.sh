#!/usr/bin/env bash
set -e
export GPG_TTY=$(tty)
export GPG_AGENT_INFO
./maven-install-snapshot.sh
mvn deploy

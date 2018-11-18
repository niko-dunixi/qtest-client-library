#!/usr/bin/env bash
set -e
if [ -d ~/.m2/repository/io/paulbaker ]; then
  rm -rfv ~/.m2/repository/io/paulbaker/*
fi
mvn clean install
tree ~/.m2/repository/io/paulbaker

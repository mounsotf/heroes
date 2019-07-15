#!/usr/bin/env sh

echo 'build docker images'
set -x
which docker-compose
pwd
docker ps
echo build images using-docker compose tool
./usr/local/bin/docker-compose -f /docker/docker-compose.yml build

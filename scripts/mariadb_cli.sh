#!/bin/bash
export $(grep -v '#.*' "`dirname $0`"/../.env | tr -d '\r' | xargs)
docker compose exec mariadb mariadb -p$MARIADB_ROOT_PASSWORD $MARIADB_DATABASE

#!/bin/bash

declare dockerComposeMainFile=docker-compose.yml
declare dockerComposeInfraFile=docker-compose-app.yml

function start_infra() {
    echo "Starting infra docker containers...."
    docker-compose -f ${dockerComposeInfraFile} up -d
    docker-compose -f ${dockerComposeInfraFile} logs -f
}

function stop_infra() {
    echo "Stopping infra docker containers...."
    docker compose -f ${dockerComposeInfraFile} stop
    docker compose -f ${dockerComposeInfraFile} rm -f
}

function start() {
    echo "Starting all docker containers...."
    docker compose -f ${dockerComposeInfraFile} -f ${dockerComposeMainFile} up --build -d
    docker compose -f ${dockerComposeInfraFile} -f ${dockerComposeMainFile} logs -f
}

function stop() {
    echo "Stopping all docker containers...."
    docker compose -f ${dockerComposeInfraFile} -f ${dockerComposeMainFile} stop
    docker compose -f ${dockerComposeInfraFile} -f ${dockerComposeMainFile} rm -f
}

function restart() {
    stop
    sleep 3
    start
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval "${action}"
#!/usr/bin/env bash
mvn clean && mvn liberty:dev -DskipTests=true

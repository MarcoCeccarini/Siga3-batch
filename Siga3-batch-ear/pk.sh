#!/usr/bin/env bash

PORTS=(9080 7777)

for PORT in "${PORTS[@]}"; do
    echo "Checking port $PORT..."

    PIDS=$(netstat -ano 2>/dev/null | grep ":$PORT" | awk '{print $5}' | sort -u)

    if [[ -n "$PIDS" ]]; then
        echo "Killing PID(s) on port $PORT: $PIDS"
        for PID in $PIDS; do
            taskkill //F //PID "$PID"
        done
    else
        echo "No process found on port $PORT"
    fi
done

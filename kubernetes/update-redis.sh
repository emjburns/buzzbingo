#!/usr/bin/env bash

kubectl delete -f spin/rs/redis-rs.yaml

sleep 10s

kubectl create -f spin/rs/redis-rs.yaml
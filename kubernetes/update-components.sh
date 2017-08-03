#!/usr/bin/env bash

kubectl delete -f spin/rs/ai-rs.yaml
kubectl delete -f spin/rs/ui-rs.yaml

sleep 10s

kubectl create -f spin/rs/ai-rs.yaml
kubectl create -f spin/rs/ui-rs.yaml
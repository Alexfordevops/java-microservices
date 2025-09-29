#!/bin/bash

# Start Minikube with 7800MB of memory
echo "Starting Minikube with 7800MB of memory..."
minikube start --memory=7800mb

# Wait for Minikube to be fully ready
echo "Waiting for Minikube to be ready..."

# Create the namespace for java-microservices
echo "Creating namespace default for java-microservices"
kubectl create namespace java-microservices

# Set the current context to the 'java-microservices' namespace
echo "Setting current context to 'java-microservices' namespace..."
kubectl config set-context --current --namespace=java-microservices

# User kubernetes docker
echo "Script execution complete."
echo "For Setting kubernetes docker type: eval $(minikube docker-env)"

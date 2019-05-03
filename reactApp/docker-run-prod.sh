#!/bin/bash
docker build -f Dockerfile-prod -t initial-client-prod .
echo "Running container..."
docker run -it -p 80:80 --rm initial-client-prod


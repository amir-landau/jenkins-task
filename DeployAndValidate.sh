#!/bin/bash

# Start the nginx and app containers
docker-compose up -d

# Check Application Availability
response=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:80/)

if [ $response -eq 200 ]
then
    echo "Request Successful"
else
    echo "Request Failed"
fi

# Cleanup Docker Compose Environment
docker-compose down

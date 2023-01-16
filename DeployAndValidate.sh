#!/bin/bash

# Start the nginx and app containers
docker-compose --no-ansi up -d

# Separating Output
printf "\n"

# Check Application Availability
response=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:80/)

if [ $response -eq 200 ]
then
    echo "Request Successful"
else
    echo "Request Failed"
fi

# Separating Output
printf "\n"

# Cleanup Docker Compose Environment
docker-compose --no-ansi down --rmi all
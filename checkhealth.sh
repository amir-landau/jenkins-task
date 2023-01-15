#!/bin/bash

response=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:80/)

if [ $response -eq 200 ]
then
    echo "Request Successful"
else
    echo "Request Failed"
fi

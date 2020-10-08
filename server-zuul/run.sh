#!/bin/sh
echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z server-eureka $EUREKASERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Starting Zuul Server"
echo "********************************************************"
java -jar /usr/local/server-zuul/server-zuul-0.0.1-SNAPSHOT.jar

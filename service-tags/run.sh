#!/bin/sh
echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z database $DATABASESERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z server-config $CONFIGSERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Starting Tags Service"
echo "********************************************************"
java -jar /usr/local/service-tags/service-tags-0.0.1-SNAPSHOT.jar

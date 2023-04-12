#!/bin/bash

# Create debug variable with the input of the first parameter
DEBUG=$1
ENVIRONMENT=$2

# Check if should run in debug mode
if [ $DEBUG = 'true' ]
then
    JVM_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005"
else
    JVM_OPTS=""
fi

if [-z $ENVIRONMENT ]
then
    PROFILE="dev"
else
    PROFILE=$ENVIRONMENT
fi

# Command that starts the spring boot application with custom JVM_OPTS
/usr/bin/java $JVM_OPTS -jar -Dspring.profiles.active=$PROFILE /usr/share/@project.build.finalName@/@project.build.finalName@.jar

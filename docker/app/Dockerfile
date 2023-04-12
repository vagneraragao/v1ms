FROM amazoncorretto:@docker.java.version@

## START DOCKERIZE ##
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION @docker.dockerize.version@
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz
## END DOCKERIZE ##

## START APP COMMANDS ##
# Copy shell scripts files to container root
COPY ./*.sh /

# Application specific steps - run entrypoint
ENV DEBUG="false"
ENV ENVIRONMENT="local"
ENTRYPOINT sh entrypoint.sh $DEBUG $ENVIRONMENT

ADD @project.build.finalName@.jar /usr/share/@project.build.finalName@/@project.build.finalName@.jar
## END APP COMMANDS ##

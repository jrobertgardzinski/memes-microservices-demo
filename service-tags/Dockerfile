FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/service-tags
ADD target/service-tags-0.0.1-SNAPSHOT.jar /usr/local/service-tags/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh

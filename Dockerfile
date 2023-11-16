FROM ubuntu:latest
MAINTAINER vignesh
RUN apt-get update && apt-get install -y wget
#create a dir foe maven in opt

#RUN cp /opt/files/* /usr/share/maven
# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME
FROM maven:3.8.6-openjdk-8 AS maven

COPY . .

RUN mvn install -X

COPY */MessengerService.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
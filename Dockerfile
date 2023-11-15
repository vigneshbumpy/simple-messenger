FROM ubuntu:latest
MAINTAINER vignesh
RUN apt-get update && apt-get install -y wget
ARG USER_HOME_DIR="/root"
#create a dir foe maven in opt
RUN mkdir /opt/maven
RUN mkdir /usr/share/maven
#RUN mkdir /usr/share/maven
#dwnl maven using link in tmp
RUN wget https://downloads.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz   -O /tmp/maven.tar.gz
#extract the file
RUN cd /tmp && tar xvf maven.tar.gz
#copy the file into opt/maven
RUN cp -R /tmp/apache-maven-3.8.4/* /usr/share/maven/
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

#dwnl zip and move to /opt/files
RUN mkdir /opt/files
RUN  wget <this is my zip file> -O /tmp/zip_file.zip
RUN cp /tmp/zip_file.zip /opt/files/


ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN mvn clean install -PmessengerApp
#RUN mvn clean install -Pdeployment


CMD ["mvn", "--version"]
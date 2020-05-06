FROM openjdk:8
MAINTAINER Livrair

# Loading the executable server inside the image
COPY integration_tests_cli ./integration_tests_cli

# Mounting a local directory to support file loading in the shell
VOLUME /host

# Configure the link to the external partners
ENV server_host=dd_system
ENV server_port=8080

# Copy the startup script
COPY ./resources/start-dd.sh .
RUN ["chmod", "u+x", "./start-dd.sh"]

# Starting the script to launch tests
ENTRYPOINT ["./start-dd.sh"]

USER root
RUN apt-get update
RUN apt-get -y --no-install-recommends install maven

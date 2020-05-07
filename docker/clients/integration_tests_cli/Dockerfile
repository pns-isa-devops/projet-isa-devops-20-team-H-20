FROM openjdk:8
MAINTAINER Livrair

# Loading the executable server inside the image
COPY ./drone-delivery-client-integration_tests_cli-1.0-SNAPSHOT-jar-with-dependencies.jar ./drone-delivery-client-integration_tests_cli.jar

# Mounting a local directory to support file loading in the shell
VOLUME /host

# Running the client using the starting shell script
ENTRYPOINT ["java", "-jar", "./drone-delivery-client-integration_tests_cli.jar"]

# Default arguments to be forwarded to the entry point
CMD ["localhost", "8080"]
# Docker environment for the DD System

## Designing the images

### The "External Partners" image (.Net)

The external partner binary is a .Net executable file (`server.exe`), with the following assumptions:

  - It relies on the `mono` environment,
  - when started, it will expose the services on port 9090,
  - the entry point of the image is the `server.exe` binary,
  - it must be run using the `/standalone` option to support daemon-like execution

[See the Dockerfile](partners/Dockerfile)

To build the docker launch the following command `./build.sh`

### The "Drone Delivery" system image (J2E)

The internal implementation of the DD system will relies on the following assumptions:

  - it will be deployed on TomEE+, with Java 8,
  - we keep the default port for TomEE (_i.e._ 8080),
  - the external partner configuration must be declared when starting the container,
  - The system is considered _healthy_ as soon as TomEE is up and running

[See the Dockerfile](dd/Dockerfile)

To build the docker launch the following command `./build.sh`

### The Client image (Java)

This image relies on an OpenJDK implementation (Java 8), and executes the JAR client (a single assembly) with the right option.

See the Dockerfiles in every client directories

To build the docker launch the following command `./build.sh` in very client directories

## Composing the final system

The three images can now be used independently, as standalone components. But one can combine the three images with the right configuration to define a valid deployment for the CoD system.

This is implemented thanks to a [`docker-compose.yml`](docker-compose.yml) descriptor. 

We basically use the composition descriptor to expose the different ports (internally with `expose` directives, and externally with `ports` directives). We set the different environment variables to ensure that the J2E systems can communicate with the .Net one. One must notice that inside a composition, each container can be identified on the network thanks to its container name.

The client can start even if the J2E system is not up. However, for the sake of demonstration, we declare a precedence rule, stating that the client will only start after the J2E system is considered `healthy`.

To run the system, one can ask`docker-compose` to start in detached mode:

`docker-compose up -d`

The client is started inside the assembly, and already running. Thus, one can attach and then detach the current context to the container. To attach the current context to a container, the `docker attach` command do the job. When attached, one can detach using the `^P ^Q` key combination.

`docker attach dd_client`

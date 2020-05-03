#!/bin/bash

# Running the image as
#  - removing the container after exit,
#  - detached (-d),
#  - binding localhost:8080 to container:8083
docker run -v jenkins:/var/jenkins_home/ --name my-jenkins -p 8083:8080 -p 50000:50000 livrair/jenkins

# to stop: docker stop ID
# to start a new shell in the container: docker exec -it ID bash
# to attach to the container: docker attach ID (^P ^Q to detach)

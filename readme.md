#PS8 - Drone Delivery

### Jenkins.sh

The jenkins.sh script will take a moment because it needs to install lots of dependencies

### client.sh

On a windows computer please launch client.sh with powershell or cmd and not git bash because git bash seems not working with docker attach

### Repository size

We tried to strip the jenkins volume but it ended up with some failures. So we push the entire volume (without .groovy, .java;..). The repository is about 800 mo because there is also the artifactory volume

### How to launch integration tests

Unfortunately, we were not able to launch the integration tests when the whole system is dockerized (during the execution of the mvn test command in the integration CLI's container, a 403 http error is thrown and the CLI does not manage to connect successfully to the backend's container). However, they run fine outside of the containers. To run them in that way, you can run the tomee server in the backend (mvn tomee:run in the WebServices module), then the external service (mono server.exe), and them run the tests in the integration CLI.

To launch tomee :
`cd j2e`
`mvn clean install`
`cd WebServices`
`mvn tomee:run`

To launch exertnal service : 
`cd MappingServiceExterne`
`mono server.exe`

To launch integration tests:
`cd clients/integration_tests_cli`
`mvn clean package`

### How to launch build plan

To launch a build plan you need to configure an ssh key and link it to your githubg account

- Oopen a terminal and type `docker exec -it dd_jenkins /bin/bash`
- `ssh-keygen -t rsa` then enter then enter then enter
- `cat /root/.ssh/id_rsa.pub`
- Copy the key
- Go to https://github.com/pns-isa-devops/projet-isa-devops-20-team-H-20/settings/keys
  - Or Go to the project > Settings > Deploy Keys
- Click on "Add deploy key"
- Paste your key
- Go to Jenkins > Identifiants > System > 	Identifiants globaux (illimité) >  Ajouter des identifiants
- In Type field choose SSH Username with private key
- Set ID and description (github-ssh-damien for both for example)
- Set your GitHub username
- Add the key
- Click OK
- Go to Jenkins (http://localhost:8083) > drone-delivery-j2e > Configurer
- Under Gestion de code source, under Credentials select the new credentials
- It failed to connect because Jenkins seems to bug a this point, so choose "Aucun" again it works
- Click on Save
- Launch a build

###Team H

Doan Quang Minh

Focas Florian

Montoya Damien

Rigaut François

Todesco Gabin

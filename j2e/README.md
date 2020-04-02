# Drone Delivery by Livrair

### Authors
* Doan Quang Minh
* Focas Florian
* Montoya Damien
* Rigaut François
* Todesco Gabin

### Technology
[Java 8](https://www.java.com/fr/download/)

[Artifactory is used to manage modules](https://jfrog.com/artifactory/)

[Arquillian is used for tests](http://arquillian.org/)

[Cukespace is used to link Arquillian and Cucumber](https://github.com/cukespace/cukespace)

[Jenkins is used to manage builds](https://jenkins.io/)

### How to use
To launch the project tomcat server, go to the root project folder (j2e), then use this command line `mvn clean install`. Then, go to the "WebServices" folder/module and use `mvn tomee:run`.

To launch the tests get to the root project then use this command line `mvn test`. When launching test the better is to have the external mapping service also launched

### Artifactory Installation

To install artifactory you need docker. Run the following command :

`docker run -p 8081:8081 -p 8082:8082 --name my-artifactory docker.bintray.io/jfrog/artifactory-oss`

After the first installation :
- `admin` and `password` to log in
- `mysuperpassword` as the new password
- skip the SET BASE URL
- skip PROXY
- check maven to create repositories
- click on the left under "Artifactory" > "Artifacts"
  - Set me up
  - copy the settings to the pom.xml under `<!-- Artifactory configuration parameters -->`
  - edit the settings.xml with your address
  - François docker toolbox path: `192.168.99.101`
  - Docker path: `localhost`

To perform operations related to the artifactory by using `mvn` command. i.e:
 - `mvn install -s ..\settings.xml` 
 - `mvn deploy -s ..\settings.xml`

### Jenkins installation

To install Jenkins you need docker. Run the following commands :

- `docker pull jenkins/jenkins`
- `docker run --name my-jenkins -p 8080:8080 -p 50000:50000 jenkins/jenkins`

- Then go to localhost:8080 (or ip if under docker toolbox). 
- Enter the password given in the comand prompt. 
- Go to Jenkins main page > Administrer Jenkins > Configuration globale des outils
  - Under Maven click "Ajouter Maven", maven 3.6.3 in name and install from Apache
  - Save
- If repository is private :
  - Return to the command prompt
  - `docker exec -it my-jenkins /bin/bash`
  - `ssh-keygen` then enter then enter
  - `cat /var/jenkins_home/.ssh/id_rsa.pub`
  - copy the key
  - go to your github project > settings > deploy key > add your key
  - In the left menu in jenkins click on Identifiants > System > Identifiants globaux > Ajouter des identifiants
  - Type choose ssh username
  - Choose Global
  - Put ssh key
- Create a new free-style job.
- Choose your github repository under "Gestion de code source".
- get to the github project code page and get the ssh connection
- Build -> choose maven version name previously entered
- Build -> Invoquer les cibles Maven de haut niveau > POM > /var/jenkins_home/workspace/Drone-Delivery-Snapshot-1.0.0/j2e/pom.xml
- Then click save

- Launch a build and voila !

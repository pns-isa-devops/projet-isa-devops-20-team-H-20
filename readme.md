#PS8 - Drone Delivery

### Comment lancer les tests d'intégration

Malheureusement, nous ne sommes pas parvenus à lancer les tests d'intégration lorsque tout le système est dockerizé (lors de l'exécution de mvn test dans le container du client d'intégration, une erreur http 403 intervient et le client n'arrive pas à se connecter à tomee) cependant en version CLI la connexion fonctionne très bien. Pour les lancer vous pouvez lancer le serveur tomee puis le service externe et ensuite lancer les tests.

Pour lancer tomee :

`cd j2e`
`mvn clean install`
`cd WebServices`
`mvn tomee:run`

Pour lancer le service externe : 
`cd MappingServiceExterne`
`mono server.exe`

Pour lancer les tests d'intégration :
`cd clients/integration_tests_cli`
`mvn clean package`

### Comment lancer un plan de build

Pour lancer un plan de build il faut configurer une clé SSH pour que votre compte github puisse so connecter au repository.

- Ouvrir un terminal et écrire `docker exec -it dd_jenkins /bin/bash`
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
- Go to Jenkins > drone-delivery-j2e > Configurer
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

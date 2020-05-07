#PS8 - Drone Delivery

###Team H
Doan Quang Minh

Focas Florian

Montoya Damien

Rigaut François

Todesco Gabin

### How to launch integration tests

Comme expliqué dans le rapport, nous ne sommes pas parvenus à lancer les tests d'intégration lorsque tout le système est dockerizé. Pour les lancer vous pouvez lancer le serveur tomee puis le service externe et ensuite lancer les tests.

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


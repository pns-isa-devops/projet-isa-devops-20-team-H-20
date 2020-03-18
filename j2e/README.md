# Drone Delivery by Livrair

### Authors
* Doan Quang Minh
* Focas Florian
* Montoya Damien
* Rigaut François
* Todesco Gabin

### Technology
[Java 8](https://www.java.com/fr/download/)
[Arquillian is used for tests](http://arquillian.org/)

### How to use
To launch the project get to the root project then use this command line `mvn clean package` then `mvn tomee:run`

To launch the tests get to the root project then use this command line `mvn test`
### Artifactory Notes
Please modify artifactory configurations finding in `pom.xml` and `settings.xml` to match to artifactory used on your computer before using.

To perform operations related to the artifactory by using `mvn` command. i.e:
 - `mvn install -s ..\settings.xml` 
 - `mvn deploy -s ..\settings.xml`

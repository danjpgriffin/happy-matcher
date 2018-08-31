#Coding Exercise (Filtering)

##Requirements
* JVM capable of running Kotlin
* docker
* running mysql image

To start the mysql image, please run the following command before running the build or starting the application

`docker run -e MYSQL_ROOT_PASSWORD=root -p3306:3306 mariadb:bionic`

It can take a while for the mysql instance to start. please wait until you can see

`mysqld: ready for connections.`

.. in the message log on screen

##Building

You may build the project using:

`./gradlew clean build`

##Running

The application can be started by running:

`java -jar build/libs/happy-matcher.jar`
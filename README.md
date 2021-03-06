# Coding Exercise (Filtering)

## Requirements
* JVM capable of running Kotlin
* docker
* running mysql image

To start the mysql image, please run the following command before running the build or starting the application

`docker run -e MYSQL_ROOT_PASSWORD=root -p3306:3306 mariadb:bionic`

It can take a while for the mysql instance to start. please wait until you can see

`mysqld: ready for connections.`

.. in the message log on screen

## Building

You may build the project using:

`./gradlew clean build`

## Running

The application can be started by running:

`java -jar build/libs/happy-matcher.jar`

## Supported features
Only hasPhoto, inContact and distance are supported at this time
The user is assumed to be in Birmingham

The application runs on port 9090. Here are some example calls:

`http://localhost:9090/findmatches`

`http://localhost:9090/findmatches?inContact=true`

`http://localhost:9090/findmatches?hasPhoto=false`

`http://localhost:9090/findmatches?distance=100`

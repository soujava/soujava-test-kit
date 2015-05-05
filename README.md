Description
===========

![SouJava](https://soujavablog.files.wordpress.com/2011/01/logo-soujava-top.jpg)
This project is an SouJava Team effort to get together the Gatling documentation into something real, updated, tested and that is ready-made for use in a CI environment.

This project will get you going with your load testing in few minutes, you will be able to target the following applications:
 * REST API testing and general pages.
 * Websockets
 * JMS

![Gatling](https://soujavablog.files.wordpress.com/2015/05/gatling-logo.png)
Gatling is open-source, and yet a very powerful Test tool used for doing performance tests using a lightweight DSL written in Scala. Gatling will teach you to "treating your performance tests as production code".
Gatling is no tool for QA Manual testers, instead for SDET or Developers that are doing work to performance test their either for work or home projects.
Gatling integrates Jenkins and this is great because you run your performance tests daily as you develop your app.

![Maven](https://soujavablog.files.wordpress.com/2015/05/maven.png)
This project integrates with Maven with the help of the maven-plugin.
Just because Maven is the default build for Java, and everyone knows how to use it.
Some people may say: "Why not use SBT if this is a Scala project, or maybe why not Gradle?"
The answer is: "Simplicity is the key, less is better the more, when less means easy to use and easy to understand".
I find SBT really interesting, but we have to be honest, not everyone knows it, and Gradle is doing a nice work, but is kind of complicated.

Requirements
============
[Maven 2+](https://maven.apache.org/download.cgi)
[OpenJDK 7+](http://openjdk.java.net/install/)
[Jenkins 1.6+](https://jenkins-ci.org/) *In case you want to configure the CI project.


Usage
=====

Initial Startup (No Jenkins)
---------------

Make sure you have download Maven, then configure it using following configurations:

Add this variables in the .bashrc or bash_profile file.
You should also add the bin directory to your $PATH, to have all your executable programs available from any directory

```
# bashrc or bash_profile
export M2_HOME=/xyz/apache-maven-3.0.5
export M2=$M2_HOME/bin
export MAVEN_OPTS="-Xmx1048m -Xms256m -XX:MaxPermSize=312M"
export PATH=$M2:$PATH
```

Install OpenJDK
```
$ sudo apt-get install openjdk-7-jdk
```

Initial Startup (With Jenkins)
---------------
[Install Maven via Jenkins configure](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s06.html)
[Install Java via Jenkins configure](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s05.html)


Demo Targets
-------------
Use one of this targets to get to know about the load testing:
1) java vertex https://vertx-simple-json-endpoint.herokuapp.com/hello
2) play scala https://sheltered-tor-1754.herokuapp.com/hello
3) nodejs 0.10.x -> https://desolate-harbor-2201.herokuapp.com/hello
4) golang 1.3 https://limitless-basin-5531.herokuapp.com/hello



Running Tests for REST APIS
============


With Jenkins
---------------
```
sudo mkdir /var/lib/jenkins/jobs/SouJava_Scala_Gatling_Generic_Test
sudo cp jenkins/config.xml /var/lib/jenkins/jobs/SouJava_Scala_Gatling_Generic_Test/
```
[Reload jenkins configurations from disk](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s02.html)

Open the job and run o/

Without Jenkins
---------------

Execute the following command and then check for the results o/
```
gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX
```


Running Tests for Websocket
============
TODO: Work in progress



Running Tests for JMS
============
TODO: Work in progress



## Author
Thomas Modeneis
[StackOverflow](https://careers.stackoverflow.com/thomasmodeneis)
[LinkedIn](https://uk.linkedin.com/in/thomasmodeneis)

License
=======

This module is licensed under the MIT license.

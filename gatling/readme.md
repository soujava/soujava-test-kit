![Gatling](https://soujavablog.files.wordpress.com/2015/05/gatling-logo.png)

Gatling is open-source, and yet a very powerful Test tool used for doing performance and load tests. Gatling will teach you about "treating your performance tests as production code" using a lightweight DSL written in Scala.

Gatling is the perfect tool for SDETs that are performing on a performance/load test role or Developers that are doing work to performance/load test apps either for work or home projects.

Gatling integrates Jenkins and this is great because you run your performance tests daily as you develop your app.


Requirements for Gatling 2.2.3
============

Have installed:

* [Maven =3.0.5](https://maven.apache.org/download.cgi)
```
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.tar.gz                           2013-05-24 13:57  4.9M
[TXT] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.tar.gz.asc                       2013-05-24 13:57  195
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.tar.gz.md5                       2013-05-24 13:57   32
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.tar.gz.sha1                      2013-05-24 13:57   40
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.zip                              2013-05-24 13:57  5.6M
[TXT] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.zip.asc                          2013-05-24 13:57  195
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.zip.md5                          2013-05-24 13:57   32
[   ] https://archive.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.zip.sha1                         2013-05-24 13:57   40
```

* [OpenJDK 8+](http://openjdk.java.net/install/)
* [Jenkins 1.6+](https://jenkins-ci.org/) *In case you want to configure the CI project.


Usage
=====

Initial Startup (No Jenkins)
---------------

Make sure you have download Maven, then configure it using following steps:

Add this variables in the .bashrc or bash_profile.
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
$ sudo apt-get install openjdk-8-jdk
```

Initial Startup (With Jenkins)
---------------
* [Install Maven via Jenkins](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s06.html)
* [Install Java via Jenkins](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s05.html)
* Make sure to configure jenkins wiith to download and install Oracle JDK 8 build 121, and name it as: JDK8u121
* [Install Jenkins Gatling Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Gatling+Plugin)
* ![Gatling Plugin](https://wiki.jenkins-ci.org/download/attachments/65669112/dashboard.png)


Demo Targets
-------------
Use one of this targets may help you to do some basic load testing:

* Java vertex https://vertx-simple-json-endpoint.herokuapp.com/hello
* Scala Play https://sheltered-tor-1754.herokuapp.com/hello
* NodeJS 0.10.x -> https://desolate-harbor-2201.herokuapp.com/hello
* Go 1.3 https://limitless-basin-5531.herokuapp.com/hello



Running Tests for REST APIS
============


With Jenkins
---------------

# Configure
* Add the generic test to the jenkins jobs folder:
```
sudo mkdir /var/lib/jenkins/jobs/SouJava_Scala_Gatling_Generic_Test
sudo cp jenkins/config.xml /var/lib/jenkins/jobs/SouJava_Scala_Gatling_Generic_Test/
```

* Configure the permissions for jenkins user to be able to see it:
`sudo chown -R jenkins:jenkins /var/lib/jenkins/jobs/SouJava_Scala_Gatling_Generic_Test/`

* Check its all correct
`$ ls -l
 total 8
 -rw-r--r-- 1 jenkins jenkins 8013 mrt 23 13:07 config.xml`

* Restart jenkins 
`sudo service jenkins restart`

OR Reload jenkins configurations from disk.

Steps: Click Manage Jenkins and then on icon: 
* `Reload Configuration from Disk`
* `Discard all the loaded data in memory and reload everything from file system. Useful when you modified config files directly on disk.`

Open the job and run o/


## I just Forgot my jenkins user password :( 
Change to root user: `sudo su -`
Copy the password: `xclip -sel clip < /var/lib/jenkins/secrets/initialAdminPassword`
Login with admin user and press `ctrl + v` on password input box.

Without Jenkins
---------------

Execute the following command and then check for the results o/
```
mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX
```


outputs
```

mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building scala-gatling-bootstrap-mvn 2.1.1
[INFO] ------------------------------------------------------------------------
Simulation soujava.BenchmarkGeneric started...

================================================================================
2015-05-05 19:24:38                                           0s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[                                                                          ]  0%
          waiting: 100    / active: 0      / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=0      KO=0     )

================================================================================

(...)

================================================================================
2015-05-05 19:25:38                                          60s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[#############################################                             ] 61%
          waiting: 39     / active: 0      / done:61
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=11646  KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=11646  KO=0     )
================================================================================

Simulation finished
[INFO] [05/05/2015 19:25:38.463] [GatlingSystem-akka.actor.default-dispatcher-11] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [2] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/FileDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/FileDataWriter#1860774045] was not delivered. [3] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [4] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/FileDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/FileDataWriter#1860774045] was not delivered. [5] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [6] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/FileDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/FileDataWriter#1860774045] was not delivered. [7] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.465] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [8] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.466] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/FileDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/FileDataWriter#1860774045] was not delivered. [9] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/05/2015 19:25:38.466] [GatlingSystem-akka.actor.default-dispatcher-12] [akka://GatlingSystem/user/ConsoleDataWriter] Message [io.gatling.core.result.writer.RequestMessage] from Actor[akka://GatlingSystem/deadLetters] to Actor[akka://GatlingSystem/user/ConsoleDataWriter#1640666221] was not delivered. [10] dead letters encountered, no more dead letters will be logged. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
Parsing log file(s)...
Parsing log file(s) done
Generating reports...

================================================================================
---- Global Information --------------------------------------------------------
> request count                                      11646 (OK=11646  KO=0     )
> min response time                                    133 (OK=133    KO=-     )
> max response time                                   8822 (OK=8822   KO=-     )
> mean response time                                   156 (OK=156    KO=-     )
> std deviation                                        155 (OK=155    KO=-     )
> response time 50th percentile                        150 (OK=150    KO=-     )
> response time 75th percentile                        155 (OK=155    KO=-     )
> mean requests/sec                                193.777 (OK=193.777 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         11637 (100%)
> 800 ms < t < 1200 ms                                   0 (  0%)
> t > 1200 ms                                            9 (  0%)
> failed                                                 0 (  0%)
================================================================================

Reports generated in 2s.
Please open the following file: /opt/scala/scala-gatling-bootstrap-mvn/target/gatling/results/benchmarkgeneric-1430846678254/index.html
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3:12.019s
[INFO] Finished at: Tue May 05 19:25:41 CEST 2015
[INFO] Final Memory: 8M/240M
[INFO] ------------------------------------------------------------------------
```

Running generic benchmark feed race_for_speed.csv:
Java Vertex VS Scala VS Nodejs VS Golang
```
mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGenericFeed -DNUM_THREADS=1 -DRAMP_TIME=1 -DDURATION=60 -DTARGET=race_for_speed.csv -DENDPOINT_NAME=vertexVSscalaVSnodejsVSgolang
```


Running Tests for Websocket
============
TODO: Work in progress
[#20](https://github.com/soujava/soujava-test-kit/issues/20)


Running Tests for JMS
============
TODO: Work in progress
[#19](https://github.com/soujava/soujava-test-kit/issues/19)




Running tests for SOAP XML WSDL Testing 
======================================
# Maven command line

`mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkSOAPGeneric -DNUM_THREADS=1 -DRAMP_TIME=100 -DDURATION=10 -DTARGET=http://localhost:809-DENDPOINT=/esbWebServiceEndpoint -DENDPOINT_NAME=ESB_WS -DTEST_FILE="src/test/resources/request-bodies/benchmarksoapgeneric_01.xml" -DSTATUS_CHECK=200 -DREGEX_CHECK="200 OK" -DTARGET1_SWITCH="90.0"`


REPORTS
========

If you haven't read this section yet, chances are that you are not being able open the generate the HTML report, 
don't you worry, we got you covered.

Explanation: Jenkins introduced a security change that breaks Gatling reports display.
You sadly have to disable it to get them back, see how-to:

Open Jenkins CFG file:
`sudo vi /etc/default/jenkins`

Add the following configuration:

Before:
```
# arguments to pass to java
JAVA_ARGS="-Djava.awt.headless=true"
```

After:
```
JAVA_ARGS="-Djava.awt.headless=true -Dhudson.model.DirectoryBrowserSupport.CSP=\"sandbox allow-scripts; style-src 'unsafe-inline' *;script-src 'unsafe-inline' *;\""
```

Then restart jenkins in order to have the configurations enabled:
`sudo service jenkins restart`

Gatling 2.1.7 - JAVA 7 - JDK 7
===============================

This section is for people interested in having Gatling 2.1.7 with Java JDK 7.
Because Gatling latest changed the minimum requirement from JDK 7 to JDK 8, many users may ask, does this still works with JDK 7 ? Answer is Yes, sure.
In order to have it running with JDK 7 is simple, open the pom.xml and uncomment lines below text `<!-- GATLING VERSION - JDK 7 -->`

Complete snippet:
```
		<gatling.version>2.1.7</gatling.version>
		<gatling-maven-plugin.version>2.1.1</gatling-maven-plugin.version>
		<akka-testkit_2.10.version>2.3.8</akka-testkit_2.10.version>
```

Then just run mvn install and ignore the failure if is related to, as this just means the default goal execute could not run without arguments.

```
[ERROR] More than 1 simulation to run, need to specify one, or enable runMultipleSimulations
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2:34.884s
[INFO] Finished at: Wed Jan 25 10:47:27 CET 2017
[INFO] Final Memory: 13M/284M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal io.gatling:gatling-maven-plugin:2.2.1:execute (default) on project gatling: Gatling failed. More than 1 simulation to run, need to specify one, or enable runMultipleSimulations -> [Help 1]
```

Don't worry, this is not a actual error with the cfg. This is just due to the fact that no tests have been selected to run, to execute the example test just run: `mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX`

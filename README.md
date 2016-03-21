Description
===========

![SouJava](https://soujavablog.files.wordpress.com/2011/01/logo-soujava-top.jpg)

This project is an SouJava Team effort to get together the Gatling documentation into something real, updated, tested and that is ready-made for use in a CI environment.

This project will get you going with your load testing in few minutes, you will be able to target the following technologies:

 * REST API
 * Static HTML pages.
 * WebSockets
 * JMS

![Gatling](https://soujavablog.files.wordpress.com/2015/05/gatling-logo.png)

Gatling is open-source, and yet a very powerful Test tool used for doing performance and load tests. Gatling will teach you about "treating your performance tests as production code" using a lightweight DSL written in Scala.

Gatling is the perfect tool for SDETs that are performing on a performance/load test role or Developers that are doing work to performance/load test apps either for work or home projects.

Gatling integrates Jenkins and this is great because you run your performance tests daily as you develop your app.


![Maven](https://soujavablog.files.wordpress.com/2015/05/maven.png)

This project build with Maven and the maven-plugin.

Some people may say: "Why not use SBT if this is a Scala project, or maybe why not Gradle?"

I find SBT and Gradle really interesting, but we have to be honest, Maven is the default build for Java, and everyone knows how to use it.


![Jenkins](https://soujavablog.files.wordpress.com/2015/05/jenkinslogo.png)

Jenkins is an open source continuous integration tool written in Java. The project was forked from Hudson after a dispute with Oracle.

Jenkins provides continuous integration services for software development.

It is a server-based system running in a servlet container such as Apache Tomcat.

It supports SCM tools including AccuRev, CVS, Subversion, Git, Mercurial, Perforce, Clearcase and RTC,
and can execute Apache Ant and Apache Maven based projects as well as arbitrary shell scripts and Windows batch commands.

The primary developer of Jenkins is Kohsuke Kawaguchi. Released under the MIT License, [Jenkins Wiki](https://wiki.jenkins-ci.org/display/JENKINS/Home) is free software.

Requirements
============

Have installed:

* [Maven 2+](https://maven.apache.org/download.cgi)
* [OpenJDK 7+](http://openjdk.java.net/install/)
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
$ sudo apt-get install openjdk-7-jdk
```

Initial Startup (With Jenkins)
---------------
* [Install Maven via Jenkins](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s06.html)
* [Install Java via Jenkins](https://www.safaribooksonline.com/library/view/jenkins-the-definitive/9781449311155/ch04s05.html)
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
mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX
```


outputs
```

$ mvn gatling:execute -Dgatling.simulationClass=soujava.BenchmarkGeneric -DNUM_THREADS=100 -DRAMP_TIME=100 -DDURATION=60 -DTARGET=http://vertx-simple-json-endpoint.herokuapp.com -DENDPOINT=/hello -DENDPOINT_NAME=HELLO_WORLD_VERTEX
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


================================================================================
2015-05-05 19:24:43                                           5s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[----                                                                      ]  0%
          waiting: 95     / active: 5      / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=0      KO=0     )

================================================================================


================================================================================
2015-05-05 19:24:48                                          10s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[--------                                                                  ]  0%
          waiting: 90     / active: 10     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=62     KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=62     KO=0     )
================================================================================


================================================================================
2015-05-05 19:24:53                                          15s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[------------                                                              ]  0%
          waiting: 84     / active: 16     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=476    KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=476    KO=0     )
================================================================================


================================================================================
2015-05-05 19:24:58                                          20s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[----------------                                                          ]  0%
          waiting: 79     / active: 21     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=1064   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=1064   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:03                                          25s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[-------------------                                                       ]  0%
          waiting: 75     / active: 25     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=1805   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=1805   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:08                                          30s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[-----------------------                                                   ]  0%
          waiting: 69     / active: 31     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=2721   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=2721   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:13                                          35s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[---------------------------                                               ]  0%
          waiting: 64     / active: 36     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=3807   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=3807   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:18                                          40s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[------------------------------                                            ]  0%
          waiting: 60     / active: 40     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=5061   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=5061   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:23                                          45s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[----------------------------------                                        ]  0%
          waiting: 55     / active: 45     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=6458   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=6458   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:28                                          50s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[--------------------------------------                                    ]  0%
          waiting: 49     / active: 51     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=8041   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=8041   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:33                                          55s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[------------------------------------------                                ]  0%
          waiting: 44     / active: 56     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=9766   KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=9766   KO=0     )
================================================================================


================================================================================
2015-05-05 19:25:38                                          60s elapsed
---- HELLO_WORLD_VERTEX --------------------------------------------------------
[---------------------------------------------                             ]  0%
          waiting: 40     / active: 60     / done:0
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=11633  KO=0     )
> HELLO_WORLD_VERTEX                                       (OK=11633  KO=0     )
================================================================================


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


Running Tests for Websocket
============
TODO: Work in progress



Running Tests for JMS
============
TODO: Work in progress



Running tests for BenchmarkSOAPGeneric
======================================



## Author
Thomas Modeneis
[StackOverflow](https://careers.stackoverflow.com/thomasmodeneis)
[LinkedIn](https://uk.linkedin.com/in/thomasmodeneis)

License
=======

This module is licensed under the MIT license.

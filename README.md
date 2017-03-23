Description
===========

![SouJava](https://soujavablog.files.wordpress.com/2011/01/logo-soujava-top.jpg)

This project is an SouJava Team effort to get together many Automation test technologies like Gatling, TestNG, Mocks, etc, into something real, updated, tested and that is ready-made for use in a CI environment.

This project will get you going with your Automation testing in few minutes, you will be able to test the following technologies:

 * REST API
 * SOAP WebServices
 * Static HTML pages
 * WebSockets
 * JMS 

![Gatling](https://soujavablog.files.wordpress.com/2015/05/gatling-logo.png)

Gatling is open-source, and yet a very powerful Test tool used for doing performance and load tests. Gatling will teach you about "treating your performance tests as production code" using a lightweight DSL written in Scala.

Gatling is the perfect tool for SDETs that are performing on a performance/load test role or Developers that are doing work to performance/load test apps either for work or home projects.

Gatling integrates Jenkins and this is great because you run your performance tests daily as you develop your app.


![Apache Camel](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Apache-camel-logo.png/150px-Apache-camel-logo.png)
(Used mainly for the webservice project)

Apache Camel a open-source integration framework based on known Enterprise Integration Patterns, specially useful to create ESB
Camel uses URIs to work directly with any kind of Transport or messaging model such as HTTP, 
ActiveMQ, JMS, JBI, SCA, MINA or CXF, as well as pluggable Components and Data Format options. 

![Camel Design](http://cdn.infoq.com/statics_s2_20160301-0105u7/resource/articles/eai-with-apache-camel/en/resources/fig2large.jpg)
Apache Camel is a small library with minimal dependencies for easy embedding in any Java application. 
Apache Camel lets you work with the same API regardless which kind of Transport is used - so learn the API once and you can interact with all the Components provided out-of-box.


![Apache CXF](https://bigdatanerd.files.wordpress.com/2012/03/box_cxf.jpg)
(Used mainly for the webservice project)

Apache CXF is an open source services framework. CXF helps you build and develop services using frontend programming APIs, like JAX-WS and JAX-RS. These services can speak a variety of protocols such as SOAP, XML/HTTP, RESTful HTTP, or CORBA and work over a variety of transports such as HTTP, JMS or JBI.


![Spring](https://thenewboston.com/photos/users/287/original/763608fe4b09e466e0398762d27396a8.png)
(Testing packages Under development)
The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications.


![Netty](http://normanmaurer.me/presentations/2013-wjax-netty/images/netty_logo.png)
(Testing packages Under development)
Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients.


![Velocity](https://velocity.apache.org/engine/1.4/images/logo.gif)
(Testing packages Under development)
Velocity is a project of the Apache Software Foundation, Velocity Engine is the actual templating engine which does all the work. 
If you came here because you heard about Velocity somewhere on the web, this is probably the right place to start.

![Maven](https://soujavablog.files.wordpress.com/2015/05/maven.png)

This project should be build with Maven +3.0.5 and the maven-plugin.
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

Some people may say: "Why not use SBT if this is a Scala project, or maybe why not Gradle?"

I find SBT and Gradle really interesting, but we have to be honest, Maven is the default build for Java, and everyone knows how to use it.


![Jenkins](https://soujavablog.files.wordpress.com/2015/05/jenkinslogo.png)

Jenkins is an open source continuous integration tool written in Java. The project was forked from Hudson after a dispute with Oracle.

Jenkins provides continuous integration services for software development.

It is a server-based system running in a servlet container such as Apache Tomcat.

It supports SCM tools including AccuRev, CVS, Subversion, Git, Mercurial, Perforce, Clearcase and RTC,
and can execute Apache Ant and Apache Maven based projects as well as arbitrary shell scripts and Windows batch commands.

The primary developer of Jenkins is Kohsuke Kawaguchi. Released under the MIT License, [Jenkins Wiki](https://wiki.jenkins-ci.org/display/JENKINS/Home) is free software.

## Installing Jenkins on Ubuntu
![Jenkins](https://wiki.jenkins-ci.org/download/attachments/327683/JENKINS?version=1&modificationDate=1302750804000)

`sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'`
`sudo apt-get update`
`sudo apt-get install jenkins`

## Installing Jenkins in other distros:
[Installing Jenkins](https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins)

## Configure Jenkins to run with alternative port

* Edit Jenkins startup script to check for a alternative 8085 port:
* Edit the contents of the function `do_start()`:
`sudo vi /etc/init.d/jenkins`

* From:
`check_tcp_port "http" "$HTTP_PORT" "8080" "$HTTP_HOST" "0.0.0.0" || return 2`

* To: 
`check_tcp_port "http" "$HTTP_PORT" "8085" "$HTTP_HOST" "0.0.0.0" || return 2`

* Edit Jenkins default config to bind to the new alternative 8085 port:
`sudo vi /etc/default/jenkins`

* From: 
`HTTP_PORT=8080`

* To: 
`HTTP_PORT=8085`


## Author
Thomas Modeneis
[StackOverflow](https://careers.stackoverflow.com/thomasmodeneis)
[LinkedIn](https://uk.linkedin.com/in/thomasmodeneis)

License
=======

This module is licensed under the MIT license.

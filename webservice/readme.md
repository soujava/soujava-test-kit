# Camel - CXF - Netty - Basic Webservice


This module was created to be used as part of the Scala Gatling Bootstrap project.

This includes 1 test case that are evidencing the webservice starts up and stop, correctly binding to a port and accepting post messages.
The message will be send to a netty processor that will execute the transformation. The transformation will just get the message and respond with a ok.

# Building
=========================

To build this project use

```mvn clean install```

# Running
===========

To run this project from within Maven use

```mvn camel:run```


# Testing
============

To execute tests

```mvn test```

# Running with OSGi JBOSS Fuse
================================

To run this project with FUSE or any other OSGi Karaf container, after starting the container `./fuse`

(make sure you did clean install before running this)

Add the features url:
```
features:addurl mvn:br.org.soujava/webservice/1.0/xml/features
```

Then install it:
```
features:install webservice
```
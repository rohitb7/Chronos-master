#!/bin/bash
mvn package
cp target/chronos-0.0.1-SNAPSHOT.war ~/apache-tomcat-7.0.68/webapps/chronos.war

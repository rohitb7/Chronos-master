mvn clean package
copy target\chronos-0.0.1-SNAPSHOT.war target\chronos.war
copy target\chronos.war "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps"

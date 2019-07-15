FROM tomcat:latest
 # ENV CATALINA_BASE /usr/local/tomcat
 # ENV CATALINA_HOME /usr/local/tomcat
 # ENV CATALINA_TMPDIR usr/local/tomcat/temp
 # ENV JRE_HOME /usr
 # ENV CLASSPATH /usr/local/tomcat/bin/bootstrap.jar
 ENV CATALINA_OPTS -server -Djava.awt.headless=true -Xms384M -Xmx1024M
 RUN set JAVA_OPTS=%JAVA_OPTS% -Xms1024m  -Xmx1024m
 RUN echo 'export CATALINA_OPTS="-Xms1024M -Xmx1024M"' > /usr/local/tomcat/bin/setenv.sh
 USER root
 COPY server.xml /usr/local/tomcat/conf/server.xml
 # COPY dist/heroes /usr/local/tomcat/webapps/heroes
COPY heroes.war /usr/local/tomcat/webapps/

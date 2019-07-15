## Automation Script

to automate web application

compile:
cd to the project folder

1. mvn clean package
(to download oracle drivers follow the url below):
url: https://blogs.oracle.com/dev2dev/get-oracle-jdbc-drivers-and-ucp-from-oracle-maven-repository-without-ides
use : mvn -s settings.xml package

2. mvn package
 #to allow run tests
3.mvn clean install -DskipTests=false
#  run specific test class
4. mvn test -Dtest=RequestLifeCycleWithDetailsTestSuite,RequestLifeCycleTestSuite

report:
1. mvn surefire-report:report
2. mvn surefire-report:report site -DgenerateReports=false

Execute:
1. (jar with all dependencies)
java -jar target/com.crystaltracketl.java-1.0-SNAPSHOT.jar com.crystaltracketl.main.App

## install as a service
1. go to \winsw
2. follow the script in command screen
   1. windows-service-crystaltrack-wrapper install
   2. windows-service-crystaltrack-wrapper uninstall
3. for more details, refer to the link below :
https://www.dontpanicblog.co.uk/2017/01/01/spring-boot-as-a-windows-service/
3. 

##NetStat to monitor the oracle connections
netstat -aobn 10 | find "192.168.0.34" | find "1521"

##GIT section
1. git add .
2. git commit -m"message here"
3. git push
4. git tag (to list all tags)
5. git tag -a v1.0 -m "my version 1.0" (to create new tag)
6. git tag -a v1.4 -m "my version 1.0" to display tag information
7. to push a tag : git push origin v1.1

# to create new local branch:
1. git branch development
2. git fect to update git references
# to Delete a local and a remote GIT branch
1. to delete local branch:
git branch -d branch_name
git branch -D branch_name (-D to force deletion)
# Application Class Data Sharing

This app is used to demonstrate Application Class Data Sharing. It's computationally complex enough so that the difference when using CDS is visible and it does not make any HTTP requests. 

It uses [CTP Java Sync](https://github.com/commercetools/commercetools-sync-java)

The source of the commands below:
https://blog.codefx.org/java/application-class-data-sharing/

**Prerequisites:**  
JDK 11

**Steps to reproduce:**
1. `./gradlew clean build`
1. `java -Xshare:dump`
1. `java -Xshare:off -Xlog:class+load:file=cds.log -jar build/libs/app.jar` (JDK CDS with unified logging)
1. `java -XX:+UseAppCDS -XX:DumpLoadedClassList=classes.lst -jar build/libs/app.jar`
1. `java -XX:+UseAppCDS -Xshare:dump -XX:SharedClassListFile=classes.lst -XX:SharedArchiveFile=app-cds.jsa --class-path build/libs/app.jar`
1. `java -XX:+UseAppCDS -Xshare:on -XX:SharedArchiveFile=app-cds.jsa -jar build/libs/app.jar`

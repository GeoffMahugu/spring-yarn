# What you'll build

You'll build a simple Hadoop YARN application with Spring Hadoop and Spring Boot.

# Build

Simple run a gradle build command.

```text
$ ./gradlew clean build
```

# Run the application

Now that you've successfully compiled and packaged your application, it's time to do the fun part and execute it on a Hadoop YARN.

Below listing shows files after a succesfull gradle build.

```text
$ ls -lt build/libs/
-rw-r--r-- 1 hadoop hadoop 35975001 Feb  2 17:39 yarn-boot-simple-container-0.1.0.jar
-rw-r--r-- 1 hadoop hadoop 35973937 Feb  2 17:39 yarn-boot-simple-client-0.1.0.jar
-rw-r--r-- 1 hadoop hadoop 35973840 Feb  2 17:39 yarn-boot-simple-appmaster-0.1.0.jar
```

Simply run your executable client jar.

```text
$ java -jar build/libs/yarn-boot-simple-client-0.1.0.jar
```

Using a Resource Manager UI you can see status of an application.

![Resource Manager UI](https://raw.github.com/spring-projects/spring-hadoop-samples/master/boot/yarn-boot-simple/rm-ui.png)

To find Hadoop's application logs, do a little find within a configured userlogs directory.

```text
$ find hadoop/logs/userlogs/|grep std
hadoop/logs/userlogs/application_1391506550167_0001/container_1391506550167_0001_01_000002/Container.stdout
hadoop/logs/userlogs/application_1391506550167_0001/container_1391506550167_0001_01_000002/Container.stderr
hadoop/logs/userlogs/application_1391506550167_0001/container_1391506550167_0001_01_000001/Appmaster.stdout
hadoop/logs/userlogs/application_1391506550167_0001/container_1391506550167_0001_01_000001/Appmaster.stderr
```

Grep logging output from a `HelloPojo` class.

```text
$ grep HelloPojo hadoop/logs/userlogs/application_1391506550167_0001/container_1391506550167_0001_01_000002/Container.stdout
[2014-02-02 17:40:38,314] boot - 11944  INFO [main] --- HelloPojo: Hello from HelloPojo
[2014-02-02 17:40:38,315] boot - 11944  INFO [main] --- HelloPojo: About to list from hdfs root content
[2014-02-02 17:40:41,134] boot - 11944  INFO [main] --- HelloPojo: FileStatus{path=hdfs://localhost:8020/; isDirectory=true; modification_time=1390823919636; access_time=0; owner=root; group=supergroup; permission=rwxr-xr-x; isSymlink=false}
[2014-02-02 17:40:41,135] boot - 11944  INFO [main] --- HelloPojo: FileStatus{path=hdfs://localhost:8020/app; isDirectory=true; modification_time=1391203430490; access_time=0; owner=jvalkealahti; group=supergroup; permission=rwxr-xr-x; isSymlink=false}
```



# Spring Hahoop 
This documentation contains a multi-project configuration for a spring for apache hadoop application.

# Prerequisites
i) Java installed >= 8 

ii) Basic knowledge on Spring boot.

iii) Basics in Docker

iv) Have Hadoop Cluster: [Docker Setup Docs](README-DOCKER.md)

### Basics on Hadoop

Hadoop has 3 components Yarn, HDFS and MapReduce.

[Yarn](https://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-site/YARN.html) :- Yarn is a resource manager which is responsible for resource allocation, monitoring and Job scheduling on the nodes. It enables for commutation between the daemon sets running the applications(Has a unix like terminal interaction with the application).

[HDFS](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html) :- is a distributed file system.

[MapReduce](https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html) :- is a framework for writing applications that process vast amount of data in parallel and multiple nodes in a reliable, fault-tolerant manner.


### Basics on Spring Yarn

Ref: https://docs.spring.io/spring-hadoop/docs/current/reference/html/springandhadoop-yarn.html

Spring Yarn is the configuration for setting up a yarn application abstructing the low level complexities of a Yarn application.

A spring yarn application consists of 3 components:

**YarnClient** :- Responsible for issuing the jobs(application) to YarnResourceManager, listing and killing of the jobs.
            - Defines launch context(commands to start the container) for YarnAppmaster & YarnContainer.

**YarnAppmaster** :- Communicates with YarnResourceManager to start & stop YarnContainer
               - Defines the launch context for YarnContainer

**YarnContainer** :- Wrapper for the application(Worker).

### Project Structure
This setup follows spring-yarn implementation of a multi project setup where we will create the 3 applications as modules that can be built seperately.

#### Things to Note:
1. I'm using gradle to build the project.
2. Single yarn configuration in ``src/main/resources/application.yml``
3. Using an older version of spring-boot(1.2.3.RELEASE) upgrading to the latest version breaks the application.
4. The configurations are pointed to the cluster hadoop configuration.


root-project

    - /src/main (contains the main application)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - /src/main/java/hello/appmaster (contains appmaster module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - /src/main/java/hello/client (contains client module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - /src/main/java/hello/container (contains the container module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)


### Build Project

To build the project run(in root of project):

``./gradlew clean build``

This will build the main application along with the 3 yarn applications in ``/build`` folder.

### Submit Job to Hadoop
This section will utilise the 3 componets we created(YarnClient, YarnAppmaster, YarnContainer) in order to issue a job to the cluster.

To submit the application to yarn resource manager, run the client application.(In root folder)

``java -jar build/libs/yarn-boot-simple-client-0.1.0.jar``

### Resources:

1. [Hadoop Structure Explained](https://www.youtube.com/watch?v=ZFbkNY6Xn94&ab_channel=COSOI)
2. [Hadoop Full Port Mapping](https://www.stefaanlippens.net/hadoop-3-default-ports.html)
3. [Hadoop on Docker](https://www.youtube.com/watch?v=dLTI2HN9Ejg&ab_channel=NextGenLearning)
4. [Intro to Spring Yarn](https://spring.io/blog/2013/09/10/introducing-the-spring-yarn-framework-for-developing-apache-hadoop-yarn-applications)
5. [Spring Yarn Setup: - Painless build](https://www.youtube.com/watch?v=qlvX7_r9aUA&ab_channel=SpringDeveloper)
6. [Spring Yarn Full Documentation](https://docs.spring.io/spring-hadoop/docs/current/reference/html/springandhadoop-yarn.html)
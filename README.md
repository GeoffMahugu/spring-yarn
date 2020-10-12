# Spring Hahoop 
This documentation contains a multi-project configuration for a spring yarn application.

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
            - Defines launch context(commands to start the container) for YarnAppmaster & YarnContainer 
            
**YarnAppmaster** :- Communicates with YarnResourceManager to start & stop YarnContainer
               - Defines the launch context for YarnContainer

**YarnContainer** :- Wrapper for the application(Worker). 

### Project Structure
This setup follows spring-yarn implementation of a multi project setup where we will create the 3 applications as modules that can be built seperately.

#### Things to Note:
1. I'm using maven as the project builder. (gradle will fail no longer supports v2)
2. Subsequent Yarn configurations are found in resources folder. eg for YarnClient (yarn_client/src/main/resources/application.yml)
3. Using an older version of spring-boot(1.2.3.RELEASE) upgrading to the latest version breaks the application.


root-project

    - /src (contains the main application)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - yarn_appmaster (contains appmaster module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - yarn_client (contains client module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)
        
    - yarn_container (contains the container module)
        - /src/main/java (application logic)
        - /src/main/resources/application.yml (yarn configuration)


### Run Project
To run the project(in root folder):

``mvn spring-boot:run``

### Build Project

To build the project run(in root of project):

``mvn package``

This will build the main application along with the 3 yarn applications.

## Integration Testing
In this step we are going to test the integration to yarn starting with FsShell then proceed to issuing a job to the YarnResourceManager.

### 1. FsShell Test

To test the configuration for FsShell(Utility for uploading and downloading files from HDFS)

The code is located in this folder. (root-folder/src/main/java/demo/DemoApplication)

Run the java file (DemoApplication) which will display the contents at the root of your HDFS. 


### 2. Issue Job to Hadoop
This section will utilise the 3 componets we created(YarnClient, YarnAppmaster, YarnContainer) in order to issue a job to the cluster.

You will first need to build the project.

``mvn package``

or repackage after first build:

``sudo mvn clean package spring-boot:repackage``

Run the client application.(The client application contains launch context for both YarnAppmaster & YarnContainer)

``cd yar_client/target/``

``java -jar yarn_client-0.0.1-SNAPSHOT.jar``

Go to this url to confirm the job has been issued(assuming you have a local Hadoop application running):

``http://localhost:8088``

### Resources:

1. [Hadoop Structure Explained](https://www.youtube.com/watch?v=ZFbkNY6Xn94&ab_channel=COSOI)
2. [Hadoop Full Port Mapping](https://www.stefaanlippens.net/hadoop-3-default-ports.html)
3. [Hadoop on Docker](https://www.youtube.com/watch?v=dLTI2HN9Ejg&ab_channel=NextGenLearning)
4. [Intro to Spring Yarn](https://spring.io/blog/2013/09/10/introducing-the-spring-yarn-framework-for-developing-apache-hadoop-yarn-applications)
5. [Spring Yarn Setup: - Painless build](https://www.youtube.com/watch?v=qlvX7_r9aUA&ab_channel=SpringDeveloper)
6. [Spring Yarn Full Documentation](https://docs.spring.io/spring-hadoop/docs/current/reference/html/springandhadoop-yarn.html)
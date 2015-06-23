# Overview
This is a *sample microservice* application build with [Spring Boot](http://projects.spring.io/spring-boot/) and [Docker](https://www.docker.com/)

Review the steps below to learn how it was created and how to create your own:

# Step 1: Set up your environment

Before installing your dev tools, you will need **Node.js >= v0.10** and **git**

If you need to upgrade or install Node.js, the easiest way is to use an installer for your platform. Download the .msi for Windows or .pkg for Mac from the [NodeJS website](https://nodejs.org/download/).

Next, you need the following tools to install [Yeoman](http://yeoman.io/) and the Spring Generator by running:

```
npm install yo generator-spring -g
```

# Step 2: Scaffold out your microservice project

Create a directory for your microservice:

```
mkdir sample-microservice && cd $_
```

Then run Yeoman generator for Spring:

```
yo spring                                            
```

Here is the sample interaction with the generator:

```

.............DD88888888888888888,............
...........:888888888888888888888,...........
..........+88888888888888888888888+..........
.........,8888888888888888888888888..........
.........888888888888...888888888888.........
.......,88888887..D88...88Z..88888888,.......
.......8888888,...888...88D...=8888888.......
......D888888,..$8888...88887...8888888......
.....Z888888$..I88888...88888:..88888888,....
....D8888888...888888...88888D..,88888888....
....88888888,..888888..,888888...88888888....
....88888888,..8888888$888888D..,88888888....
....88888888I..Z8888888888888+..888888888....
.....Z8888888...O888888888888..,88888888.....
......88888888...,88888888D...,88888888......
.......88888888=.....?I+.....I88888888.......
.......,88888888D7.........ZD88888888,.......
.........888888888888888888888888888.........
.........,8888888888888888888888888..........
..........+88888888888888888888888+..........
...........,888888888888888888888:...........
.............DD888888888888888DD.............

Welcome to the Spring Boot Generator


[?] (1/6) What version of Spring Boot would you like to use? 1.2.4.RELEASE
[?] (2/6) What is your default package name? com.vspglobal
[?] (3/6) What is the base name of app? sample-microservice
[?] (4/6) select your starters: Actuator
[?] (5/6) Would you like to use Spock? Yes
[?] (6/6) Would you like to add the Gradle wrapper? Yes
   create build.gradle
   create src/main/java/com/vspglobal/Application.java
   
````
   
   
# Step 3: Create a REST endpoint

Run the Yeoman generator for Spring REST:

```
yo spring:rest
```

Here is the sample interaction with the generator: 

```
[?] (1/4) Package name: com.vspglobal
[?] (2/4) Name for your representation class: Foo
[?] (3/4) Name for your controller: FooController
[?] (4/4) Path to Controller: /foo
   create src/main/java/com/vspglobal/controller/FooController.java
   create src/main/java/com/vspglobal/domain/Foo.java
```


# Step 4: Run it locally

Start the app by running:

```
 gradle run
``` 

Try it out:

```
curl http://localhost:8080/foo
```


# Step 5: Create a Docker image

Create a dockerfile:

```
  FROM java:8
  MAINTAINER Casey Lee email: casele@vsp.com
  ADD sample-microservice-0.1.0.jar app.jar
  ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

Update gradle to build the docker image:

```
buildscript {
    ...
    dependencies {
        ...
        classpath('se.transmode.gradle:gradle-docker:1.2')
    }
}

group = 'vspglobal'

...
apply plugin: 'docker'

task docker(type: Docker, dependsOn: build) {
  push = false
  applicationName = jar.baseName
  dockerfile = file('src/main/docker/Dockerfile')
  doFirst {
    copy {
      from jar
      into stageDir
    }
  }
}
```

Then run:

```
gradle docker
```
 

Run it with:

```
docker run -p 8080:8080 -t vspglobal/sample-microservice
```



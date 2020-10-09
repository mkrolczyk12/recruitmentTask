# Recruitment task
Backend Developer Assignment

## Table of contents
- [Technologies and dependencies](#technologies-and-dependencies)
- [Requirements](#requirements)
- [Build instruction](#build-instruction)
- [Database instruction](#database-instruction)
- [Status](#status)
- [Contact](#contact)

## Technologies and dependencies
* Java - version 11
* Spring 5
* Spring boot - version 2.3.0.RELEASE
* Maven
* SQL - H2 (local profile)
* Hibernate - version 6.1.5.Final
* Flywaydb
* spring-boot-starter: data-jpa, web, starter-test

## Requirements
Git, Java Development Kit 11 (JDK11) <br />

## Build instruction
There are two ways of interaction with project: <br />
### Use of a ready-made solution prepared by me <br />
To use project, follow these steps: <br />
1. Open page: https://recruitmenttask.herokuapp.com/notes <br />
- If the application has not been used for some time, you need to wait for the application to build (usually it takes about 20 seconds) <br />
2. Use the application by using ready endpoints available at the link: https://www.getpostman.com/collections/ac816262f12afdfbc33f <br />
3. (Optional) - If you want make your own http request:
- Download Postman from official page: https://www.postman.com/downloads/ <br />
- Open Postman: <br />
a) Load ready endpoints by following this instruction: https://learning.postman.com/docs/getting-started/importing-and-exporting-data/ <br />
The prepared json data file is called 'RecruitmentTaskWeb.postman_collection.json' and is located in cloned project <br />
b) After successful loading, a file with application endpoints should be available for use.
### Configuring the project yourself <br />
1. Open terminal and clone the project from github repository:
```
$ git clone https://github.com/mkrolczyk12/recruitmentTask.git
```
2. Write following commands into console:
```
$ .\mvnw clean
$ .\mvnw install
```
In result you should receive application .jar file. <br />
3. Open second terminal and type below commands:
```
$ cd [direct_path_to_the_previously_built_.jar_file]
$ java -jar [name_of_the_jar_file]
```
4. After loading application is ready to use. <br />
- Spring application uses port 8080 by default. <br />
- Interact with application using link: http://localhost:8080/ <br />
- You can also use Postman for making requests. I have also prepared a ready list of endpoints for localhost: <br />
a) Available online: https://www.getpostman.com/collections/4495f497622ce37df451 <br />
b) To load in Postman: load file named 'RecruitmentTaskLocalHost.postman_collection.json' located in cloned project. <br />

## Database instruction
Application uses in-memory database in local profile. <br />
There is no need to do anything, just follow the build instruction and the database should work. <br />
If the app was built locally, there should be available a link to database admin panel: localhost:8080/h2-console <br />
The panel allows to view the current database data. To log in type: <br />
user name: admin <br />
password: admin

## Status

_completed_

## Contact

Created by @mkrolczyk12 - feel free to contact me!

- Phone: (48) 503 699 962
- E-mail: m.krolczyk66@gmail.com

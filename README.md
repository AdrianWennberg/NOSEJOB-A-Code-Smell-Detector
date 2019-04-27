# NOSEJOB-A-Code-Smell-Detector
The 4 student team called The Coding Rangers' version of the software engineering assignment titled NOSEJOB: A Code Smell Detector

## Description

This detector is written mainly using Java with a bit of web stuff in there. The spec got was fairly loose, and we got to be creative in how we designed out detector. Our design document has bore details, but here's the gist of it. We use the Spring Boot library for the web server, and the Java Parser library to parse Java source files. Using these tools we lat the user upload a Java source file or a whole Java project as a zip file and we will analyze it for seven different code smells.

## Try it out!

The project will be up and running at <http://thecodingrangers.netsoc.com> for the foreseeable future. However, if you want to run it locally, here is a how to: 

0. Make sure to have Java and maven installed (tested with Java 11 and maven 3.6.1)
1. Clone the repository
2. Open a terminal inside the /nosejob/ folder
3. > mvn spring-boot:run

This will automatically download all the needed dependencies using maven and then run the project. By default, it will listen on port 4202, so to test it out, open your web browser of choice and go to: <http://localhost:4202>

## Images

Here's a screen shot of the working program as it now stands. This will probably be updated in the next 24 hours:
![Image of the work (in progress)](https://i.ibb.co/GWqh5wx/59352536-333931623976870-3800886576234364928-n.png)


# Selenium with Java Automation.

Utilising Selenium Webdriver to automate and Compare Temperature in degrees obtained from Accuweather and OpenWeather.


## Documentation
This project features a both UI & API automationa using Selenium with Java.

**What Powers this project:**
* Java 
* Selenium Webdriver
* Maven | maven-archtype-quickstart Artifact id 
* TestNG
* Google HTTP Client

  
## Features

**The framework has followig features:**
* Page Object Model.
* Maven pom.xml for dependencies.
* TestNG annotation for controlled execution of tests with required setup and teardown. testng.xml for driving the cases as per the use cases.
* Google HTTP Client for sending out API requests.
* Resuable Components: 
  1. Properties file to configure url, browsers, url, city etc.
  2. Setup class to initialise browser drivers.
  3. Utilities
      1. Listeners class for performing actions on events related to method run(for eg: OnTestSuccess, OntestFailure). This feature here is used to generate Extent Reports.
      
* Continuos Testing with Jenkins Pipeline and GitHub.
* Shareable reports in test-output folder.

  
## To Run this at your end

**All the dependencies are present in pom.xml file.It will take care of all the packages and plugins.
Jenkinsfile has necessary stages. Follow below Steps to run:**
1. Create a Pipeline style Jenkins build:
2. Go to Pipeline Section.
3.  _If using 'Pipescript from SCM' then specify git repo url  under Repository URL and in Jenkinsfile comment git command from "Test" stage._
    _If Using Pipeline Script then uncomment git command from "Test" stage to source the repo._
4. Specify Jenkinsfile under Script Path.
5. Save and Build. Execution should start.

**If you load project files in your local machine and want to run from your IDE(Let's say Eclipse):**
1. Open Projects from File System and Browse the whole project
2. Install Java, Maven , TestNG from Eclipse.
3. Run testng1.xml as TestNG Project OR you can use mvn commands from command line.

  
## Appendix

[Selenium WebDriver](https://www.selenium.dev/documentation/)

  
## Feedback

If you have any feedback, please reach out to me at simplyrohan93@gmail.com

  

# UI-Automation-TestNG-Java
# Objective: 
Developed a sample framework that supports to develop automation test scripts for functional testing.

# Libraries used:
1. Java
2. Selenium
3. TestNG
4. Extentreports
4. Maven

# Steps to start:
1. Clone / Download the project into your local.
2. Open the Command prompt and navigate to project location.
3. Execute the following Maven command's
    - mvn clean :- To clean the maven repo.
    - mvn install :- To install the maven requirements.
    - mvn test :- To execute the test scenarios.


# UI application details: 
Sample demo application created inside the project (Path - /test/resources/demo_application/Home.html)
1. Copy pest the path of Home.html in any browser address bar and press Enter. 
2. Enter any email address (valid regex) in the email field.
3. Enter any data in password field and click on Login button.
4. Do exploratory testing with this demo app.


# Approach: 
1. Implemented modular driven approach.
2. Used page object model design pattern.
3. Maintained low level and high level logs.
4. Implemented FileUtil, RandomUtil, Screenshot libraries. 
5. Test data maintained in properties files.
6. Browser and environment are parameterized from xml suits.
7. Maintained groups for all test scripts.
8. Used TestNG library for assertions & reporting.
9. Added screenshots with emailable-report.
10. Added Extentreports with screenshots.

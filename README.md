# Launch Interceptor Program
#### Generates a boolean signal which determines if an interceptor should be launched or not.

##### A project by group 17 for the course DD2480 Software Engineering Fundamentals.

The Launch Interceptor Program called Decide(), contains of 15 conditions (LIC) that will be either true or false based on the immediate situation and its tracking input variables. Together, the results of the 15 conditions will form a vector called Conditions Met Vector (CMV). The CMV will together with the given input matrix Logical Connector Matrix (LCM), decide which of these 15 conditions must be considered jointly for the launch. The result of the CMV and LCM will be stored in the Preliminary Unlocking Matrix (PUM). Given a Preliminary Unlocking Vector (PUV), the program Decide() will compare the values in the PUM with the PUV and form the corresponding values in the Final Unlocking Vector (FUV). If all values in FUV is true, the program will generate the launch-unlock signal.

# Tutorial
#### Required software
* Java 11
* Maven
#### Build
* Run the script XXXX
#### Run the program
The program can be executed by following these steps:
* Edit the parameter values in XXXX.java
* Run Decide.java
#### Run Test Cases
The program has 3 main test cases that tests the full functionality of the program. The program also has test cases for each 
class.
* To run the three main tests cases, run XXXX.java
* Class specific tests can be runned by executing DecideTest.java, IOHandlerTest.java
or UtilityTest.java.



## Contributions
We are dividing the contributions in 5 categories, how much code the person has made, how many pull-request the preson has reviewed,
how much the person participated in discussions, documentation and overall contribution. All these criteria are graded between 0 and 10.

Edevin Ardö:
* Code 10
* Pull-requests 10
* Discussion 10
* Documentation
* Overall contribution 10

Marcus Jonsson Ewerbring:
* Code 8
* Pull-requests
* Discussion 
* Documentation 10
* Overall contribution

Johanna Iivanainen:
* Code 8
* Pull-requests
* Discussion 
* Documentation 10
* Overall contribution

George Rezkalla:
* Code 8
* Pull-requests
* Discussion 
* Documentation
* Overall contribution

## TO DO BEFORE WE COMMIT TO REPO:
* Create a test case for the function we have created.
* Test the old test cases and check them.
* Create a branch that includes the number of the issue. For instance git checkout -b issue/2.
* Describe in the commit what you have created, modified or fixed in the commmit.
* Well commented, according to JavaDocs standard
* It exists tests(verify that the test work) for the implemented code
* No unnecessary code is added (code that is not used)
* Make sure that the code is correctly implemented
## Issue naming convention
`prefix/#issueNR description-of-commit`

###### Prefixes
`feat`
`fix`
`test`
`refactor`
`doc`

###### Example
`feat/#21 added IOHandler `

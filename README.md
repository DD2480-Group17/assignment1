# Launch Interceptor Program
#### Generates a boolean signal which determines if an interceptor should be launched or not.

##### A project by group 17 for the course DD2480 Software Engineering Fundamentals.

The Launch Interceptor Program called Decide(), contains of 15 conditions (LIC) that will be either true or false based on the immediate situation and its tracking input variables. Together, the results of the 15 conditions will form a vector called Conditions Met Vector (CMV). The CMV will together with the given input matrix Logical Connector Matrix (LCM), decide which of these 15 conditions must be considered jointly for the launch. The result of the CMV and LCM will be stored in the Preliminary Unlocking Matrix (PUM). Given a Preliminary Unlocking Vector (PUV), the program Decide() will compare the values in the PUM with the PUV and form the corresponding values in the Final Unlocking Vector (FUV). If all values in FUV is true, the program will generate the launch-unlock signal.

# Tutorial
#### Required software
* Java 11
* Maven
* JUnit (Jupiter 5.0 or later)

#### Run and build the program
The program can be executed in two ways, by entering custom parameters or three predefined test cases.
* (Custom parameters) Edit the parameter values in XXXX.java and run the program with ` mvn -q exec:java -Dexec.mainClass="Decide" -Dexec.args="1"`
and use TODO as argument.
* (Predefined test cases) Run and build the program with: 
` mvn -q exec:java -Dexec.mainClass="Decide" -Dexec.args="1"` The following arguments
can be used, 1, 2 or 3 to decide which test should be executed. 1 = test1, 2 = test2 and 3 = test3
 
#### Run Test Cases
The program also has test cases for each class.
* The tests can be executed by the following command ` mvn test`


## Contributions
We are proud over our implementation of the program and that we used Travis for continues integration. We have learnt a lot
during the lab on how a git project should be structured, by using issues, pull requests and testing.
 
In this section it is specified what each person contributed to the project with.

Edevin Ardö:


Marcus Jonsson Ewerbring:
* Code, implemented 3 LIC and test cases, refactored code
* Pull requests, review several pull requests
* Documentation, README and code documentation

Johanna Iivanainen:


George Rezkalla:


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

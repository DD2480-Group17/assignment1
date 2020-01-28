# Launch Interceptor Program
#### Generates a boolean signal which determines if an interceptor should be launched or not.

##### A project by group 17 for the course DD2480 Software Engineering Fundamentals.

The Launch Interceptor Program called Decide(), consists of of 15 conditions (LIC) that will be either true or false based on the immediate situation and its tracking input variables. Together, the results of the 15 conditions will form a vector called Conditions Met Vector (CMV). The CMV will together with the given input matrix Logical Connector Matrix (LCM), decide which of these 15 conditions must be considered jointly for the launch. The result of the CMV and LCM will be stored in the Preliminary Unlocking Matrix (PUM). Given a Preliminary Unlocking Vector (PUV), the program Decide() will compare the values in the PUM with the PUV and form the corresponding values in the Final Unlocking Vector (FUV). If all values in FUV is true, the program will generate the launch-unlock signal.

## Files structure
The structure of the program is illustrated bellow:
```bash
.
├── README.md
├── pom.xml
├── travis.yml
├── src
   ├── main
   │   └── java
   │       ├── Decide.java
   │       ├── IOHandler.java
   │       ├── Parameters.java
   │       ├── SmallestEnclosingCircle.java
   │       └── Utility.java
   └── test
       └── java
           ├── DecideTest.java
           ├── IOHandlerTest.java
           ├── TestCases.java
           └── UtilityTest.java
```
The main folder contains all classes used in the project, where `Decide` class contains all LIC, CMV, PUV, FUV and launch. The IOHandler is used to parse data
and return an object of type `Decide` for the program to use. `Parameters` is a class that stores the the parameters that are needed by the methods in `Decide` class. `SmallestEnclosingCircle` is a support
method for some LIC. `Utility` contains functions related to math that several classes use.

The test folder contains all JUnit tests for the implemented classes in the main folder.

The main method is located in Decide.java 
## Tutorial
#### Required software
* Java 11
* Maven
* JUnit (Jupiter 5.0 or later)

#### Run and build the program
The program can be executed in two ways, by entering custom parameters or three predefined test cases.
* (Custom parameters) On the terminal, first, go to the top directory of the project where the pom.xml file exists. Then, run and build the program with:` mvn -q exec:java -Dexec.mainClass="Decide" -Dexec.args=<file name>`. The argument `<file name>` should be replaced by the filepath of a file containing the wanted parameters.

* (Predefined test cases) On the terminal, first, go to the top directory of the project where the pom.xml file exists. Then, run and build the program with:
` mvn -q exec:java -Dexec.mainClass="Decide" -Dexec.args=<argument>`. To decide which test should be executed from test1, test2 or test3, the following arguments should be used in place of `<argument>`: `"1"`, `"2"` or `"3"`, respectively.

#### Run Test Cases
The program also has test cases for each class.
* On the terminal, first, go to the top directory of the project where the pom.xml file exists. Then, the tests can be executed by the following command ` mvn test`.

## Open Source Material
For this program, we have used a free software from [https://www.nayuki.io/page/smallest-enclosing-circle](https://www.nayuki.io/page/smallest-enclosing-circle) to calculate the smallest enclosing circle for a given set of points. The license for the software is given from GNU Lesser General Public License v3.0+. We have used it for our Launch Interceptor Conditions 1, 8 and 12. A detailed description of the algorithm that are used in the software can be found here: https://www.nayuki.io/res/smallest-enclosing-circle/computational-geometry-lecture-6.pdf.

## Contributions
We are proud over our implementation of the program and that we used Travis for continuous integration (CI). We have learnt a lot during the lab on how a git project should be structured, by using issues, specific prefix for our commits, pull requests and testing.

In this section it is specified what each person contributed to the project with.

Edvin Ardö:


Marcus Jonsson Ewerbring:
* Code, implemented 3 LIC and test cases, refactored code
* Pull requests, reviewed several pull requests
* Documentation and code documentation

Johanna Iivanainen:
* Code, implemented 3 LIC and test cases, refactored code
* Pull requests, reviewed several pull requests
* Documentation and code documentation

George Rezkalla:
* Code, implemented 4 LIC, calcCMV, launch methods, test cases, refactored code, and contributed to the implementation of calcFUV.
* Pull requests, reviewed several pull requests.
* Documentation and code documentation.

## Contribution policy: TO DO BEFORE WE COMMIT TO REPO
* Create a test case for the function you have created.
* Test the old test cases and check them locally.
* Create a branch that includes the number of the issue using issue naming convention, like this: `git checkout -b issue/#issueNr`, where `#issueNr` is replaced by the actual issue number. For instance, `git checkout -b issue/2`, where `#issueNr` = 2.
* Describe in the commit message what you have created, modified or fixed in the commit using the commit message convention (see [Commit message convention](#commit-message-convention) below).
* Code is well-documented, according to JavaDocs standard.
* No unnecessary code is added (code that is not used).
* Make sure that the code is correctly implemented.
### Commit message convention
`prefix/#issueNR description-of-commit`

#### Commit Prefixes
`feat`
`fix`
`test`
`refactor`
`doc`

##### Example
`feat/#21 added IOHandler` and, then in the following line, write more detailed description of the modifications done by the commit.

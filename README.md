## About the Project
This is a learning project made for studying test automation. The SUT is a webshop and in this project we test standard UI elements and aswell as the workings of the webshop itself.
Our goal in this project is to use our accumulated knowledge of Selenium and JUnit to test the project to the best of our abilities.


## Built With
-Java
-JUnit
-Selenium
-Maven

## Getting Started

###The SUT
The System Under Testing is found online at: https://www.saucedemo.com/
it is a publically available site made for automated testing, there is no-need to set it up.


###The project
-Install and run tests:
1. ** Clone this repository: **
```bash
git clone https://github.com/CodecoolGlobal/test-automation-webshop-in-beta-general-akosszajb.git
```

2. **Navigate to the folder and install dependencies and enviroment variables: **
Since the testing site only accepts predetermined usernames and passwords for these to be added as they are
```bash
set STANDARD_USER=standard_user
set PW_FOR_ALL=secret_sauce
```
Add your own preferred name and zip
```bash
set FIRST_NAME=yourPreferredFirstName
set LAST_NAME=yourPreferredLastName
set ZIP_CODE=yourPreferredZIP
```

```bash
mvn clean install
```
The install should run tests on startup, if it doesnt use:
```bash
mvn test
```

## Contributors
<a href="https://github.com/anita210">anita210</a>

<a href="https://github.com/Zergi0">Zergi0</a>

<a href="https://github.com/akosszajb">akosszajb</a>


# UpgradeCodingChallenge
Coding challenge from Upgrade


** RUNNING TESTS **
To run these test you need the following:
- Java JDK installed (1.8 or higher)
- Eclipse IDE with gradle and lombok plugins
- Chrome driver executable (will need path to executable)

Next, open project in Eclipse and right click on your project - select Gradle->Refresh Gradle Project
to import all dependencies. 
Ensure the Junit View is visible so you can monitor the test as it executes. 
You can now simply right-click any of the test classes (in src/test/java) and choose Run As->Junit Test.
NOTE: for the UI test, you will have to run it in Debug mode (i.e. Debug As->Junit Test) as you need to
set a breakpoint so you can deal with a captcha as there is no way to automate this. See line 104 or the test class. 

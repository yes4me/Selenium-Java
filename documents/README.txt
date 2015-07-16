=============================================================================================================================================
Author:			Thomas Nguyen
Latest code:	https://github.com/yes4me/Selenium-Java
Status:			Everything worked with the browser Chrome on my computer. Testing Firefox is just too slow on my computer. Firefox takes about 1 minute to start.
History:		Assignment 5 completed on 07/16/2015
=============================================================================================================================================

Overview of the project:
	src/test/java/config/Constants.java		to change the browser, update the variable BROWSER to either firefox or chrome
											to change the username and password. This was setup using the email yes4me@cuvox.de (from http://www.fakemailgenerator.com/)
	src/test/java/edu/ucsc.extension:		All test files (example: Wordpress.java contains all test cases for Wordpress)
	src/test/java/edu/lib/					All libraries used in all project
	src/test/java/pages_worldpress/			All page objects regarding Wordpress
	src/test/java/pages_various/			All page objects of other projects

Example:
	The test cases for Worldpress.java will call a DriverFactory object that create a driver based on the BROWSER variable.
	Then Worldpress.java will run all the tests, by using page objects. Each page object have:
		- default general methods defined at CommonPage.java
		- required methods to define from the interface BasicPageObject
		- use the library BasePage.java
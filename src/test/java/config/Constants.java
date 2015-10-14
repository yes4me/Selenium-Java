/* ===========================================================================
Created:	2015/06/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Keep all global constant values
=========================================================================== */

package config;

public class Constants {
	public static final String BROWSER	= "chrome";	//Possible allowed value: 'firefox' or 'chrome'
	public static final int GLOBAL_TIMEOUT	= 10;	//used in DriverFactory.java
	public static final int TIMEOUT			= 10;	//used in BasePage.java

	//Email used: yes4me@cuvox.de
	public static final String DEFAULT_WORDPRESS_USERNAME = "yes4me";
	public static final String DEFAULT_WORDPRESS_PASSWORD = "Selenium2";
}
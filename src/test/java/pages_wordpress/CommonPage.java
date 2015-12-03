/* ===========================================================================
Created:	2015/07/13
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Common defined methods for all page objects for wordpress.com
=========================================================================== */

package pages_wordpress;

import org.openqa.selenium.WebDriver;

import config.Paths;
import lib.BasePage;

public class CommonPage extends BasePage {
	private static final String PARTIAL_URL = "/wp-login.php?loggedout=true";

	public CommonPage(WebDriver driver) {
		super(driver);
	}
	public void Logout() {
		visitURL(Paths.WORDPRESS_URL + PARTIAL_URL);
	}
}

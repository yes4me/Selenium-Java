/* ===========================================================================
Created:	2015/07/12
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
PS:			Order of preference for locators: http://seleniumeasy.com/selenium-tutorials/selenium-locators
=========================================================================== */

package pages_wordpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Paths;

public class P_LoginPage extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Log In";
	private static final String PARTIAL_URL		= "/wp-login.php";

	private WebDriver driver = null;
	@FindBy(id = "user_login") private WebElement username_input;
	@FindBy(id = "user_pass") private WebElement password_input;
	@FindBy(id = "wp-submit") private WebElement submit_button;
	@FindBy(id = "user_login2") private WebElement username_input2;

	public P_LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Override
	public void visit() {
		visit(Paths.WORDPRESS_URL + PARTIAL_URL);
	}

	@Override
	public boolean checkPage() {
		return checkPartialTitle(PARTIAL_TITLE) && checkPartialURL(PARTIAL_URL);
	}

	@Override
	public void waitForPage() {
		//System.out.println(this.getClass().getName() +": "+ getTitle() );
		//System.out.println(this.getClass().getName() +": "+ getCurrentURL() );

		waitForPartialTitle(PARTIAL_TITLE);
		if ( !checkPage() )
			throw new IllegalStateException("Not on the "+ this.getClass().getName());
	}


	public P_Dashboard login(String username, String password) {
		waitForPage();
		type(username_input, username);
		type(password_input, password);
		click(submit_button);

		return PageFactory.initElements(driver, P_Dashboard.class);
	}
}
/* ===========================================================================
Created:	2015/07/13
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
=========================================================================== */

package pages_wordpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Paths;

public class P_Dashboard extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Dashboard";
	private static final String PARTIAL_URL		= "/wp-admin";

	private WebDriver driver = null;
	@FindBy(id = "postbox-container-1") private WebElement atAGlance_div;	//For waiting
	@FindBy(css = "#menu-posts a") private WebElement postsMenu;
	@FindBy(css = "#menu-media a") private WebElement mediaMenuLibrary;
	@FindBy(linkText = "Add New") private WebElement mediaMenuAddNew;

	public P_Dashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Override
	public void visit() {
		visitURL(Paths.WORDPRESS_URL + PARTIAL_URL);
	}

	@Override
	public boolean checkPage() {
		return checkPartialTitle(PARTIAL_TITLE) && checkPartialURL(PARTIAL_URL);
	}

	@Override
	public void waitForPage() {
		waitForPartialTitle(PARTIAL_TITLE);
		waitFor( atAGlance_div );	//Here we should wait for few more items just to be sure
		if ( !checkPage() )
			throw new IllegalStateException("Not on the "+ this.getClass().getName());
	}


	public P_PostDashboard showPostDashboard() {
		waitForPage();
		click(postsMenu);
		return PageFactory.initElements(driver, P_PostDashboard.class);
	}
	public P_MediaLibrary showMediaLibrary() {
		waitForPage();
		click(mediaMenuLibrary);
		return PageFactory.initElements(driver, P_MediaLibrary.class);
	}
	public P_MediaNew showMediaNew() {
		waitForPage();

		//Hover over "Media" menu and click the submenu "Add New"
		Actions actions = new Actions(driver);
		actions.moveToElement(mediaMenuLibrary);
		actions.perform();
		waitFor(mediaMenuAddNew);	//Find comes with a wait
		actions.moveToElement(mediaMenuAddNew);
		actions.click();
		actions.perform();

		return PageFactory.initElements(driver, P_MediaNew.class);
	}
}
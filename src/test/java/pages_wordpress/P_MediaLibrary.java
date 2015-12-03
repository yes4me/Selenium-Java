/* ===========================================================================
Created:	2015/07/15
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
=========================================================================== */

package pages_wordpress;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Paths;

public class P_MediaLibrary extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Media Library";
	private static final String PARTIAL_URL		= "/wp-admin/upload.php";

	private WebDriver driver = null;
	@FindBy(linkText = "Bulk Select") private WebElement bulkSelect_button;
	@FindBy(id = "__attachments-view-40 li") private List<WebElement> thumbsnails_checkbox;
	@FindBy(linkText = "Delete Selected") private WebElement delete_button;

	public P_MediaLibrary(WebDriver driver) {
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
		if ( !checkPage() )
			throw new IllegalStateException("Not on the "+ this.getClass().getName());
	}


	public P_MediaNew showMediaNew() {
		waitForPage();
		click( By.linkText("Add New") );
		return PageFactory.initElements(driver, P_MediaNew.class);
	}


	public boolean BulkSelect(int numberSelectedMedia) {
		if (numberSelectedMedia<=0)
			return false;
		waitForPage();

		click(bulkSelect_button);
		if (numberSelectedMedia > thumbsnails_checkbox.size())
			numberSelectedMedia = thumbsnails_checkbox.size();
		for (WebElement thumbsnail_checkbox : thumbsnails_checkbox)
		{
			if (numberSelectedMedia-- <= 0)
				break;
			click(thumbsnail_checkbox);
		}
		return true;
	}
	public boolean BulkDelete(int numberSelectedMedia) {
		boolean bulkSelect = BulkSelect(numberSelectedMedia);
		click(delete_button);

		//This is for popup
		Alert javascriptAlert = driver.switchTo().alert();
		javascriptAlert.accept();

		return bulkSelect;
	}
}
/* ===========================================================================
Created:	2015/07/15
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
=========================================================================== */

package pages_wordpress;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Paths;

public class P_MediaNew extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Upload New Media";
	private static final String PARTIAL_URL		= "/wp-admin/media-new.php";

	private WebDriver driver = null;
	@FindBy(linkText = "Switch to the multi-file uploader") private WebElement multiFileUploader_URL;
	@FindBy(linkText = "browser uploader") private WebElement browserUploader_URL;
	//@FindBy(css = ".upload-flash-bypass a") private WebElement browserUploader_URL;

	@FindBy(id = "async-upload") private WebElement fileUploader_div;
	@FindBy(id = "html-upload") private WebElement fileUploader_button;


	public P_MediaNew(WebDriver driver) {
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

	public P_MediaLibrary uploadNewMedia(String fileName) {
		waitForPage();

		File f = new File(fileName);
		if(f.exists() && !f.isDirectory())
		{
			//Use the browser uploader instead. The file input field is hidden and cannot be accessed
			if ( isDisplayed(browserUploader_URL) )
				click(browserUploader_URL);

			WebElement fileUpload = find( By.id("async-upload") );
			fileUpload.sendKeys(fileName);
			click(fileUploader_button);
		}
		return PageFactory.initElements(driver, P_MediaLibrary.class);
	}
}
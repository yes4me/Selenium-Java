/* ===========================================================================
Created:	2015/07/14
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
=========================================================================== */

package pages_wordpress;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;

public class P_NewPostForm extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Add New Post";
	private static final String PARTIAL_URL		= "/wp-admin/post-new.php";
	private static final String FRAME_TYPING	= "content_ifr";

	private WebDriver driver = null;
	@FindBy(name = "post_title") private WebElement title_input;
	@FindBy(id = "publish") private WebElement publish_button;
	@FindBy(css = "#mceu_4>button") private WebElement numberList_button;
	@FindBy(id = "post_ID") private WebElement postHiddenID;

	//Inside the frame
	@FindBy(id = "tinymce") private WebElement textArea;

	public P_NewPostForm(WebDriver driver) {
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
		waitForPartialTitle(PARTIAL_TITLE);
		if ( !checkPage() )
			throw new IllegalStateException("Not on the "+ this.getClass().getName());
	}


	public int publish(String title, List<String> contentBullets) {
		waitForPage();

		type(title_input, title);
		click(numberList_button);

	    driver.switchTo().frame(FRAME_TYPING);
	    for (String contentBullet : contentBullets)
	    {
    		type(textArea, contentBullet + Keys.RETURN);
	    }
	    driver.switchTo().defaultContent();

	    int postID = Integer.parseInt( postHiddenID.getAttribute("value") );
	    click(publish_button);
		return postID;
	}
}
/* ===========================================================================
Created:	2015/07/13
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object for wordpress.com
=========================================================================== */

package pages_wordpress;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Paths;

public class P_PostDashboard extends CommonPage implements BasicPageObject {
	private static final String PARTIAL_TITLE	= "Posts";
	private static final String PARTIAL_URL		= "/wp-admin/edit.php";
	private static final String NO_POST_FOUND	= "No posts found.";
	private static final String DELETE_BULK		= "Move to Trash";

	private WebDriver driver = null;
	@FindBy(linkText = "Add New") private WebElement addNewPost_button;
	@FindBy(linkText = "Update") private WebElement updatePost_button;
	@FindBy(css = "table.posts") private WebElement tablePosts;			//For waiting
	@FindBy(css = "tbody#the-list tr") private List<WebElement> posts;
	@FindBy(id = "cb-select-all-1") private WebElement selectAllPost_checkbox;
	@FindBy(id = "bulk-action-selector-bottom") private WebElement actionAll_select;
	@FindBy(id = "doaction2") private WebElement submitApply_button;


	public P_PostDashboard(WebDriver driver) {
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
		waitFor(addNewPost_button);
		waitFor(tablePosts);
		if ( !checkPage() )
			throw new IllegalStateException("Not on the "+ this.getClass().getName());
	}


	public int getPostCount() {
		waitForPage();
		int numberPost = posts.size();

		//need to check if "no posts found"
		if (numberPost == 1)
			if ( posts.get(0).getText().contains(NO_POST_FOUND) )
				return 0;
		return numberPost;
	}
	public void selectPostById(int postId) {
		waitForPage();
		if (postId >= 0)
		{
			WebElement post = find( By.id("cb-select-"+ postId) );
			click(post, true);
		}
	}
	public void selectAllPosts() {
		waitForPage();
		click(selectAllPost_checkbox, true);
	}


	public P_NewPostForm showNewPostForm() {
		waitForPage();
		click(addNewPost_button);
		return PageFactory.initElements(driver, P_NewPostForm.class);
	}
	public void deletePostById(int postId) {
		if (getPostCount() > 0)
		{
			selectPostById(postId);
			BulkActions(DELETE_BULK);
		}
	}
	public void deleteAllPosts() {
		if (getPostCount() > 0)
		{
			selectAllPosts();
			BulkActions(DELETE_BULK);
		}
	}
	public void BulkActions(String action) {
		select(actionAll_select, action);
		click(submitApply_button);
	}


	public void quickEdit(int postId) {
		waitForPage();
		if (getPostCount() > 0)
		{
			WebElement post = find( By.id("cb-select-"+ postId) );
			Actions actions = new Actions(driver);
			actions.moveToElement(post);
			actions.perform();

			WebElement quickEdit_URL = find( By.cssSelector("tr#post-"+ postId +" a.editinline") );
			waitFor(quickEdit_URL);
			actions.moveToElement(quickEdit_URL);
			actions.click();
			actions.perform();
		}
	}
	public void quickEditTitle(int postId, String newTitle) {
		waitForPage();
		quickEdit(postId);

		WebElement title_input = find( By.cssSelector("tr#edit-"+ postId +" input.ptitle") );
		waitFor(title_input);
		typeNew(title_input, newTitle);
		click(updatePost_button);
	}
}
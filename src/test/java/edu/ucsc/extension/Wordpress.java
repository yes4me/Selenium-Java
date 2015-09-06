/* ===========================================================================
Created: 2015/07/13 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: test Wordpress
=========================================================================== */

package edu.ucsc.extension;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import config.Constants;
import config.Paths;
import lib.DriverFactory;
import pages_wordpress.P_Dashboard;
import pages_wordpress.P_LoginPage;
import pages_wordpress.P_MediaLibrary;
import pages_wordpress.P_MediaNew;
import pages_wordpress.P_NewPostForm;
import pages_wordpress.P_PostDashboard;

public class Wordpress {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new DriverFactory().driver();
	}
	@After
	public void tearDown() throws Exception {
		System.out.println("DONE");
		driver.quit();
	}


	@Test
	public void testPostCreation() {
		//Login, count the number of posts, add a new post, count again the number of posts
		System.out.println("RUNNING TEST: testPostCreation");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_PostDashboard postdashboard	= dashboard.showPostDashboard();
		int postCount = postdashboard.getPostCount();
		System.out.println("There are " + postCount + " posts.");

		P_NewPostForm newPostForm		= postdashboard.showNewPostForm();

		List<String> contentBullet = new ArrayList<String>();
		contentBullet.add("Jalin S Shah");
		contentBullet.add("Vidhi A Desai");
		contentBullet.add("Aarav J Shah");
		int postID = newPostForm.publish("New Title", contentBullet);
		System.out.println("Post ID: " + postID);

		postdashboard.visit();
		postCount = postdashboard.getPostCount();
		System.out.println("There are " + postCount + " posts.");

		loginPage.Logout();
	}

	@Test
	public void testDelete1Post() {
		//Login, create 1 post, delete the same post
		System.out.println("RUNNING TEST: testDelete1Post");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_PostDashboard postdashboard	= dashboard.showPostDashboard();
		P_NewPostForm newPostForm		= postdashboard.showNewPostForm();

		//Create one post
		List<String> contentBullet = new ArrayList<String>();
		contentBullet.add("Jalin S Shah");
		contentBullet.add("Vidhi A Desai");
		contentBullet.add("Aarav J Shah");
		int postID = newPostForm.publish("Post to delete", contentBullet);
		postdashboard.visit();
		postdashboard.deletePostById(postID);

		loginPage.Logout();
	}

	@Test
	public void testDelete3Post() {
		//Login, create 3 post, delete 3 posts one at a time
		System.out.println("RUNNING TEST: testDelete3Post");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_PostDashboard postdashboard	= dashboard.showPostDashboard();

		//Create 3 posts
		List<Integer> postID = new ArrayList<Integer>();
		P_NewPostForm newPostForm;
		for (int i=0; i<3; i++)
		{
			newPostForm		= postdashboard.showNewPostForm();
			List<String> contentBullet = new ArrayList<String>();
			contentBullet.add("Jalin S Shah");
			contentBullet.add("Vidhi A Desai");
			contentBullet.add("Aarav J Shah");
			postID.add( newPostForm.publish("testDelete3Post "+ i, contentBullet) );
			postdashboard.visit();
		}

		for (Integer id : postID)
			postdashboard.deletePostById(id);

		loginPage.Logout();
	}

	@Test
	public void testDeleteAllPost() {
		//Login, create 5 posts, delete all posts
		System.out.println("RUNNING TEST: testDeleteAllPost");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_PostDashboard postdashboard	= dashboard.showPostDashboard();

		//Create 5 posts
		P_NewPostForm newPostForm;
		for (int i=0; i<5; i++)
		{
			newPostForm		= postdashboard.showNewPostForm();
			List<String> contentBullet = new ArrayList<String>();
			contentBullet.add("Jalin S Shah");
			contentBullet.add("Vidhi A Desai");
			contentBullet.add("Aarav J Shah");
			newPostForm.publish("testDeleteAllPost "+ i, contentBullet);
			postdashboard.visit();
		}

		postdashboard.deleteAllPosts();

		loginPage.Logout();
	}

	@Test
	public void testQuickEdit() {
		//Login, create 1 post, quick edit the title of the post, and delete the post
		System.out.println("RUNNING TEST: testQuickEdit");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		//loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_PostDashboard postdashboard	= dashboard.showPostDashboard();

		//Create a post
		P_NewPostForm newPostForm		= postdashboard.showNewPostForm();
		List<String> contentBullet = new ArrayList<String>();
		contentBullet.add("Jalin S Shah");
		contentBullet.add("Vidhi A Desai");
		contentBullet.add("Aarav J Shah");
		int postID = newPostForm.publish("Post to quickly edit", contentBullet);

		postdashboard.visit();
		postdashboard.quickEditTitle(postID, "New title");

		loginPage.Logout();
	}

	@Test
	public void testUploadMedia() {
		//Login, upload 3 photos, delete them
		System.out.println("RUNNING TEST: testUploadMedia");

		P_LoginPage loginPage = PageFactory.initElements(driver, P_LoginPage.class);
		loginPage.visit();
		loginPage.windowsMaximize();

		P_Dashboard dashboard			= loginPage.login(Constants.DEFAULT_WORDPRESS_USERNAME, Constants.DEFAULT_WORDPRESS_PASSWORD);
		P_MediaNew mediaNew				= dashboard.showMediaNew();
		P_MediaLibrary mediaLibrary		= mediaNew.uploadNewMedia(Paths.HOME_FOLDER + "\\drawable\\android1.png");
		mediaLibrary.showMediaNew();
		mediaNew.uploadNewMedia(Paths.HOME_FOLDER + "\\drawable\\android2.png");
		mediaLibrary.showMediaNew();
		mediaNew.uploadNewMedia(Paths.HOME_FOLDER + "\\drawable\\android3.png");
		mediaLibrary.BulkDelete(3);

		loginPage.Logout();
	}
}
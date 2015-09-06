package edu.ucsc.extension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lib.BasePage;
import lib.DriverFactory;
import lib.Util;

public class Demo {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new DriverFactory().driver();
	}
	@After
	public void tearDown() throws Exception {
		System.out.println("DONE");
		//driver.quit();
	}

	// ===============================================================

	//Default test
	@Test
	@Ignore
	public void basic() {
		System.out.println("RUNNING TEST: basic");
		BasePage page = new BasePage(driver);
		page.visit("http://www.google.com");

		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("UCSC Extension");
		element.submit();
	}

	//Testing KEYS controls
	@Test
	@Ignore
	public void testingKeys() {
		System.out.println("RUNNING TEST: testingKeys");
		BasePage page = new BasePage(driver);
		page.visit("http://www.google.com");

		//Display on the console
		((JavascriptExecutor)driver).executeScript("console.log('Logging from Selenium');");

		//Get the 1st element by name
		WebElement searchBox = (WebElement)((JavascriptExecutor)driver).executeScript("return document.getElementsByName('q')[0]");
		//WebElement searchBox = (WebElement)((JavascriptExecutor)driver).executeScript("return document.getElementById('email')");

		//Type in the search box
		//searchBox.sendKeys("UCSC Extension");
		searchBox.sendKeys(Keys.chord(Keys.SHIFT, "university of santa clara"));
		searchBox.submit();

		//Scroll down
		for(int i=0; i<10; i++) {
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0, window.innerHeight)");
			Util.wait(2000);
		}
	}

	//Testing Javascript
	@Test
	@Ignore
	public void testingJavascript() {
		System.out.println("RUNNING TEST: testingJavascript");
		BasePage page = new BasePage(driver);
		page.visit("http://www.mortgagecalculator.org");

		((JavascriptExecutor)driver).executeScript("$(\"select[name='param[start_month]']\").val('3')");
		((JavascriptExecutor)driver).executeScript("$(\"select[name='param[start_year]']\").val('2014')");
	}

	//Testing iFrame: Enter the zip code inside an iframe, and check the city name that is return from Ajax
	@Test
	@Ignore
	public void testingIframes() {
		System.out.println("RUNNING TEST: testingIframes");
		BasePage page = new BasePage(driver);
		page.visit("http://www.credomobile.com/coverage");

		page.click( By.className("close") );

		//check which one work
		driver.switchTo().frame(0);
		//String frameID = page.visitIFrame( By.xpath("//div[@id='coverageMap']/div/iframe") );
		//String frameID = page.searchIFrame( By.id("mapzip") );	//My custom way
		page.type( By.id("mapzip"), "95035");
		page.click( By.id("mapit") );

		page.waitFor( By.id("covaddr"), 1000);

		String cityName = page.getText( By.id("covaddr") );
		System.out.println("cityName:"+ cityName);

		assertEquals(cityName, "Milpitas, CA 95035");
		assertTrue( cityName.contains("Milpitas") );
		Assert.assertEquals(cityName, "Milpitas, CA 95035");
		Assert.assertTrue( cityName.contains("Milpitas") );

		//Return to the parent frame (either ways work)
		driver.switchTo().parentFrame();
		//driver.switchTo().frame(frameID);
	}

	//Testing the attributes: After you google "12345", check if the google map refer to the address 12345
	@Test
	public void google_12345()
	{
		System.out.println("RUNNING TEST: google_12345");
		BasePage page = new BasePage(driver);
		page.visit("http://www.google.com");

		page.type( By.name("q"), "12345");
		//driver.findElement( By.name("btnK") ).submit();	//If we don't wait at all, we can use "btnK"
		page.submit( By.name("btnG") );						//If we wait a tiny bit, we have to use "btnG"

		List<WebElement> maps = page.finds( By.id("lu_map") );
		assertEquals(maps.size(), 1);
		//Verify.verify( maps.size()==1 );

		String href = maps.get(0).getAttribute("src");
		System.out.println("href="+ href);
		assertTrue( href.contains("www.google.com/maps") );
	}
}
package edu.ucsc.extension;

import lib.BasePage;
import lib.DriverFactory;
import lib.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	@Test
	@Ignore
	public void basic() {
		BasePage page = new BasePage(driver);
		page.visit("http://www.google.com");

		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("UCSC Extension");
		element.submit();
	}

	@Test
	@Ignore
	public void javascript() {
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
			Util.wait(2);
		}
	}

	@Test
	//@Ignore
	public void testSomething() {
		BasePage page = new BasePage(driver);
		page.visit("http://www.mortgagecalculator.org");

		((JavascriptExecutor)driver).executeScript("$(\"select[name='param[start_month]']\").val('3')");
		((JavascriptExecutor)driver).executeScript("$(\"select[name='param[start_year]']\").val('2014')");
	}
}
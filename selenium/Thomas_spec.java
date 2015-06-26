package com.jatin.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Thomas_spec {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("START");
		System.setProperty("webdriver.chrome.driver", "C:\\save\\thomas\\job\\computer\\Selenium Java\\Important code\\vendor\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("DONE");
		//driver.quit();
	}


	@Test
	public void demo() {
		System.out.println("RUNNING TEST");

		BasePage basePage = new BasePage(driver);
		basePage.visit( Constants.BASE_URL );
	}
}
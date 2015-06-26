package com.jatin.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestThomas {
	@Test
	public void demo() {
		//BasePage b = new BasePage();
		//b.test();
		//System.out.println("asd");
		
		System.setProperty("webdriver.chrome.driver", "C:\\save\\thomas\\job\\computer\\Selenium Java\\Important code\\vendor\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();

		BasePage basePage = new BasePage(driver);
		basePage.visit("https://www.google.com/?gws_rd=ssl");
		//basePage.select( By.name("mydropdown"), "Old Cheese");
		//String tmp = basePage.getText( By.linkText("Gmail"));
		//Util.wait(5);
		
	
		//driver.quit();
	}
}

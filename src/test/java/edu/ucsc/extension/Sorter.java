package edu.ucsc.extension;

import java.io.File;
import java.util.List;

import lib.Util;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Sorter {

	@Test
	public void sortNumbers() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "sorter.html");
		
		// Your sorting code here
		
		Util.wait(10);
		driver.quit();		
	}
	
}

/* ===========================================================================
2015/06/26 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: set of Selenium methods that should universally work on all websites
=========================================================================== */

package com.jatin.selenium;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;


	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}


	//General methods
	public void visit(String url_path) {
		driver.get(url_path);
	}
	public String getTitlePage() {
		return driver.getTitle();
	}
	//Verify
	public boolean isDisplayed(By locator) {
		return find(locator).isDisplayed();
	}
	public String getText(By locator) {
		return find(locator).getText();
	}
	public void waitFor(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated( locator));
	}


	//Locator
	public WebElement find(By locator) {
		return driver.findElement(locator);
	}
	public List<WebElement> finds(By locator) {
		return driver.findElements(locator);
	}
	//Status code 403 responses are the result of the web server being configured to deny access, for some reason, to the requested resource by the client.
    public boolean is403(By locator) {
        try {
        	find(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	public int countElement(By locator) {
		return finds(locator).size();
	}


	//Forms: input, buttons & select
	public boolean type(By locator, String text) {
		if ( isDisplayed(locator) )
		{
			find(locator).sendKeys(text);
			return true;
		}
		return false;
	}
	public boolean clear(By locator) {
		if ( isDisplayed(locator) )
		{
			find(locator).clear();
			return true;
		}
		return false;
	}
	public void select(By selectLocator, String optionText) {
		//Either way works:
		/*
		WebElement select = find(selectLocator);
		List<WebElement> options = select.findElements( By.tagName("option") );
		for (WebElement option : options) {
			if (optionText.equals( option.getText().trim() )) {
				option.click();
			}
		}
		*/

		Select dropdown = new Select(driver.findElement(selectLocator));
		dropdown.selectByVisibleText(optionText);
	}
	public boolean click(By locator) {
		if ( isDisplayed(locator) )
		{
			find(locator).click();
			return true;
		}
		return false;
	}
	public boolean submit(By locator) {
		if ( isDisplayed(locator) )
		{
			find(locator).submit();
			return true;
		}
		return false;
	}


	//Others
	public void windowsMaximize() {
		driver.manage().window().maximize();
	}
	//Source: http://stackoverflow.com/questions/997482/does-java-support-default-parameter-values
	public void takeScreenshot(String... fileName) {
		String screenshotFileName = fileName.length>0? fileName[0] : Constants.SCREENSHOT_FILE;
		try
		{
			File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenFile, new File(screenshotFileName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	 }



	//TO DO
	public boolean downloadPict(By locator) {
		return true;
	}
}
/* ================================================================
2015/06/26 Thomas
Purpose: set of Selenium methods that should universally work on all websites
================================================================ */

package com.jatin.selenium;

import java.util.List;

import org.openqa.selenium.By;
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
	public boolean select(By selectLocator, String optionText) {
		//Either way works:
		//Select dropdown = new Select(driver.findElement(locator));
		//dropdown.selectByVisibleText(optionText);

		WebElement select = find(selectLocator);
		List<WebElement> options = select.findElements( By.tagName("option") );
		for (WebElement option : options) {
			if (optionText.equals( option.getText().trim() )) {
				option.click();
				return true;
			}
		}
		return false;
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
}
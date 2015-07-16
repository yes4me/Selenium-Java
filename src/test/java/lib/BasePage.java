/* ===========================================================================
Created:	2015/06/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	set of Selenium methods that should universally work on all websites
=========================================================================== */

package lib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Constants;
import config.Paths;

public class BasePage {
	private WebDriver driver;


	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}

	/* ---------------------------------------------------------------------------
	Windows
	--------------------------------------------------------------------------- */
	public String getTitle() {
		return driver.getTitle();
	}
	public boolean checkPartialTitle(String title) {
		return getTitle().contains(title);
	}

	public String fixURL(String url) {
		url = url.trim();
		url = url.replaceAll("^www.", "http://www.");
		url = url.replaceAll("\\\\$", "");
		return url;
	}
	public boolean compareURL(String url1, String url2) {
		url1 = fixURL(url1).toLowerCase();
		url2 = fixURL(url2).toLowerCase();
		if (url1.equals(url2))
			return true;
		return false;
	}
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	public boolean checkPartialURL(String url) {
		return getCurrentURL().contains(url);
	}

	public boolean visit(String url_path) {
		if (url_path != "")
		{
			driver.get(url_path);
			return true;
		}
		return false;
	}
	public void navigate_forward() {
		driver.navigate().forward();
	}
	public void navigate_refresh() {
		driver.navigate().refresh();
	}
	public void navigate_back() {
		driver.navigate().back();
	}

	public void windowsMaximize() {
		driver.manage().window().maximize();
	}
	public void windowsResize(int height, int width) {
		driver.manage().window().setSize( new Dimension(height, width) );
	}

	/* ---------------------------------------------------------------------------
	Frames
	--------------------------------------------------------------------------- */

	public void ListAllFrames() {
	    final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	    for (WebElement iframe : iframes) {
			System.out.println("==>"+ iframe.getAttribute("id") );
	    }
	}

	/* ---------------------------------------------------------------------------
	Locators
	--------------------------------------------------------------------------- */
	public WebElement find(By locator) {
		//return (WebElement)((JavascriptExecutor)driver).executeScript("return document.getElementById('email')");
		waitFor(locator);
		return driver.findElement(locator);
	}
	public List<WebElement> finds(By locator) {
		waitFor(locator);
		return driver.findElements(locator);
	}
	public int countElement(List<WebElement> webElements) {
		return webElements.size();
	}
	public int countElement(By locator) {
		return countElement( finds(locator) );
	}
	public String getText(WebElement webElement) {
		return webElement.getText();
	}
	public String getText(By locator) {
		return getText( find(locator) );
	}


	/* ---------------------------------------------------------------------------
	Verify
	--------------------------------------------------------------------------- */
	public boolean isDisplayed(WebElement webElement) {
		try
		{
			return webElement.isDisplayed();
		}
		catch (Exception e)
		{
			//System.out.println("Error isDisplayed: "+ e);
			//e.printStackTrace();
		}
		return false;
	}
	public boolean isDisplayed(By locator) {
		return isDisplayed( find(locator) );
	}

	/* ---------------------------------------------------------------------------
	Forms: input, buttons & select
	--------------------------------------------------------------------------- */
	public boolean typeNew(WebElement webElement, String text) {
		clear(webElement);
		return type(webElement, text);
	}
	public boolean typeNew(By locator, String text) {
		return typeNew(find(locator), text);
	}
	public boolean type(WebElement webElement, String text) {
		if ( isDisplayed(webElement) )
		{
			//Stuff that can be passed to sendKeys: Keys.SHIFT
			webElement.sendKeys(text);
			return true;
		}
		return false;
	}
	public boolean type(By locator, String text) {
		return type(find(locator), text);
	}
	public boolean clear(WebElement webElement) {
		if ( isDisplayed(webElement) )
		{
			webElement.clear();
			return true;
		}
		return false;
	}
	public boolean clear(By locator) {
		return clear( find(locator) );
	}
	public void select(WebElement webElement, String optionText) {
		Select dropdown = new Select(webElement);
		dropdown.selectByVisibleText(optionText);
	}
	public void select(By selectLocator, String optionText) {
		//WAY#1:
		/*
		WebElement select = find(selectLocator);
		List<WebElement> options = select.findElements( By.tagName("option") );
		for (WebElement option : options) {
			if (optionText.equals( option.getText().trim() )) {
				option.click();
			}
		}

		//WAY#2:
		((JavascriptExecutor)driver).executeScript("$(\"select[name='param[start_month]']\").val('" + optionText +"')");

		//WAY#3:
		Select dropdown = new Select(driver.findElement(selectLocator));
		dropdown.selectByVisibleText(optionText);
		dropdown.selectByValue("5");
		dropdown.selectByIndex(3);
		*/
		select(find(selectLocator), optionText);
	}

	public boolean click(WebElement webElement, boolean... checkBox) {
		waitFor(webElement);
		if ( isDisplayed(webElement) )
		{
			if (checkBox.length > 0)
			{
				if (Boolean.TRUE.equals(checkBox[0]))
				{
					if (!webElement.isSelected())
						webElement.click();
				}
				else if (Boolean.FALSE.equals(checkBox[0]))
				{
					if (webElement.isSelected())
						webElement.click();
				}
			}
			else
			{
				webElement.click();
			}
			return true;
		}
		return false;
	}
	public boolean click(By locator, boolean... checkBox) {
		return click(find(locator), checkBox);
	}
	public boolean submit(WebElement webElement) {
		waitFor(webElement);
		if ( isDisplayed(webElement) )
		{
			webElement.submit();
			return true;
		}
		return false;
	}
	public boolean submit(By locator) {
		return submit( find(locator) );
	}

	/* ---------------------------------------------------------------------------
	Pictures
	--------------------------------------------------------------------------- */
	//Source: http://stackoverflow.com/questions/997482/does-java-support-default-parameter-values
	public boolean takeScreenshot(String... fileName) {
		String screenshotFileName = fileName.length>0? fileName[0] : Paths.SCREENSHOT_FILENAME;
		try
		{
			File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenFile, new File(screenshotFileName));
			return true;
		}
		catch (IOException e)
		{
			System.out.println("Error takeScreenshot: "+ e);
			e.printStackTrace();
		}
		return false;
	}
	public boolean downloadPict(WebElement webElement, String fileName) {
		String url = webElement.getAttribute("src");
		return downloadPict(url, fileName);
	}
	public boolean downloadPict(By locator, String fileName) {
		return downloadPict( find(locator), fileName);
	}
	public boolean downloadPict(String url, String fileName) {
		try {
			org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), new File(fileName) );
			return true;
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException downloadPict: "+ e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException downloadPict: "+ e);
			e.printStackTrace();
		}
		return false;
	}

	/* ---------------------------------------------------------------------------
	Wait, Selenium is too fast ^_^
	--------------------------------------------------------------------------- */
	//Check for a given condition every 500ms until it returns successfully or timeout
	public void waitFor(WebElement webElement) {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(webElement));
		}
		catch (Exception e)
		{
			System.out.println("Exception waitFor: "+ e);
			e.printStackTrace();
			//throw new NoSuchElementException("No webElement");
		}
	}
	public void waitFor(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		//WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated( locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated( locator));
	}

	//Wait for a specific title
	public void waitForPartialTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.titleContains(title));

		if ( !checkPartialTitle(title) )
			throw new IllegalStateException("Not at: "+ title);
	}

	/* ---------------------------------------------------------------------------
	Other methods
	--------------------------------------------------------------------------- */
	//Status code 403 responses are the result of the web server being configured to deny access, for some reason, to the requested resource by the client.
    public boolean is403(By locator) {
        try {
        	find(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
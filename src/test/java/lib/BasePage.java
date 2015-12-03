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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.Constants;
import config.Paths;

public class BasePage {
	private final int WAIT_TIME_MS		= 10;
	private final int WAIT_MAX_ATTEMPT	= 500;

	private WebDriver driver;
	private String parentWindowHandle;

	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}

	/* ---------------------------------------------------------------------------
	Getter and Setter methods
	--------------------------------------------------------------------------- */

	public WebDriver getWebDriver() {
		return driver;
	}

	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	/* ---------------------------------------------------------------------------
	URL
	--------------------------------------------------------------------------- */

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

	public boolean visitURL(String url_path) {
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

	//For input elements, the displayed text is not wrapped by the <input> tag, instead it's inside the value attribute
	public String getText(WebElement webElement) {
		return (webElement.getTagName().equals("input") )? webElement.getAttribute("value") : webElement.getText();
	}
	public String getText(By locator) {
		return getText( driver.findElement(locator) );
	}

	/* ---------------------------------------------------------------------------
	Verify
	--------------------------------------------------------------------------- */

	public boolean isDisplayed(WebElement webElement) {
		try
		{
			return webElement.isDisplayed();
		} catch (NoSuchElementException e) {
			//LOG.error("Unable to find webelement: NoSuchElementException in isDisplayed(): ", e);
			Assert.fail();
		} catch (StaleElementReferenceException e) {
			//LOG.error("Unable to find webelement: StaleElementReferenceException in isDisplayed(): ", e);
			Assert.fail();
		} catch (Exception e) {
			//LOG.error("Unable to find webelement: Exception in isDisplayed(): ", e);
			Assert.fail();
		}
		return false;
	}
	public boolean isDisplayed(By locator) {
		return isDisplayed( driver.findElement(locator) );
	}

	public boolean isEnabled(WebElement webElement) {
		try
		{
			return webElement.isEnabled();
		} catch (NoSuchElementException e) {
			//LOG.error("Unable to find webelement: NoSuchElementException in isDisplayed(): ", e);
			Assert.fail();
		} catch (StaleElementReferenceException e) {
			//LOG.error("Unable to find webelement: StaleElementReferenceException in isDisplayed(): ", e);
			Assert.fail();
		} catch (Exception e) {
			//LOG.error("Unable to find webelement: Exception in isDisplayed(): ", e);
			Assert.fail();
		}
		return false;
	}
	public boolean isEnabled(By locator) {
		return isEnabled( driver.findElement(locator) );
	}

	/* ---------------------------------------------------------------------------
	Forms: input, buttons
	--------------------------------------------------------------------------- */

	public boolean typeNew(WebElement webElement, String text) {
		clear(webElement);
		return type(webElement, text);
	}
	public boolean typeNew(By locator, String text) {
		return typeNew( driver.findElement(locator), text);
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
		return type( driver.findElement(locator), text);
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
		return clear( driver.findElement(locator) );
	}

	public boolean click(WebElement webElement, boolean... checkBox) {
		waitFor(webElement);
		if ( isDisplayed(webElement) && isEnabled(webElement))
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
		return click( driver.findElement(locator), checkBox);
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
		return submit( driver.findElement(locator) );
	}

	/* ---------------------------------------------------------------------------
	Forms: select
	--------------------------------------------------------------------------- */

	public void setSelect(WebElement webElement, String optionText) {
		Select dropDown = new Select(webElement);
		dropDown.selectByVisibleText(optionText);
	}
	public void setSelect(By selectLocator, String optionText) {
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
		setSelect( driver.findElement(selectLocator), optionText);
	}

	public void setSelect(WebElement webElement, int index) {
		Select dropdown = getSelect(webElement);
		dropdown.selectByIndex(index);
	}
	public void setSelect(By selectLocator, int index) {
		Select dropdown = getSelect(selectLocator);
		dropdown.selectByIndex(index);
	}

	public String getSelectText(WebElement webElement) {
		return getSelect(webElement).getFirstSelectedOption().getText().trim();
	}

	public Select getSelect(WebElement webElement) {
		return new Select(webElement);
	}
	public Select getSelect(By selectLocator) {
		return getSelect(selectLocator);
	}
	public List<String> getSelects(WebElement webElement) {
		List<String> result = new ArrayList<String>();

		/*
		Select dropDown = new Select(webElement);
		List<WebElement> options = dropDown.getAllSelectedOptions();
		for (WebElement option : options) {
			result.add(option.getText());
		}
		*/
		List<WebElement> options = webElement.findElements(By.tagName("option"));
		for (WebElement option : options) {
			//Clean up the option text (i.e. remove any extra new line)
			String optionText = option.getText().trim();
			optionText = optionText.replaceAll("\n", " ");

			result.add(optionText);
		}
		return result;
	}

	/* ---------------------------------------------------------------------------
	Pictures
	--------------------------------------------------------------------------- */

	//Check if a picture has been loaded
	boolean isPictDisplayed(WebElement pict)
	{
		Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth>0", pict);
		return (!imageLoaded1)? false:true;
	}
	//Check if all pictures are loaded
	boolean isAllPictsDisplayed()
	{
		List<WebElement> picts = driver.findElements(By.tagName("img"));
		for (WebElement pict : picts)
		{
			if (! isPictDisplayed(pict))
				return false;
		}
		return true;
	}

	//Source: http://stackoverflow.com/questions/997482/does-java-support-default-parameter-values
	public boolean takeScreenshot(String... fileName) {
		String screenshotFileName = fileName.length>0? fileName[0] : Paths.DEFAULT_SCREENSHOT_FILENAME;
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
		return downloadPict( driver.findElement(locator), fileName);
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
	Cookies
	--------------------------------------------------------------------------- */

	//Check if a specific cookie is present
	public boolean checkCookie(String name) {
		return (driver.manage().getCookieNamed(name)!=null)? true:false;
	}
	public boolean addCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		try
		{
			driver.manage().addCookie(cookie);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	public boolean createCookie(String name, String value) {
		if ( checkCookie(name) )
			return false;
		return addCookie(name, value);
	}
	public boolean updateCookie(String name, String value) {
		if ( !checkCookie(name) )
			return false;
		return addCookie(name, value);
	}
	public String readCookieValue(String name) {
		if ( !checkCookie(name) )
			return "";
		Cookie cookie = driver.manage().getCookieNamed(name);
		return cookie.getValue();
	}
	public boolean deleteCookie(String name) {
		if ( !checkCookie(name) )
			return false;
		driver.manage().deleteCookieNamed(name);
		return true;
	}

	//Return the number of cookies
	public int countAllCookies() {
		Set<Cookie> allCookies = driver.manage().getCookies();
		return allCookies.size();
	}
	//View all the cookies information
	public void readAllCookies() {
		Set<Cookie> allCookies = driver.manage().getCookies();
		for (Cookie aCookie : allCookies)
		{
			System.out.println(aCookie.getName() +": "+ aCookie.getValue());
		}
	}
	//Return the number of cookies deleted
	public int deleteAllCookies() {
		int cookieNb = countAllCookies();
		driver.manage().deleteAllCookies();
		return cookieNb;
	}

	/* ---------------------------------------------------------------------------
	iFrames
	--------------------------------------------------------------------------- */

    //Return the number of iframe present in the page
    public int nbIFrame() {
		List<WebElement> myIframes= driver.findElements(By.tagName("iframe"));
		return myIframes.size();
    }

    //Go to an iFrame based on the xpath, and return the ID of the original iframe so we can return back
    public String visitIFrame(WebElement webElement) {
    	String myOriginalWindowHandle = driver.getWindowHandle();
    	driver.switchTo().frame(webElement);
    	return myOriginalWindowHandle;
    }
    public String visitIFrame(By locator) {
    	return visitIFrame( driver.findElement(locator) );
    }

    //Search for a webElement inside a iFrame
    public String searchIFrame(By locator) {
    	String iFrameHandle_original = driver.getWindowHandle();
    	String iFrameHandle = null;

    	List<WebElement> iframes= driver.findElements(By.tagName("iframe"));
		for (int i=0; i<iframes.size(); i++)
		{
			driver.switchTo().frame(i);
			if (driver.findElements( locator ).size() != 0)
			{
				iFrameHandle = driver.getWindowHandle();
		    	break;
			}
		}
		driver.switchTo().window(iFrameHandle_original);
		return iFrameHandle;
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
	Windows
	--------------------------------------------------------------------------- */

	public String getTitle() {
		return driver.getTitle();
	}
	public boolean checkPartialTitle(String title) {
		return getTitle().contains(title);
	}

	public void windowsMaximize() {
		driver.manage().window().maximize();
	}
	public void windowsResize(int height, int width) {
		driver.manage().window().setSize( new Dimension(height, width) );
	}

	/* ---------------------------------------------------------------------------
	Wait, Selenium is too fast ^_^ => explicit wait methods
	--------------------------------------------------------------------------- */

	//wait for page loaded
	public boolean waitPageLoaded()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i=0; i<WAIT_MAX_ATTEMPT; i++)
		{
			Util.wait(WAIT_TIME_MS);
			if ( js.executeScript("return document.readyState").equals("complete") )
				return true;
		}
		return false;
	}

	//Check for a given condition every 500ms until it returns successfully or timeout
	//wait for a bit after. This is important in the case of Ajax when the information just arrived but has not been displayed yet
	public boolean waitFor(WebElement webElement, int milliseconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			Util.wait(milliseconds);
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Exception waitFor: "+ e);
			e.printStackTrace();
			//throw new NoSuchElementException("No webElement");
		}
		return false;
	}
	public boolean waitFor(WebElement webElement) {
		return waitFor(webElement, 0);
	}

	public boolean waitFor(By locator, int milliseconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
			//visibilityOfElementLocated works better than visibilityOf in some cases
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Util.wait(milliseconds);
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Exception waitFor: "+ e);
			e.printStackTrace();
			//throw new NoSuchElementException("No webElement");
		}
		return false;
	}
	public void waitFor(By locator) {
		waitFor(locator, 0);
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

	/* ---------------------------------------------------------------------------
	Every1Counts: Windows
	--------------------------------------------------------------------------- */

	private void setParentWindowHandle(String s) {
		parentWindowHandle = s;
	}

	public String getParentWindowHandle() {
		return parentWindowHandle;
	}

	/**
	 * Asserts if a new window or tab exists.
	 *
	 * @param windowCount: the total number of window/tab before a new one is opened.
	 */
	public boolean windowNewExist(int windowCount) {
		//LOG.info("Checking if a new tab or window exists.");
		waitPageLoaded();
		try {
			Assert.assertEquals(windowCount + 1, driver.getWindowHandles().size());
		} catch (AssertionError e) {
			//LOG.info("A new tab or window does not exist.");
			return false;
		} catch (Exception e) {
			//LOG.error("Failed to determine if a new tab or window exists: Exception in newWindowExist()", e);
		}
		return true;
	}

	/**
	 * Responsible for handling new windows and tabs. Will set the driver to the
	 * new window/tab. Passes the window handle for the parent to
	 * {@link #setParentWindowHandle(String)} for switching back to previous window.
	 *
	 * @param windowHandles: a Set<String> of windowHandles
	 */
	public void windowSwitch(Set<String> windowHandles) {
		Iterator<String> iterator = windowHandles.iterator();

		String mainWindowHandle = iterator.next();
		setParentWindowHandle(mainWindowHandle);
		String newWindowHandle = iterator.next();

		try {
			driver.switchTo().window(newWindowHandle);
			driver.manage().window().setSize(new Dimension(1024, 768));
		} catch (NoSuchWindowException e) {
			//LOG.error("Failed to switch over to new window or tab: NoSuchWindowException in switchNewWindow()", e);
			Assert.fail();
		} catch (Exception e) {
			//LOG.error("Failed to switch over to new window or tab: Exception in switchNewWindow()", e);
			Assert.fail();
		}
	}

	/**
	 * Will set the driver back to the previous window. Window handle of previous window
	 * was set when switching over to new window.
	 */
	public void windowSwitchPrevious() {
		try {
			driver.switchTo().window(getParentWindowHandle());
		} catch (NoSuchWindowException e) {
			//LOG.error("Failed to switch over to previous window or tab: NoSuchWindowException in switchPreviousWindow()", e);
			Assert.fail();
		} catch (Exception e) {
			//LOG.error("Failed to switch over to previous window or tab: Exception in switchPreviousWindow()", e);
			Assert.fail();
		}
	}

	/* ---------------------------------------------------------------------------
	JavaScript popup
	--------------------------------------------------------------------------- */

	/**
	 * Utility methods for dealing with JavaScript alerts on pages.
	 */
	public boolean isAlertPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			//LOG.info("Alert present and reads, \"" + alert.getText() + "\"");
			return true;
		} catch (NoAlertPresentException e) {
			//LOG.info("Failed to locate alert and get text: NoAlertPresentException", e);
		}
		return false;
	}

	public String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			//LOG.info("Failed to locate alert and get text: NoAlertPresentException", e);
		}
		return null;
	}

	public boolean alertClickOk() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (NoAlertPresentException e) {
			//LOG.error("Failed to locate alert and click ok: NoAlertPresentException", e);
			Assert.fail();
		}
		return false;
	}

	public boolean alertDismiss() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			return true;
		} catch (NoAlertPresentException e) {
			//LOG.error("Failed to locate alert and dismiss alert: NoAlertPresentException", e);
			Assert.fail();
		}
		return false;
	}
}
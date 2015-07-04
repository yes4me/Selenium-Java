package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.Constants;

public class DriverFactory {
	private WebDriver driver = null;

	public WebDriver driver() {
		if (Constants.BROWSER.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\save\\thomas\\job\\computer\\Selenium Java\\Important code\\vendor\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (Constants.BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		return driver;
	}
}

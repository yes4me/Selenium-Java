/* ===========================================================================
Created:	2015/07/04
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object
=========================================================================== */

package pages_various;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;
import lib.BasePage;
import lib.Util;

public class P_FlickrDownloader extends BasePage implements BasicPageObject {
	private final static int PICTURES_DOWNLOADED_MAX = 5;	//Max number of pictures that will be downloaded

	@FindBy(className = "download") private WebElement downloadHREF;
	@FindBy(partialLinkText = "View all sizes") private WebElement viewAllSizeHREF;
	@FindBy(xpath = "//div[@id='allsizes-photo']/img") private WebElement mainPhotoPict;

	public P_FlickrDownloader(WebDriver driver) {
		super(driver);
	}

	@Override
	public void visit() {
		visit(Paths.FLICKR_URL);
	}

	@Override
	public boolean checkPage() {
		return compareURL(getCurrentURL(), Paths.FLICKR_URL);
	}

	public void downloadAllHiResPicts() {
		String pictURL = "";
		String pictFileName = "";
		int counter=0;

		//Wait for the page to load PICTURES_DOWNLOADED_MAX pictures
		List<WebElement> picture_WebElement = null;
		counter=0;
		while (counter++ < PICTURES_DOWNLOADED_MAX)
		{
			picture_WebElement = finds(By.className("overlay"));
			Util.wait(1000);
			if (picture_WebElement.size() >= PICTURES_DOWNLOADED_MAX)
				break;
		}

		//Get the list of URLs
		counter=0;
		List<String> picture_url = new ArrayList<String>();
		for (WebElement webElement : picture_WebElement)
		{
			picture_url.add(counter, webElement.getAttribute("href"));
			if (++counter >= PICTURES_DOWNLOADED_MAX)
				break;
		}

		//Visit the correct page and download the pictures
		for (String url : picture_url)
		{
			visit(url);
			windowsMaximize();
			click(downloadHREF);
			click(viewAllSizeHREF);
			pictURL = mainPhotoPict.getAttribute("src");
			pictFileName = FilenameUtils.getName(pictURL);
			downloadPict( pictURL, Paths.TMP_FOLDER + pictFileName);
		}
	}
}
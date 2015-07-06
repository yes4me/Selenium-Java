package locators;

import org.openqa.selenium.By;

public class L_FlickrDownloader {
	public static final By DOWNLOAD_HREF = By.className("download");
	public static final By VIEW_ALL_SIZE_HREF = By.partialLinkText("View all sizes");
	public static final By MAIN_PHOTO_PICT = By.xpath("//div[@id='allsizes-photo']/img");
}

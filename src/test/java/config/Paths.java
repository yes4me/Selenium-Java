/* ===========================================================================
Created:	2015/06/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Keep all URL locations
=========================================================================== */

package config;

import java.io.File;

public class Paths {
	public static final String HOME_FOLDER = System.getProperty("user.dir");
	public static final String CHROME_FILE = "C:\\save\\thomas\\job\\computer\\Selenium Java\\Important code\\vendor\\chromedriver.exe";

	public static final String FLICKR_URL = "https://www.flickr.com/explore";
	public static final String MORGAGECALCULATOR_URL = "http://www.mortgagecalculator.org/";
	public static final String RECTANGLES_TEST_URL = "file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "rect.html";
	public static final String SORTER_URL = "file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "sorter.html";
	public static final String TEST_PAINTING_URL = "file://" + System.getProperty("user.dir") + File.separator + "html" + File.separator + "paint.html";
}
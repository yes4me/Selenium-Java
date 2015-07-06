package edu.ucsc.extension;

import lib.DriverFactory;
import lib.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.P_RectanglesTest;

public class RectanglesTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new DriverFactory().driver();
	}
	@After
	public void tearDown() throws Exception {
		System.out.println("DONE");
		driver.quit();
	}

	@Test
	public void testIntersection() {
		P_RectanglesTest page = new P_RectanglesTest(driver);
		page.visit();
		page.checkIntersect();

		Util.wait(1);
	}
}

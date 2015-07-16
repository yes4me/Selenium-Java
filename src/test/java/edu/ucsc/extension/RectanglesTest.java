/* ===========================================================================
Created: 2015/07/05 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: Check which rectangles touch each others
=========================================================================== */

package edu.ucsc.extension;

import lib.DriverFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages_various.P_RectanglesTest;

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
		System.out.println("RUNNING TEST: testIntersection");

		P_RectanglesTest page = PageFactory.initElements(driver, P_RectanglesTest.class);
		page.visit();

		page.checkIntersect();
	}
}
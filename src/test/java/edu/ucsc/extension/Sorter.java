/* ===========================================================================
Created: 2015/07/07 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: Sort and reverse sort "rectangles"
=========================================================================== */

package edu.ucsc.extension;

import lib.DriverFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.P_Sorter;

public class Sorter {
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
	public void sortNumbers() {
		System.out.println("RUNNING TEST: sortNumbers");

		P_Sorter page = PageFactory.initElements(driver, P_Sorter.class);
		page.visit();

		//page.takeScreenshot();
		page.sort_order();
		//Util.wait(3);
		page.sort_reverseOrder();
	}
}
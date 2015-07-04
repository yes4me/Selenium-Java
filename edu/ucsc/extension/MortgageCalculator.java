/* ===========================================================================
Created: 2015/07/01 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: Test page for 
=========================================================================== */

package edu.ucsc.extension;

import java.io.File;

import lib.DriverFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.P_MortgageCalculator;
import config.Constants;

public class MortgageCalculator {
	private WebDriver driver;
	private static String outputFolder;


	@BeforeClass
	public static void before() {
		outputFolder = System.getProperty("tmp.dir") + File.separator + "tmp";
		System.out.println("OUTPUT DIR: " + outputFolder);
	}

	@AfterClass
	public static void after() {
	}
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
	public void testMortgageCalculator() {
		System.out.println("RUNNING TEST: testMortgageCalculator");

		P_MortgageCalculator page = new P_MortgageCalculator(driver);
		page.visit();
		page.takeScreenshot(Constants.TMP_FOLDER + "before.png");
		page.fillForm();
		page.submitForm();
		page.takeScreenshot(Constants.TMP_FOLDER + "after.png");
	}
}

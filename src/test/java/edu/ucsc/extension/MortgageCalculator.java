/* ===========================================================================
Created: 2015/07/01 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: See ../tmp/Assignment3.pdf
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
import org.openqa.selenium.support.PageFactory;

import pages.P_MortgageCalculator;
import config.Paths;

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

		P_MortgageCalculator page = PageFactory.initElements(driver, P_MortgageCalculator.class);
		page.visit();

		page.fillForm();
		page.takeScreenshot(Paths.TMP_FOLDER + "before.png");
		page.submitForm();
		page.waitPageLoad();
		page.takeScreenshot(Paths.TMP_FOLDER + "after.png");
	}
}

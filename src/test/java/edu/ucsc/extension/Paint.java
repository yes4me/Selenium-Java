/* ===========================================================================
Created:	2015/07/01
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	To practice actionBuilder
=========================================================================== */

package edu.ucsc.extension;

import lib.DriverFactory;
import lib.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.P_Paint;

public class Paint {
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
	public void testPaint() {
		System.out.println("RUNNING TEST: testPaint");

		P_Paint page = new P_Paint(driver);
		page.visit();

		//Face
		page.drawCircle(400, 400, 200);
		//Glasses
		page.drawRectangle(250, 300, 350, 360);
		page.drawRectangle(450, 300, 550, 360);
		page.drawLine(350, 330, 450, 330);
		//Mouth
		page.drawLine(300, 500, 500, 500);
		//Hat
		page.drawLine(200, 200, 600, 200);
		page.drawLine(200, 200, 400, 120);
		page.drawLine(400, 120, 600, 200);

		Util.wait(10);
	}
}
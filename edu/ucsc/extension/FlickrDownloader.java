package edu.ucsc.extension;

import java.io.File;

import lib.DriverFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.P_FlickrDownloader;

public class FlickrDownloader {
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
	public void flickrDownloader() {
		System.out.println("RUNNING TEST: flickrDownloader");

		P_FlickrDownloader page = new P_FlickrDownloader(driver);
		page.visit();
		page.downloadAllHiResPicts();
	}
}
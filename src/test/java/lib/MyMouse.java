/* ===========================================================================
Created:	2015/07/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Generic time related the mouse
=========================================================================== */

package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class MyMouse {
	private WebDriver driver;
	private static int mouse_x=0;
	private static int mouse_y=0;

	public MyMouse(WebDriver driver)
	{
		this.driver = driver;
	}

	public void mouseLeftClick_ByOffset(int xOffset, int yOffset) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveByOffset(xOffset, yOffset).click();

		Action action = actionBuilder.build();
		action.perform();
	}
	public void mouseLeftClick(int x, int y) {
		mouseLeftClick_ByOffset(x-mouse_x, y-mouse_y);
		mouse_x = x;
		mouse_y = y;
	}

	/*
	public void mouseDragAndDrop(By locatorOrigin, int x, int y) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy( find(locatorOrigin), x, y);
		actionBuilder.perform();
	}
	public void mouseDragAndDrop(By locatorOrigin, By locatorDestination) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.dragAndDrop( find(locatorOrigin), find(locatorDestination) );
		actionBuilder.perform();
	}
	*/
}

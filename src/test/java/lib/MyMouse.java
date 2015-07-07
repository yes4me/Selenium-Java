/* ===========================================================================
Created:	2015/07/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Generic time related the mouse
=========================================================================== */

package lib;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class MyMouse {
	private WebDriver driver;
	private static int mouseLocation_x = 0;
	private static int mouseLocation_y = 0;

	public MyMouse(WebDriver driver)
	{
		this.driver = driver;
	}

	public void mouseLeftClick_ByOffset(int xOffset, int yOffset) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveByOffset(xOffset, yOffset).click();

		Action action = actionBuilder.build();
		action.perform();
		mouseLocation_x += xOffset;
		mouseLocation_y += yOffset;
	}
	public void mouseLeftClick(int x, int y) {
		mouseLeftClick_ByOffset(x-mouseLocation_x, y-mouseLocation_y);
		mouseLocation_x = x;
		mouseLocation_y = y;
	}

	public void mouseDragAndDrop_ByOffset(WebElement webElement, int xOffset, int yOffset) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy(webElement, xOffset, yOffset);
		actionBuilder.perform();
		mouseLocation_x += xOffset;
		mouseLocation_y += yOffset;
	}
	public void mouseDragAndDrop(WebElement webElement, int x, int y) {
		Point point = webElement.getLocation();
		mouseDragAndDrop_ByOffset(webElement, x-point.x, y-point.y);
		mouseLocation_x = x;
		mouseLocation_y = y;
	}
	public void mouseDragAndDrop(WebElement webElementOrigin, WebElement webElementDestination) {
		Actions actionBuilder = new Actions(driver);
		actionBuilder.dragAndDrop(webElementOrigin, webElementDestination);
		actionBuilder.perform();

		Point point = webElementDestination.getLocation();
		mouseLocation_x = point.x;
		mouseLocation_y = point.y;
	}
}

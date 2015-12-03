/* ===========================================================================
Created:	2015/07/01
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object
=========================================================================== */

package pages_various;

import org.openqa.selenium.WebDriver;

import config.Paths;
import lib.BasePage;
import lib.MyMouse;

public class P_Paint extends BasePage implements BasicPageObject {
	private final static int CLICK_INTERVAL = 10;

	private MyMouse myMouse;

	public P_Paint(WebDriver driver) {
		super(driver);
		myMouse = new MyMouse(driver);
	}

	@Override
	public void visit() {
		visitURL(Paths.TEST_PAINTING_URL);
	}

	@Override
	public boolean checkPage() {
		return compareURL(getCurrentURL(), Paths.TEST_PAINTING_URL);
	}

	public void drawPoint(int x, int y) {
		myMouse.mouseLeftClick(x, y);
	}
	public void drawLine(int source_x, int source_y, int target_x, int target_y) {
		//Equation: y = ax + b
		int width	= target_x-source_x;
		int height	= target_y-source_y;

		if (width != 0)
		{
			int x1 = (source_x <= target_x)? source_x : target_x;
			int x2 = (source_x <= target_x)? target_x : source_x;
			int y1 = (source_x <= target_x)? source_y : target_y;
			int y2 = (source_x <= target_x)? target_y : source_y;

			double a = (double)height / (double)width;
			double b = y1 - a*x1;
			while (x1 <= x2)
			{
				myMouse.mouseLeftClick(x1, y1);
				x1 += CLICK_INTERVAL;
				y1 = (int)(a*x1 + b);
			}
			myMouse.mouseLeftClick(x2, y2);
		}
		else
		{
			int x2 = (source_y <= target_y)? target_x : source_x;
			int y1 = (source_y <= target_y)? source_y : target_y;
			int y2 = (source_y <= target_y)? target_y : source_y;

			while (y1 <= y2)
			{
				myMouse.mouseLeftClick(source_x, y1);
				y1 += CLICK_INTERVAL;
			}
			myMouse.mouseLeftClick(x2, y2);
		}
	}

	public void drawRectangle(int origin_x, int origin_y, int target_x, int target_y) {
		drawLine(origin_x, origin_y, target_x, origin_y);
		drawLine(origin_x, origin_y, origin_x, target_y);
		drawLine(target_x, origin_y, target_x, target_y);
		drawLine(origin_x, target_y, target_x, target_y);
	}
	public void drawCircle(int origin_x, int origin_y, int radius) {
		double y = 0;
		for (int x=-radius; x<=radius; x+=CLICK_INTERVAL)
		{
			//Equation: x^2 + y^2 = r^2
			y = Math.pow(radius,2) - Math.pow(x,2);
			y = Math.sqrt( Math.abs(y) );
			myMouse.mouseLeftClick(origin_x+x, origin_y-(int)y);
			myMouse.mouseLeftClick(origin_x+x, origin_y+(int)y);
		}
	}
}
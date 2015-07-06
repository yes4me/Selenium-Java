/* ===========================================================================
Created:	2015/07/05
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Object for the page object P_RectanglesTest
=========================================================================== */

package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class Rectangle {
	Point point = null;
	Dimension dimension = null;

	Rectangle(Point point, Dimension dimension) {
		this.point	= point;
		this.dimension	= dimension;
	}
	public String getInfo() {
		return "Point:"+ point.x +"/"+ point.y +"__Dimension:"+ dimension.width +"/"+ dimension.height;
	}

	public boolean CompareTo(Rectangle compareRectangle) {
		int intersection = 0;
		int r1_x = point.x;
		int r1_y = point.y;
		int r2_x = compareRectangle.point.x;
		int r2_y = compareRectangle.point.y;
		int r1_width	= dimension.width;
		int r2_width	= compareRectangle.dimension.width;
		int r1_height	= dimension.height;
		int r2_height	= compareRectangle.dimension.height;

		//Check x-axis
		if (r1_x <= r2_x)
		{
			if (r2_x-r1_x <= r1_width)
				intersection++;
		}
		else
		{
			if (r1_x-r2_x <= r2_width)
				intersection++;
		}

		//Check y-axis
		if (r1_y <= r2_y)
		{
			if (r2_y-r1_y <= r1_height)
				intersection++;
		}
		else
		{
			if (r1_y-r2_y <= r2_height)
				intersection++;
		}

		if (intersection==2)
			return true;
		return false;
	}
}

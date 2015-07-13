/* ===========================================================================
Created:	2015/07/05
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object
=========================================================================== */

package pages;

import java.util.List;

import lib.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;

public class P_RectanglesTest extends BasePage implements PageFactory {
	@FindBy(className ="rect") private List<WebElement> RECTANGLE_DIV;

	public P_RectanglesTest(WebDriver driver) {
		super(driver);
	}

	@Override
	public void visit() {
		visit(Paths.RECTANGLES_TEST_URL);
	}

	@Override
	public boolean check_page() {
		return compareURL(getCurrentURL(), Paths.RECTANGLES_TEST_URL);
	}

	public void checkIntersect() {
		int total_counter = 0;
		int counter_r1 = 0;
		int counter_r2 = 0;

		List<WebElement> rectangles = RECTANGLE_DIV;
		for (WebElement rectangle : rectangles)
		{
			Rectangle r1 = new Rectangle(rectangle.getLocation(), rectangle.getSize() );
			for (WebElement rectangle2 : rectangles)
			{
				if ( !rectangle.equals(rectangle2) )
				if (counter_r1<counter_r2) //This is the way to display only once the intersection between rectangles
				{
					Rectangle r2 = new Rectangle(rectangle2.getLocation(), rectangle2.getSize() );
					if (r1.hasIntersection(r2))
					{
						System.out.println("Rectangle "+ counter_r1 +"(" + r1.getInfo() +") intersect with rectangle "+ counter_r2 +"(" + r2.getInfo() +")");
						total_counter++;
					}
				}
				counter_r2++;
			}
			counter_r1++;
			counter_r2=0;
		}

		if (total_counter>0)
			System.out.println("SUMMARY: total number of intersections: "+ total_counter);
	}
}
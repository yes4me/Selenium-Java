package pages;

import java.util.List;

import lib.BasePage;
import lib.MyMouse;
import locators.L_Sorter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Paths;

public class P_Sorter extends BasePage implements PageFactory {
	MyMouse myMouse;

	public P_Sorter(WebDriver driver) {
		super(driver);
		myMouse = new MyMouse(driver);
	}

	@Override
	public void visit() {
		visit(Paths.SORTER_URL);
	}

	@Override
	public boolean check_page() {
		return compareURL(getCurrentURL(), Paths.SORTER_URL);
	}

	public void sort_order() {
		List<WebElement> rectangles = finds( L_Sorter.SLOT_LI );
		int counter = 0;

		for (int i=0; i<rectangles.size(); i++)
		{
			WebElement rectangle = rectangles.get(counter);

			counter++;
			for (WebElement rectangle2 : rectangles)
			{
				int id = Integer.parseInt( rectangle2.getText() );
				if (id == counter)
				{
					myMouse.mouseDragAndDrop(rectangle2, rectangle);
					rectangles = finds( L_Sorter.SLOT_LI );
					break;
				}
			}
		}
	}
	public void sort_reverseOrder() {
		List<WebElement> rectangles = finds( L_Sorter.SLOT_LI );
		int counter = 0;

		for (int i=0; i<rectangles.size(); i++)
		{
			WebElement rectangle = rectangles.get(counter);

			counter++;
			for (WebElement rectangle2 : rectangles)
			{
				int id = Integer.parseInt( rectangle2.getText() );
				if (id == rectangles.size()-counter+1)
				{
					myMouse.mouseDragAndDrop(rectangle2, rectangle);
					rectangles = finds( L_Sorter.SLOT_LI );
					break;
				}
			}
		}
	}
}
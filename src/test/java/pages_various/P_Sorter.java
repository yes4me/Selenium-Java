package pages_various;

import java.util.List;

import lib.BasePage;
import lib.MyMouse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;

public class P_Sorter extends BasePage implements BasicPageObject {
	//@FindBy(className ="ui-state-default ui-sortable-handle") private WebElement slot_LI;	//NOT ALLOWED
	@FindBy(tagName = "li") private List<WebElement> slot_LI;

	private MyMouse myMouse;

	public P_Sorter(WebDriver driver) {
		super(driver);
		myMouse = new MyMouse(driver);
	}

	@Override
	public void visit() {
		visit(Paths.SORTER_URL);
	}

	@Override
	public boolean checkPage() {
		return compareURL(getCurrentURL(), Paths.SORTER_URL);
	}

	public void sort_order() {
		List<WebElement> rectangles = slot_LI;
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
					rectangles = slot_LI;
					break;
				}
			}
		}
	}
	public void sort_reverseOrder() {
		List<WebElement> rectangles = slot_LI;
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
					rectangles = slot_LI;
					break;
				}
			}
		}
	}
}
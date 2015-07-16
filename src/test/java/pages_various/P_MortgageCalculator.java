/* ===========================================================================
Created:	2015/07/01
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object
=========================================================================== */

package pages_various;

import lib.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;

public class P_MortgageCalculator extends BasePage implements BasicPageObject {
	@FindBy(name = "param[homevalue]") private WebElement homeValue_input;
	@FindBy(id = "loanamt") private WebElement loanAmount_input;
	@FindBy(id = "intrstsrate") private WebElement interestRate_input;
	@FindBy(name = "param[start_month]") private WebElement startDateMonth_option;
	@FindBy(name = "param[start_year]") private WebElement startDateYear_option;
	@FindBy(partialLinkText = "Output parameters") private WebElement parameters_link;
	@FindBy(name = "param[draw_charts]") private WebElement drawCharts_checkbox;				//Draw charts
	@FindBy(name = "param[show_m_vs_w]") private WebElement monthlyPayment_checkbox;			//Monthly vs bi-weekly payments
	@FindBy(name = "param[show_annual]") private WebElement showYearAmortization_checkbox;		//Show annual amortization table
	@FindBy(name = "param[show_monthly]") private WebElement showMonthlyAmortization_checkbox;	//Show monthly amortization table
	@FindBy(name = "cal") private WebElement submit_button;
	@FindBy(id = "monthlyschedule") private WebElement monthlySchedule_div;

	public P_MortgageCalculator(WebDriver driver) {
		super(driver);
	}

	@Override
	public void visit() {
		visit(Paths.MORGAGECALCULATOR_URL);
	}

	@Override
	public boolean checkPage() {
		return compareURL(getCurrentURL(), Paths.MORGAGECALCULATOR_URL);
	}

	public void waitPageLoad() {
		waitFor(monthlySchedule_div);
	}

	public void fillForm() {
		typeNew(homeValue_input, "600000");
		typeNew(loanAmount_input, "500000");
		typeNew(interestRate_input, "5");
		select(startDateMonth_option, "Jan");
		select(startDateYear_option, "2016");

		click(parameters_link);
		click(drawCharts_checkbox, false);
		click(monthlyPayment_checkbox, true);
		click(showYearAmortization_checkbox, true);
		click(showMonthlyAmortization_checkbox, true);
	}
	public void submitForm() {
		click(submit_button);
	}
}
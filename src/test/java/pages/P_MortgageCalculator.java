/* ===========================================================================
Created:	2015/07/01
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	Page object
=========================================================================== */

package pages;

import lib.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;

public class P_MortgageCalculator extends BasePage implements PageFactory {
	@FindBy(name ="param[homevalue]") private WebElement HOMEVALUE_INPUT;
	@FindBy(id ="loanamt") private WebElement LOANAMOUNT_INPUT;
	@FindBy(id ="intrstsrate") private WebElement INTERESTRATE_INPUT;
	@FindBy(name ="param[start_month]") private WebElement STARTDATE_MONTH_OPTION;
	@FindBy(name ="param[start_year]") private WebElement STARTDATE_YEAR_OPTION;
	@FindBy(partialLinkText ="Output parameters") private WebElement PARAMETERS_LINK;
	@FindBy(name ="param[draw_charts]") private WebElement DRAWCHARTS_CHECKBOX;				//Draw charts
	@FindBy(name ="param[show_m_vs_w]") private WebElement MONTHLYPAYMENT_CHECKBOX;			//Monthly vs bi-weekly payments
	@FindBy(name ="param[show_annual]") private WebElement SHOWYEARAMORTIZATION_CHECKBOX;	//Show annual amortization table
	@FindBy(name ="param[show_monthly]") private WebElement SHOWMONTHLYAMORTIZATION_CHECKBOX;	//Show monthly amortization table
	@FindBy(name ="cal") private WebElement SUBMIT_BUTTON;
	@FindBy(id ="monthlyschedule") private WebElement MONTHLYSCHEDULE_DIV;

	public P_MortgageCalculator(WebDriver driver) {
		super(driver);
	}

	@Override
	public void visit() {
		visit(Paths.MORGAGECALCULATOR_URL);
	}

	@Override
	public boolean check_page() {
		return compareURL(getCurrentURL(), Paths.MORGAGECALCULATOR_URL);
	}

	public void waitPageLoad() {
		waitFor(MONTHLYSCHEDULE_DIV);
	}

	public void fillForm() {
		typeNew(HOMEVALUE_INPUT, "600000");
		typeNew(LOANAMOUNT_INPUT, "500000");
		typeNew(INTERESTRATE_INPUT, "5");
		select(STARTDATE_MONTH_OPTION, "Jan");
		select(STARTDATE_YEAR_OPTION, "2016");

		click(PARAMETERS_LINK);
		click(DRAWCHARTS_CHECKBOX, false);
		click(MONTHLYPAYMENT_CHECKBOX, true);
		click(SHOWYEARAMORTIZATION_CHECKBOX, true);
		click(SHOWMONTHLYAMORTIZATION_CHECKBOX, true);
	}
	public void submitForm() {
		click(SUBMIT_BUTTON);
	}
}
package pages;

import org.openqa.selenium.WebDriver;

import config.Paths;
import lib.BasePage;
import locators.L_MortgageCalculator;

public class P_MortgageCalculator extends BasePage implements PageFactory {
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
	
	public void fillForm() {
		typeNew(L_MortgageCalculator.HOMEVALUE_INPUT, "600000");
		typeNew(L_MortgageCalculator.LOANAMOUNT_INPUT, "500000");
		typeNew(L_MortgageCalculator.INTERESTRATE_INPUT, "5");
		select(L_MortgageCalculator.STARTDATE_MONTH_OPTION, "Jan");
		select(L_MortgageCalculator.STARTDATE_YEAR_OPTION, "2016");

		click(L_MortgageCalculator.PARAMETERS_LINK);
		click(L_MortgageCalculator.DRAWCHARTS_CHECKBOX, false);
		click(L_MortgageCalculator.MONTHLYPAYMENT_CHECKBOX, true);
		click(L_MortgageCalculator.SHOWYEARAMORTIZATION_CHECKBOX, true);
		click(L_MortgageCalculator.SHOWMONTHLYAMORTIZATION_CHECKBOX, true);
	}
	public void submitForm() {
		click(L_MortgageCalculator.SUBMIT_BUTTON);
	}
}

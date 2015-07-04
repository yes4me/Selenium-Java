package locators;

import org.openqa.selenium.By;

public class L_MortgageCalculator {
	public static final By HOMEVALUE_INPUT = By.name("param[homevalue]");
	public static final By LOANAMOUNT_INPUT = By.id("loanamt");
	public static final By INTERESTRATE_INPUT = By.id("intrstsrate");
	public static final By STARTDATE_MONTH_OPTION = By.name("param[start_month]");
	public static final By STARTDATE_YEAR_OPTION = By.name("param[start_year]");
	public static final By PARAMETERS_LINK = By.partialLinkText("Output parameters");
	public static final By DRAWCHARTS_CHECKBOX = By.name("param[draw_charts]");		//Draw charts
	public static final By MONTHLYPAYMENT_CHECKBOX = By.name("param[show_m_vs_w]");	//Monthly vs bi-weekly payments
	public static final By SHOWYEARAMORTIZATION_CHECKBOX = By.name("param[show_annual]");	//Show annual amortization table
	public static final By SHOWMONTHLYAMORTIZATION_CHECKBOX = By.name("param[show_monthly]");	//Show monthly amortization table
	public static final By SUBMIT_BUTTON = By.name("cal");
}
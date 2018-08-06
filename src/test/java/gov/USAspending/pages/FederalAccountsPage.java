package gov.USAspending.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.USAspending.utilities.Driver;

public class FederalAccountsPage extends HomePage {

	public FederalAccountsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//div[@class = 'pagination__totals']")
	public WebElement federalAccountsResultsCount;

	@FindBy(xpath = "//h2")
	public WebElement findAFederalAccountProfileHeader;

	@FindBy(xpath = "//div[@class = 'header-label']")
	public WebElement federalAccountsAccountNumber;

	@FindBy(xpath = "//div[@class='results-table-cell__content']")
	public WebElement federalAccountNumberFirstResult;

	@FindBy(xpath = "//tbody[@class='results-table__body']/tr/td[1]/div/div")
	public List<WebElement> listOfAccountNumbers;

	@FindBy(xpath = "//table/thead/tr[1]/td[1]/div[@class='award-result-header-cell ']/div[1]/div[1]/div[2]/button[2]")
	public WebElement accountNumberButtom;
}

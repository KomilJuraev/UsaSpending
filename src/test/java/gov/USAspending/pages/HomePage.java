package gov.USAspending.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.USAspending.utilities.Driver;

public class HomePage {

	public HomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//*[.='Profiles']")
	public WebElement profiles;

	@FindBy(xpath = "(//a[@class = 'nav-children__link '])[3]")
	public WebElement agencies;

	@FindBy(xpath = "(//a[@class = 'nav-children__link '])[4]")
	public WebElement federalAccounts;

	@FindBy(xpath = "(//a[@class = 'nav-children__link '])[5]")
	public WebElement states;
	

	@FindBy(xpath = "//div[@class='results-table-message']")
	public WebElement noResultFoundMsg;

	@FindBy(xpath = "//ul[@class='full-menu__list']/li[3]/div[1]/button/div[1]")
	public WebElement profilesButton;
}

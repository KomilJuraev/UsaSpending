package gov.USAspending.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.USAspending.utilities.Driver;

public class AgenciesPage extends HomePage {

	public AgenciesPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//input")
	public WebElement agencySearchBox;

	@FindBy(xpath = "//div[@class = 'results-count']")
	public WebElement agencyResultsCount;

	@FindBy(xpath = "//h2")
	public WebElement findAnAgencyProfileHeader;

	@FindBy(xpath = "//div[@class= 'header-label']")
	public WebElement agencyName;
	// <<<<<<< HEAD

	// <<<<<<< HEAD
	@FindBy(xpath = "//tbody/tr/td[1]")
	public WebElement departmentOfDefenceResult;

	// @FindBy(partialLinkText = "Access Board ")
	// =======
	// @FindBy(xpath = "//td[@class='row-odd'][1]")
	// >>>>>>> 117d6d71d33b4fa6a2e5d112a93504a7291d3abc
	// =======

	@FindBy(xpath = "//td[@class='row-odd'][1]")
	// >>>>>>> 70eda607564df707ef4e6bd58f47e32bb15f18e9
	public WebElement agencyAccessBoard;

	@FindBy(xpath = "//a[@href='/#/agency/1173']")
	public WebElement agencyDepartmentOfDefenseLink;

	@FindBy(xpath = "(//div[@class = 'cell-content'])[5]")
	public WebElement agencyDepartmentOfDefenseAmount;

	@FindBy(xpath = "(//div[@class = 'cell-content'])[6]")
	public WebElement departmentOfDefensePercentOfTotal;

	@FindBy(partialLinkText = "Department of Defense")
	public WebElement DepartmentOfDefenseSearchResult;

	
	@FindBy(xpath = "//div[@class='results-count']")
	public WebElement negativeSearchResult;
			
}



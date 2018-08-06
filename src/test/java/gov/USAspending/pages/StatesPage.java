package gov.USAspending.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.USAspending.utilities.Driver;

public class StatesPage extends HomePage{

	public StatesPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//input")
	public WebElement statesSearchBox;

	@FindBy(xpath = "//h2")
	public WebElement FindAStateProfileHeader;

	@FindBy(xpath = "//div[@class = 'results-count']")
	public WebElement statesResultsCount;

	@FindBy(xpath = "//div[@class = 'header-cell__title']")
	public WebElement stateOrTerritoryName;

	@FindBy(partialLinkText = "Alabama (AL)")
	public WebElement stateOfAlabama;

	
}

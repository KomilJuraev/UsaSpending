package gov.USAspending.utilities.tests.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import gov.USAspending.pages.AgenciesPage;
import gov.USAspending.pages.FederalAccountsPage;
import gov.USAspending.pages.HomePage;
import gov.USAspending.pages.StatesPage;
import gov.USAspending.utilities.BrowserUtils;
import gov.USAspending.utilities.ConfigurationReader;
import gov.USAspending.utilities.Driver;
import gov.USAspending.utilities.tests.TestBase;

public class AllTestCasesCombined extends TestBase {

	HomePage homePage;
	StatesPage statesPage;
	AgenciesPage agenciesPage;
	FederalAccountsPage federalAccountsPage;

	@Test(priority = 0)
	public void smokeTest() {

		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		Assert.assertEquals(actualTitle, expectedTitle);

		// Step 2
		homePage = new HomePage();
		BrowserUtils.waitFor(5);
		BrowserUtils.hover(homePage.profiles);

		// Step 3
		homePage.agencies.click();
		agenciesPage = new AgenciesPage();
		Assert.assertTrue(agenciesPage.findAnAgencyProfileHeader.isDisplayed());

		// Step 4
		Driver.getDriver().navigate().back();

		BrowserUtils.waitFor(5);
		BrowserUtils.hover(homePage.profiles);

		federalAccountsPage = new FederalAccountsPage();
		BrowserUtils.waitFor(5);
		homePage.federalAccounts.click();
		BrowserUtils.waitFor(10);
		Assert.assertTrue(federalAccountsPage.findAFederalAccountProfileHeader.isDisplayed());
		Driver.getDriver().navigate().back();
		BrowserUtils.waitFor(5);
		BrowserUtils.hover(homePage.profiles);
		statesPage = new StatesPage();
		BrowserUtils.waitFor(5);
		statesPage.states.click();
		BrowserUtils.waitFor(10);
		Assert.assertTrue(statesPage.FindAStateProfileHeader.isDisplayed());
	}

	// test case TC001 Agencies SearchBox - Positive

	@Test(priority = 1)
	public void agenciesSearchPos() {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		Assert.assertEquals(actualTitle, expectedTitle);
		homePage = new HomePage();
		BrowserUtils.waitFor(5);
		BrowserUtils.hover(homePage.profiles);
		homePage.federalAccounts.click();
		BrowserUtils.waitFor(5);
		federalAccountsPage = new FederalAccountsPage();
		Assert.assertTrue(federalAccountsPage.findAFederalAccountProfileHeader.isDisplayed());

	}

	@Test(priority = 2)
	// test case TC002 verify total result
	public void agenciesTotalResult() {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.agencies.click();
		agenciesPage = new AgenciesPage();
		BrowserUtils.waitFor(5);
		Assert.assertEquals(agenciesPage.agencyResultsCount.getText(), "97 results");
	}

	// TC003 Search Boxes

	@Test(priority = 3)
	public void agenciesSearchBox() {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.agencies.click();
		BrowserUtils.waitFor(5);
		agenciesPage = new AgenciesPage();
		BrowserUtils.waitFor(5);
		agenciesPage.agencySearchBox.sendKeys("Department of Defense");
		BrowserUtils.waitFor(5);
		String expected = "Department of Defense";

		String actual = agenciesPage.DepartmentOfDefenseSearchResult.getText();
		BrowserUtils.waitFor(5);
		Assert.assertTrue(actual.contains(expected));

	}

	// TC004 Negative Scenario- Invalid Search
	@Test(priority = 4)
	public void agenciesSearchBoxNeg() {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.agencies.click();
		BrowserUtils.waitFor(5);
		agenciesPage = new AgenciesPage();
		agenciesPage.agencySearchBox.sendKeys("deppart");
		String expected = "0 results";
		BrowserUtils.waitFor(5);
		String actual = agenciesPage.negativeSearchResult.getText();
		BrowserUtils.waitFor(5);
		Assert.assertTrue(actual.contains(expected));

	}

	@Test(priority = 7)
	public void statesPageHeader() {
		homePage = new HomePage();
		statesPage = new StatesPage();

		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

		BrowserUtils.hover(homePage.profilesButton);

		homePage.states.click();

		WebElement header = statesPage.FindAStateProfileHeader;
		assertTrue(header.isDisplayed());

	}

	@Test(priority = 8)
	public void agenciesPageHeader() {
		homePage = new HomePage();
		agenciesPage = new AgenciesPage();

		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

		BrowserUtils.hover(homePage.profilesButton);

		WebElement agencies = homePage.agencies;
		assertTrue(agencies.isDisplayed());
		WebElement federalAccounts = homePage.federalAccounts;
		assertTrue(federalAccounts.isDisplayed());
		WebElement states = homePage.states;
		assertTrue(states.isDisplayed());

		homePage.agencies.click();

		WebElement header = agenciesPage.findAnAgencyProfileHeader;
		assertTrue(header.isDisplayed());

		String actualText = agenciesPage.agencySearchBox.getAttribute("placeholder");
		String expectedText = "Start typing to find an agency...";
		assertEquals(actualText, expectedText);
	}

	@Test(priority = 9)
	public void accountNumberInDescendingOrder() throws InterruptedException {
		homePage = new HomePage();
		federalAccountsPage = new FederalAccountsPage();

		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

		BrowserUtils.hover(homePage.profilesButton);
		Thread.sleep(1500);
		homePage.federalAccounts.click();

		WebElement header = federalAccountsPage.findAFederalAccountProfileHeader;
		assertTrue(header.isDisplayed());

		Thread.sleep(1500);
		federalAccountsPage.accountNumberButtom.click();
		Thread.sleep(1500);

		List<WebElement> accountNumbers = federalAccountsPage.listOfAccountNumbers;
		List<String> accountNumbersString = new ArrayList<>();

		for (WebElement element : accountNumbers) {
			accountNumbersString.add(element.getText());
		}
		assertEquals(accountNumbersString.get(0), "581-5578");

		// ---------------------------putting original string list into number list
		List<Integer> accountNumbersInteger = new ArrayList<>();
		for (String str : accountNumbersString) {
			str = str.replace("-", "");
			int n = Integer.valueOf(str);
			accountNumbersInteger.add(n);
		}

		// ------------------------------putting numbers into descending order
		int num = accountNumbersInteger.get(0);
		List<Integer> descendingOrderChecker = new ArrayList<>();
		for (int i = 0; i < accountNumbersInteger.size(); i++) {
			int num2 = accountNumbersInteger.get(i);

			if (num2 <= num) {
				descendingOrderChecker.add(num2);
			}
		}
		assertEquals(accountNumbersInteger, descendingOrderChecker);

	}

	@Test(priority = 10)
	public void federalAccountPageHeader() {
		homePage = new HomePage();
		federalAccountsPage = new FederalAccountsPage();

		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

		BrowserUtils.hover(homePage.profilesButton);

		WebElement header = federalAccountsPage.findAFederalAccountProfileHeader;
		assertTrue(header.isDisplayed());
	}

	@Test(priority = 11)
	public void TestCases11() throws InterruptedException {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		Assert.assertEquals(actualTitle, expectedTitle);

		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.federalAccounts.click();
		federalAccountsPage = new FederalAccountsPage();
		BrowserUtils.waitForVisibility(federalAccountsPage.federalAccountsResultsCount, 5);
		assertEquals(federalAccountsPage.federalAccountsResultsCount.getText(), "1-50 of 1713 results");
		BrowserUtils.hover(homePage.profiles);
		homePage.agencies.click();
		agenciesPage = new AgenciesPage();
		BrowserUtils.waitFor(5);
		agenciesPage.agencyName.click();
		Thread.sleep(1500);
		assertEquals(agenciesPage.agencyAccessBoard.getText(), "Access Board");
	}

	@Test(priority = 14) // TC014
	public void verifyFirstState() {

		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.states.click();
		statesPage = new StatesPage();
		assertTrue(statesPage.FindAStateProfileHeader.isDisplayed());
		// BrowserUtils.waitForVisibility(statesPage.stateOfAlabama, 5);
		BrowserUtils.waitFor(5);
		assertTrue(statesPage.stateOfAlabama.isDisplayed());

	}

	@Test(priority = 15) // TC015
	public void verifyStateTitle() {
		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.states.click();
		statesPage = new StatesPage();
		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);

	}

	@Test(priority = 16) // TC016
	public void stateSearch() {
		Driver.getDriver().get("https://www.usaspending.gov/#/state");
		statesPage = new StatesPage();
		statesPage.statesSearchBox.click();
		statesPage.statesSearchBox.sendKeys("Iowa");
		BrowserUtils.waitFor(3);
		String actualResults = statesPage.statesResultsCount.getText();
		String expectedResults = "1 result";
		assertEquals(actualResults, expectedResults);

	}

	@Test(priority = 17) // TC0017
	public void verifyStateTotalResults() {
		String actualTitle = Driver.getDriver().getTitle();
		String expectedTitle = "USAspending.gov";
		assertEquals(actualTitle, expectedTitle);
		homePage = new HomePage();
		BrowserUtils.hover(homePage.profiles);
		homePage.states.click();
		statesPage = new StatesPage();
		BrowserUtils.waitFor(3);
		String actualResults = statesPage.statesResultsCount.getText();
		String expectedResults = "56 results";
		assertEquals(actualResults, expectedResults);

	}
}

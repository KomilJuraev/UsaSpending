package SmokeTest;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import gov.USAspending.pages.AgenciesPage;
import gov.USAspending.pages.FederalAccountsPage;
import gov.USAspending.pages.HomePage;
import gov.USAspending.pages.StatesPage;
import gov.USAspending.utilities.BrowserUtils;
import gov.USAspending.utilities.ConfigurationReader;
import gov.USAspending.utilities.Driver;
import gov.USAspending.utilities.tests.TestBase;

public class Tests extends TestBase {

	HomePage homePage;
	AgenciesPage agenciesPage;
	FederalAccountsPage fedAccounts;
	StatesPage statesPage;

	@Test
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

		fedAccounts = new FederalAccountsPage();
		BrowserUtils.waitFor(5);
		homePage.federalAccounts.click();
		BrowserUtils.waitFor(10);
		Assert.assertTrue(fedAccounts.findAFederalAccountProfileHeader.isDisplayed());
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

	@Test
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
		fedAccounts = new FederalAccountsPage();
		Assert.assertTrue(fedAccounts.findAFederalAccountProfileHeader.isDisplayed());

	}

	@Test
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

	@Test
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
	@Test
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
}

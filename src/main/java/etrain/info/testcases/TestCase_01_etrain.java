package etrain.info.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import etrain.info.pages.SearchTrainsPage;
import etrain.info.testngbase.TestNgBase_etrain;

@Listeners(etrain.info.seleniumbase.CustomListener.class)

public class TestCase_01_etrain extends TestNgBase_etrain {

	@BeforeTest
	public void setValues() {
		excelFileName = "etrain";
		testCaseName = "TestCase_01_etrain";
		testDescription = "Search Trains TestCase ";
		nodes = "Search Module";
		authors = "Bhuvaneswari Govindarajan";
		category = "Smoke";
	}

	@Test(dataProvider = "fetch")
	public void searchTrainsPage(String sourceStation, String sourceValue, String destinationStation,
			String destinationValue, int daysToAdd, String borderColor, String expectedVerificationMessage)
			throws InterruptedException, Exception {

		new SearchTrainsPage(driver, node, test)
		.enterSourceStation(sourceStation)
		.selectSourceStationValue(sourceValue)
		.enterdestinationStation(destinationStation)
		.selectDestinationStationValue(destinationValue)
		.currentDateColor(borderColor)
		.clickDatePicker(daysToAdd)
		.clickgetTrainsButton()
		.findingFastestTrain()
		.verifyValidationMessage(expectedVerificationMessage);

	}
}

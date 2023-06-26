package etrain.info.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import etrain.info.pages.SearchTrainsPage;
import etrain.info.testngbase.TestNgBase_etrain;

public class TestCase_01_etrain extends TestNgBase_etrain {
	
	@BeforeTest
	public void setValues() {
		testCaseName = "TestCase_01_etrain";
        testDescription = "Search Trains TestCase ";
        nodes = "Search Module";
        authors = "Bhuvaneswari Govindarajan";
        category = "Smoke";
	}

	@Test
	public void searchTrainsPage() throws InterruptedException, Exception {

		new SearchTrainsPage(driver,node,test)
		.enterSourceStation()
		.selectSourceStationValue()
		.enterdestinationStation()
		.selectDestinationStationValue()
		.currentDateColor()
		.clickDatePicker()
		.clickgetTrainsButton()
		.calculateFastestTrain()
		.getVerificationMessage();

	}
}

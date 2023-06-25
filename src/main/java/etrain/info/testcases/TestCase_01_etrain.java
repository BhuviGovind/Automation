package etrain.info.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import etrain.info.pages.SearchTrainsPage;
import etrain.info.testngbase.TestNgBase_etrain;

public class TestCase_01_etrain extends TestNgBase_etrain {

	@BeforeTest
	public void setValues() {

	}

	@Test
	public void searchTrainsPage() throws InterruptedException, Exception {

		new SearchTrainsPage(driver)
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

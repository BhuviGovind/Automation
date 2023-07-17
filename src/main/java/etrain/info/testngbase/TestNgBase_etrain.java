package etrain.info.testngbase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import etrain.info.seleniumbase.SeleniumBase_etrain;
import utils.DataInputProvider;

@Listeners(etrain.info.seleniumbase.CustomListener.class)
public class TestNgBase_etrain extends SeleniumBase_etrain {

	public String excelFileName;

	@BeforeMethod
	@Parameters({ "url" })
	public void beforeMethod(String url) {
		invokeApp("firefox", url);
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		closeApp();
	}

	@DataProvider(name = "fetch")
	public Object[][] sendData() {
		return DataInputProvider.readExcelData(excelFileName);

	}
}

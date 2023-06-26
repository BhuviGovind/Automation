package etrain.info.testngbase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import etrain.info.seleniumbase.SeleniumBase_etrain;

public class TestNgBase_etrain extends SeleniumBase_etrain {

	@BeforeMethod
	@Parameters({ "url" })
	public void beforeMethod(String url) {
		invokeApp("firefox", url);
	}

    @AfterMethod
    public void afterMethod() throws InterruptedException {
    	
            closeApp();
         }
}

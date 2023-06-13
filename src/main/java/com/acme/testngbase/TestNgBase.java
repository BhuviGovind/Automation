package com.acme.testngbase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.acme.seleniumbase.SeleniumBase;

import utils.DataInputProvider;

public class TestNgBase extends SeleniumBase {

	public String excelFileName;
	  
    @BeforeMethod
    @Parameters({"url"})
    public void beforeMethod(String url) {
        invokeApp("firefox",url);
    }
    
    @AfterMethod
    public void afterMethod() {
            closeApp();
         }

    @DataProvider(name="fetchData")
    public String[][] sendData(){
    	return DataInputProvider.readExcelData(excelFileName);
    	
    }
}

package com.acme.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.acme.pages.LoginPage;
import com.acme.testngbase.TestNgBase;

public class TestCase_No_01 extends TestNgBase {
	
	@BeforeTest
	public void setValues() {
		excelFileName = "acmedetails";
		testCaseName = "TestCase_No_01";
        testDescription = "Login TestCase ";
        nodes = "Login Module";
        authors = "Bhuvaneswari Govindarajan";
        category = "Smoke";
	}

	@Test(dataProvider="fetchData")
	public void LoginPage(String userName, String password, String VendorTaxID, String Name, String Address, String City, String Country) throws InterruptedException {
		
		new LoginPage(driver,node,test)
	
			.userName(userName)
			.password(password)
			.tabKey()
			.clickButton()
			.moveToVendorsButton()
			.clickAddVendor()
			.enterVendorID(VendorTaxID)
			.enterNameOfVendor(Name)
			.enterAddressOfVendor(Address)
			.enterCityOfVendor(City)
			.enterCountryOfVendor(Country)
			.clickAdd()
			.alerts()
			.clickLogOutButton();
         
}
}
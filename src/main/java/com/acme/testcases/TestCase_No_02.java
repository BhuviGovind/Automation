package com.acme.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.acme.pages.LoginPage;
import com.acme.testngbase.TestNgBase;

public class TestCase_No_02 extends TestNgBase{
	
	@BeforeTest
	public void setValues() {
		excelFileName = "acmedetails";
		testCaseName = "TestCase_No_02";
        testDescription = "Login TestCase ";
        nodes = "Login Module";
        authors = "Bhuvaneswari Govindarajan";
        category = "Smoke";
	}

	@Test(dataProvider="fetchData")
	public void LoginPage(String userName, String password, String Name, String VendorTaxID, String Address, String City, String Country) throws InterruptedException {
		
		new LoginPage(driver,node,test)
	
			.userName(userName)
			.password(password)
			.tabKey()
			.clickButton()
			.moveToVendorsButton()
			.clickSearchVendor()
			.searchByVendorName(Name)
			.clickSearchButton()
			.verifyVendorName(Name)
			.verifyTaxId(VendorTaxID)
			.verifyAddressData(Address)
			.verifyCityData(City)
			.verifyCountryData(Country)
			.clickLogOutButton();

}
	}

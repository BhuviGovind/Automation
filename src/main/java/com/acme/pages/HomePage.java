package com.acme.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.acme.testngbase.TestNgBase;

public class HomePage extends TestNgBase {
	
	public HomePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver,this);
        }
	//****************** For adding vendors ******************//
	
	    @FindBy(how=How.XPATH ,using="//div[@class='dropdown'][5]")private WebElement vendorsButton;
	    @FindBy(how=How.XPATH ,using="//a[contains(text(),'Add Vendor')]")private WebElement addVendor;
	    @FindBy(how=How.XPATH ,using="//input[@id='vendorTaxId']")private WebElement vendorID;
	    @FindBy(how=How.XPATH ,using="//input[@id='name']")private WebElement enterName;
	    @FindBy(how=How.XPATH ,using="//input[@id='address']")private WebElement enterAddress;
	    @FindBy(how=How.XPATH ,using="//input[@id='city']")private WebElement enterCity;
	    @FindBy(how=How.XPATH ,using="//input[@id='country']")private WebElement enterCountry;
	    @FindBy(how=How.XPATH ,using="//button[@id='addVendor']")private WebElement clickAdd;
    
    
	//****************** For searching vendors ******************//
    
	    @FindBy(how=How.XPATH ,using="//a[contains(text(),'Search for Vendor')]")private WebElement searchVendor;
	    @FindBy(how=How.XPATH ,using="//input[@id='vendorName']")private WebElement searchVendorName;
	    @FindBy(how=How.XPATH ,using="//button[@id='buttonSearch']")private WebElement clickSearch;
	    @FindAll({
	    	@FindBy(how=How.XPATH ,using="//table[@class='table']//tr//td[1]")})private List<WebElement> vendorNameData;
	    @FindAll({
	    	@FindBy(how=How.XPATH ,using="//table[@class='table']//tr//td[2]")})private List<WebElement> taxData;
	    @FindAll({
	    	@FindBy(how=How.XPATH ,using="//table[@class='table']//tr//td[3]")})private List<WebElement> addressData;
	    @FindAll({
	    	@FindBy(how=How.XPATH ,using="//table[@class='table']//tr//td[4]")})private List<WebElement> cityData;
	    @FindAll({
	    	@FindBy(how=How.XPATH ,using="//table[@class='table']//tr//td[5]")})private List<WebElement> countryData;

	//****************** LogOut action ******************//

    @FindBy(how=How.XPATH ,using="//a[@href='https://acme-test.uipath.com/logout']")private WebElement clickLogOut;
    
	//****************** For searching vendors methods ******************//
    
   	public HomePage clickSearchVendor() {
		click(searchVendor);
        return this;
    }
   	public HomePage clickSearchButton() {
		click(clickSearch);
        return this;
    }

    public HomePage verifyVendorName(String data) {
    	for (WebElement i:vendorNameData) {
    		System.out.println("Vendor name when adding a new entry : "+data);
    		System.out.println("Saved Data:"+i.getText());
    		if(i.getText().equalsIgnoreCase(data)) {
    			System.out.println("The Vendor name entered is same as the one added");
    			System.out.println();

    		}else {
    			System.out.println("The Vendor name entered is not the same as the one added");
    			System.out.println();
    		}	
		}
		return this;
    }
    public HomePage verifyTaxId(String data) {
    	for (WebElement i:taxData) {
    		System.out.println("Vendor TaxID when adding a new entry : "+data);
    		System.out.println("Saved Data:"+i.getText());
    		if(i.getText().equalsIgnoreCase(data)) {
    			System.out.println("The Vendor TaxID entered is same as the one added");
    			System.out.println();

    		}else {
    			System.out.println("The Vendor TaxID entered is not the same as the one added");
    			System.out.println();

    		}
	}
    	return this;
    }
    public HomePage verifyAddressData(String data) {
    	for (WebElement i:addressData) {
    		System.out.println("Vendor Address when adding a new entry : "+data);
    		System.out.println("Saved Data:"+i.getText());
			if(i.getText().equalsIgnoreCase(data)) {
    			System.out.println("The Vendor Address entered is same as the one added");
    			System.out.println();

    		}else {
    			System.out.println("The Vendor Address entered is not the same as the one added");
    			System.out.println();

    		}
	}
		return this;
    }
    public HomePage verifyCityData(String data) {
    	for (WebElement i:cityData) {
    		System.out.println("Vendor City when adding a new entry : "+data);
    		System.out.println("Saved Data:"+i.getText());
			if(i.getText().equalsIgnoreCase(data)) {
    			System.out.println("The Vendor Address entered is same as the one added");
    			System.out.println();

    		}else {
    			System.out.println("The Vendor City entered is not the same as the one added");
    			System.out.println();

    		}
	}
    	return this;
    }
    public HomePage verifyCountryData(String data) {
    	for (WebElement i:countryData) {
    		System.out.println("Vendor Country when adding a new entry : "+data);
    		System.out.println("Saved Data:"+i.getText());
			if(i.getText().equalsIgnoreCase(data)) {
    			System.out.println("The Vendor Address entered is same as the one added");
    			System.out.println();

    		}else {
    			System.out.println("The Vendor Country entered is not the same as the one added");
    			System.out.println();
    		}
	}
    	return this;
    }
    
	//****************** For adding vendors methods ******************//

   	public HomePage moveToVendorsButton() {
        actions(vendorsButton);
        return this;
    }
  	public HomePage clickAddVendor() {
		click(addVendor);
        return this;
    }
   	public HomePage enterVendorID(String data) {
        enterText(vendorID,data);
        return this;
    }
  	public HomePage enterNameOfVendor(String data) {
        enterText(enterName,data);
        return this;
    }
  	public HomePage searchByVendorName(String data) {
        enterText(searchVendorName,data);
        return this;
    }
  	public HomePage enterAddressOfVendor(String data) {
        enterText(enterAddress,data);
        return this;
    }
  	public HomePage enterCityOfVendor(String data) {
        enterText(enterCity,data);
        return this;
    }
  	public HomePage enterCountryOfVendor(String data) {
        enterText(enterCountry,data);
        return this;
    }
  	public HomePage clickAdd() {
		click(clickAdd);
        return this;
    }
  	
	//****************** For handling alert modal ******************//

    	public HomePage alerts() throws InterruptedException {
  		Thread.sleep(5000);
  		Alert saveMessage = driver.switchTo().alert();
  		String textValue = saveMessage.getText();
  		saveMessage.accept(); 
  		System.out.println("Alert Message is : "+textValue);
		return this;
  	}
   
   	//****************** For logout ******************//

	public HomePage clickLogOutButton () {
		click(clickLogOut);
        return this;
    }
  	
  	
}


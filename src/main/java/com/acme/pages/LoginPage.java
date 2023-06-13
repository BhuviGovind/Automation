package com.acme.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.acme.testngbase.TestNgBase;
import com.aventstack.extentreports.ExtentTest;

public class LoginPage extends TestNgBase{
		public LoginPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
	    	this.driver = driver;
	    	this.node = node;
	    	this.test = test;
	        PageFactory.initElements(driver,this);
	        }
	    
	    @FindBy(how=How.XPATH ,using="//input[@id='email']")private WebElement enterUserName;
	    @FindBy(how=How.XPATH ,using="//input[@id='email']")private WebElement pressTabKey;
	    @FindBy(how=How.XPATH ,using="//input[@id='password']")private WebElement enterPassword;
	    @FindBy(how=How.XPATH ,using="//button[contains(text(),'Login')]")private WebElement clickLoginButton;

	    
	    public LoginPage userName(String data) {
	     	enterText(enterUserName, data);
	         return this;
	     }
	    
	    public LoginPage tabKey() {
	    	pressTabKey.sendKeys(Keys.TAB);
	         return this;
	     }
	     
	    public LoginPage password(String data) {
	         enterText(enterPassword,data);
	         return this;
	     }
	     public HomePage clickButton() {
	         click(clickLoginButton);
	         return new HomePage(driver) ;
	 }
	  
	}



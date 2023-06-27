package etrain.info.pages;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import etrain.info.testngbase.TestNgBase_etrain;

public class SearchTrainsPage extends TestNgBase_etrain {
	Map<Integer, String> map = new HashMap<Integer, String>();
	public WebDriverWait wait;

	public SearchTrainsPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
    	this.driver = driver;
    	this.node = node;
    	this.test = test;
        PageFactory.initElements(driver,this);
        }
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Source Station']")
	private WebElement sourceStation;
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Destination Station']")
	private WebElement destinationStation;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'CHENNAI EGMORE')]")
	private WebElement selectSourceValue;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'BANGALORE CY JN (SBC')]")
	private WebElement selectDestinationValue;
	@FindBy(how = How.ID, using = "tbssbmtbtn")
	private WebElement getTrainsButton;

	/************************** Date Picker **************************/
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Select Date']")
	private WebElement datePicker;
	@FindBy(how = How.XPATH, using = "//input[@value='>']")
	private WebElement datePickerNextButton;
	@FindBy(how = How.XPATH, using = "//input[starts-with(@class,'calBtn')]")
	private WebElement datePickerRow;
	@FindBy(how = How.XPATH, using = "//input[@class='calBtn today']")
	private WebElement currentDate;
	@FindBys({ @FindBy(how = How.XPATH, using = "//input[starts-with(@class,'calBtn')]") })
	private List<WebElement> datePickerRows;

	/************************** Pantry Availability **************************/
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@etitle='Pantry Available' and @style]") })
	private List<WebElement> pantryAvailableTrains;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class='icon-food h-link']/ancestor::tr//td[1]//a") })
	private List<WebElement> trainNumber;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class='icon-food h-link']/ancestor::tr//td[2]//a") })
	private List<WebElement> trainName;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class='icon-food h-link']/ancestor::tr//td[6]") })
	private List<WebElement> arrivalTime;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class='icon-food h-link']/ancestor::tr//td[4]") })
	private List<WebElement> departureTime;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Request Successful.')]")
	private WebElement verificationMessage;

	public SearchTrainsPage enterSourceStation() {
		enterText(sourceStation, "ms");
		return this;
	}

	public SearchTrainsPage selectSourceStationValue() {
		click(selectSourceValue);
		return this;
	}

	public SearchTrainsPage enterdestinationStation() throws InterruptedException {
		Thread.sleep(2000);
		enterText(destinationStation, "ksr");
		return this;
	}

	public SearchTrainsPage selectDestinationStationValue() {
		click(selectDestinationValue);
		return this;
	}

	public SearchTrainsPage clickgetTrainsButton() throws InterruptedException {
		Thread.sleep(5000);
		click(getTrainsButton);
		return this;
	}

	public SearchTrainsPage clickDatePicker() throws InterruptedException {
		Thread.sleep(5000);
		int count = getFutureDateMonth() - getCurrentDateMonth();
		for (int i = 1; i <= count; i++) {
			click(datePickerNextButton);
		}
		Thread.sleep(8000);
		driver.findElement(By.xpath("//input[@type='button' and @value='" + addDaysToCurrentDate().getDayOfMonth() + "']")).click();
		return this;
	}

	public SearchTrainsPage currentDateColor() throws InterruptedException {
		Thread.sleep(3000);
		click(datePicker);
		String color = currentDate.getCssValue("border-color");
		String border = Color.fromString(color).asHex();
		if (border.equalsIgnoreCase("#00c800")) {
			reportStep("Border Color Of the Current Date is GREEN " + border , "pass");

		} else {
			reportStep("Border Color Of the Current Date is not GREEN " + border , "fail");

		}
		return this;
	}

	public SearchTrainsPage findingFastestTrain() throws ParseException {
		Map<Integer, String> pantryAvailableTrain = getFastestTrain(pantryAvailableTrains, trainName, trainNumber, arrivalTime, departureTime);
		List<Integer> sortedKeys = new ArrayList<Integer>(pantryAvailableTrain.keySet());
		Collections.sort(sortedKeys);		
		System.out.println("Fastest Train Details: " + pantryAvailableTrain.get(sortedKeys.get(0)));
		String fastestTrain = pantryAvailableTrain.get(sortedKeys.get(0)).substring(14, 19).trim();		
		driver.findElement(By.xpath("//td[@class='wd55']//a[contains(@href, '" + fastestTrain + "')]")).click();
		return this;
	}

//	public SearchTrainsPage getVerificationMessage() {
//		String message = verificationMessage.getText();
//		if (message.equalsIgnoreCase("request successful.")) {
//			System.out.println("Verified the message");
//			reportStep("Message ' " +message+ " ' is verified", "pass");
//
//		} else {
//			System.out.println("Message is not verified");
//			reportStep("Message ' " +message+ " ' is not verified", "fail");
//		}
//		return this;
//	}
	
	
	public SearchTrainsPage getVerificationMessage() throws Exception {
	    String message = verificationMessage.getText();
	    if (message.equalsIgnoreCase("request successful.")) {
	        System.out.println("Verified the message");
	        reportStep("Message '" + message + "' is verified", "pass");
	    } else {
	        System.err.println();
	        reportStep("Message '" + message + "' is not verified", "fail");
	    }
	    return this;
	}
}

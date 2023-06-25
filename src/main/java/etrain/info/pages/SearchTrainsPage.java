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

import etrain.info.testngbase.TestNgBase_etrain;

public class SearchTrainsPage extends TestNgBase_etrain {
	Map<Integer, String> map = new HashMap<Integer, String>();
	public WebDriverWait wait;

	public SearchTrainsPage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Source Station']")
	private WebElement sourceStation;
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Destination Station']")
	private WebElement destinationStation;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'CHENNAI EGMORE')]")
	private WebElement selectSourceValue;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'BANGALORE CY JN (SBC)')]")
	private WebElement selectDestinationValue;
	@FindBy(how = How.ID, using = "tbssbmtbtn")
	private WebElement getTrainsButton;

	/************************** Date Picker **************************/
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Select Date']")
	private WebElement datepicker;
	@FindBy(how = How.XPATH, using = "//input[@value='>']")
	private WebElement datepickerNextButton;
	@FindBy(how = How.XPATH, using = "//input[starts-with(@class,'calBtn')]")
	private WebElement datepickerRow;
	@FindBy(how = How.XPATH, using = "//input[@class='calBtn today']")
	private WebElement currentDate;
	@FindBys({ @FindBy(how = How.XPATH, using = "//input[starts-with(@class,'calBtn')]") })
	private List<WebElement> datepickerRows;

	/************************** Pantry Availability **************************/
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@etitle=\"Pantry Available\" and @style]") })
	private List<WebElement> pantryAvailableTrains;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class=\"icon-food h-link\"]/ancestor::tr//td[1]//a") })
	private List<WebElement> trainNumber;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class=\"icon-food h-link\"]/ancestor::tr//td[2]//a") })
	private List<WebElement> trainName;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class=\"icon-food h-link\"]/ancestor::tr//td[6]") })
	private List<WebElement> arrivalTime;
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class=\"icon-food h-link\"]/ancestor::tr//td[4]") })
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
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(45);
		int currentDateMonth = currentDate.getMonthValue();
		int futureDateMonth = futureDate.getMonthValue();
		int futureDateValue = futureDate.getDayOfMonth();
		System.out.println("Date added to 45 days from Current Date is : " + futureDate);
		int count = futureDateMonth - currentDateMonth;
		for (int i = 1; i <= count; i++) {
			click(datepickerNextButton);
		}
		Thread.sleep(8000);
		driver.findElement(By.xpath("//input[@type='button' and @value='" + futureDateValue + "']")).click();
		return this;
	}

	public SearchTrainsPage currentDateColor() throws InterruptedException {
		Thread.sleep(3000);
		click(datepicker);
		String color = currentDate.getCssValue("border-color");
		System.out.println("Border Color Code of Current Date: " + color);
		String border = Color.fromString(color).asHex();
		System.out.println(border);
		if (border.equalsIgnoreCase("#00c800")) {
			System.out.println("The border color of current date is GREEN");
		} else {
			System.out.println("The border color of current date is not GREEN");
		}
		return this;
	}

	public SearchTrainsPage calculateFastestTrain() throws ParseException {
		for (int i = 0; i < pantryAvailableTrains.size(); i++) {
			String trainDetails = null;
			String trainNames = trainName.get(i).getText();
			String trainNumbers = trainNumber.get(i).getText();
			if (!trainNumbers.isEmpty() && !trainNames.isEmpty()) {
				trainDetails = "Train Number: " + trainNumbers + " and " + "Train Name: " + trainNames;
				System.out.println(trainDetails);
			}
			String arrival = arrivalTime.get(i).getText();
			System.out.println("Arrival Time: " + arrival);
			String departure = departureTime.get(i).getText();
			System.out.println("Departure Time: " + departure);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime arrtime = null;
			LocalTime deptime = null;
			int arrTimeInMinutes = 0;
			int deptTimeInMinutes = 0;

			if (!arrival.isEmpty()) {
				arrtime = LocalTime.parse(arrival, formatter);
			}
			if (!departure.isEmpty()) {
				deptime = LocalTime.parse(departure, formatter);
			}
			if (arrtime != null && deptime != null) {
				arrTimeInMinutes = arrtime.getHour() * 60 + arrtime.getMinute();
				deptTimeInMinutes = deptime.getHour() * 60 + deptime.getMinute();
				if (deptTimeInMinutes > arrTimeInMinutes) {
					arrTimeInMinutes = arrTimeInMinutes + 1440;
				}
				System.out.println("Arrival Time in Minutes: " + arrTimeInMinutes);
				System.out.println("Departure Time in Minutes: " + deptTimeInMinutes);
				int minutesAsInt = arrTimeInMinutes - deptTimeInMinutes;
				if (minutesAsInt < 0) {
					minutesAsInt = minutesAsInt * -1;
				}
				if (minutesAsInt > 0) {
					System.out.println("Travel Time in Minutes: " + minutesAsInt);
					map.put(minutesAsInt, trainDetails);
				}
			}

		}
		List<Integer> sortedKeys = new ArrayList<Integer>(map.keySet());
		Collections.sort(sortedKeys);
		//System.out.println(sortedKeys);
		System.out.println("Fastest Train Details: " + map.get(sortedKeys.get(0)));
		String fastestTrain = map.get(sortedKeys.get(0)).substring(14, 19).trim();
		//System.out.println(fastestTrain);
		driver.findElement(By.xpath("//td[@class='wd55']//a[contains(@href, '" + fastestTrain + "')]")).click();
		return this;
	}

	public SearchTrainsPage getVerificationMessage() {
		String message = verificationMessage.getText();
		System.out.println("Verification Message: " + message);
		Assert.assertEquals(message, "Request Successful.");
		return this;
	}
}

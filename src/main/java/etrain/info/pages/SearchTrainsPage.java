package etrain.info.pages;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import etrain.info.testngbase.TestNgBase_etrain;

public class SearchTrainsPage extends TestNgBase_etrain {
	Map<Integer, String> map = new HashMap<Integer, String>();
	public WebDriverWait wait;

	public SearchTrainsPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
		PageFactory.initElements(driver, this);
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
	@FindBy(how = How.XPATH, using = "//input[@class='calBtn today']")
	private WebElement currentDate;

	/************************** Pantry Availability **************************/
	@FindBys({ @FindBy(how = How.XPATH, using = "//i[@class='icon-food h-link']//ancestor::tr[@data-train]") })
	private List<WebElement> trainDetails;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Request Successful.')]")
	private WebElement verificationMessage;

	/*
	 * This method enters the source station information into the corresponding
	 * field.
	 * 
	 * @param data The source station name or code to be entered.
	 */
	public SearchTrainsPage enterSourceStation(String data) {
		enterText(sourceStation, data, " 'Source Station'");
		return this;
	}

	/*
	 * This method selects the source station value on the Search Trains page.
	 * 
	 * @param data The value to be selected as the source station.
	 */
	public SearchTrainsPage selectSourceStationValue(String data) {
		click(selectSourceValue, data, " 'CHENNAI EGMORE (MS)'");
		return this;
	}

	/*
	 * This method enters the destination station information into the search field.
	 * 
	 * @param data The destination station name or code to be entered into the
	 * search field.
	 */

	public SearchTrainsPage enterdestinationStation(String data) throws InterruptedException {
		Thread.sleep(2000);
		enterText(destinationStation, data, " 'Destination Station'");
		return this;
	}

	/*
	 * This method selects the destination station value from a dropdown or input
	 * field.
	 * 
	 * @param data The value to be selected as the destination station.
	 * 
	 */
	public SearchTrainsPage selectDestinationStationValue(String data) {
		click(selectDestinationValue, data, " 'KSR BANGALORE CY JN (SBC)'");
		return this;
	}

	/*
	 * This method clicks on the 'Get Trains' button on the current page to initiate
	 * a train search.
	 */
	public SearchTrainsPage clickgetTrainsButton() throws InterruptedException {
		Thread.sleep(2000);
		clickButton(getTrainsButton, " 'Get Trains' Button");
		return this;
	}

	/*
	 * This method clicks on the date picker and selects a date by adding a
	 * specified number of days to the current date.
	 * 
	 * @param daysToAdd The number of days to be added to the current date for
	 * selecting the desired date.
	 * 
	 * @return The SearchTrainsPage after selecting the date.
	 * 
	 */
	public SearchTrainsPage clickDatePicker(int daysToAdd) throws InterruptedException {
		clickDatePickerNextButton(datePickerNextButton, daysToAdd);
		driver.findElement(By.xpath("//input[@type='button' and @value='" + addDaysToCurrentDate(daysToAdd).getDayOfMonth() + "']")).click();
		return this;
	}

	/*
	 * This method clicks on the "Current Date" button in the DatePicker. After
	 * clicking, it retrieves the border color of the "Current Date" button and
	 * compares it with the provided 'borderColor' parameter.
	 *
	 * @param borderColor The expected border color of the "Current Date" button.
	 * 
	 */

	public SearchTrainsPage currentDateColor(String borderColor) throws InterruptedException {
		Thread.sleep(2000);
		clickButton(datePicker, " 'Current Date' in the DatePicker");
		getBorderColorOfCurrentDateButton(currentDate, borderColor);
		return this;
	}

	/*
	 * This method finds and clicks on the link of the fastest train available with
	 * a pantry service. This method searches for the fastest train among the
	 * provided trainDetails,considering the availability of pantry services on each
	 * train. If multiple trains have the same fastest speed and pantry services, it
	 * will click on the link of the first train found in the sorted list.
	 *
	 * @return SearchTrainsPage - An instance of the SearchTrainsPage after clicking
	 * the fastest train link.
	 */

	public SearchTrainsPage findingFastestTrain() throws ParseException {

		Map<Integer, String> pantryAvailableTrain = getFastestTrain(trainDetails);
		String fastestTrain = sortPantryAvailableTrains(pantryAvailableTrain);
		driver.findElement(By.xpath("//td[@class='wd55']//a[contains(@href, '" + fastestTrain + "')]")).click();
		return this;
	}

	/*
	 * This method verifies the validation message displayed on the
	 * SearchTrainsPage.
	 * 
	 * @param expectedVerificationMessage The expected validation message to be
	 * compared with the actual message on the page.
	 */
	public SearchTrainsPage verifyValidationMessage(String expectedVerificationMessage) {
		getValidationMessage(verificationMessage, expectedVerificationMessage);
		return this;
	}
}

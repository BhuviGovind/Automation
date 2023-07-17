package etrain.info.seleniumbase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import etrain.info.seleniumdesign.Browser;
import etrain.info.seleniumdesign.Element;
import utils.Reporter;

public class SeleniumBase_etrain extends Reporter implements Browser, Element {

	public WebDriverWait wait;
	public static RemoteWebDriver driver;

	/*
	 * This method is used to initialize and launch the specified web browser and
	 * open the given URL.
	 * 
	 * @param browser The name of the web browser to be used (e.g., "chrome" or
	 * "firefox").
	 * 
	 * @param url The URL of the web application to be opened.
	 * 
	 */
	public void invokeApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException();
		}
	}

	/* This method closes the current web application. */
	public void closeApp() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			driver.close();
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException();
		}
	}

	/*
	 * This method performs a click action on the specified WebElement.
	 * 
	 * @param element The WebElement to be clicked.
	 * 
	 * @param description A brief description of the element for logging purposes.
	 * 
	 * @param data Additional data related to the click action (if needed).
	 * 
	 */
	public void click(WebElement element, String description, String data) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportStep("Clicked on the element " + description, "pass");

		} catch (Exception e) {
			reportStep("Unable to click on the element " + description, "fail");
			System.err.println(e);
			throw new RuntimeException();
		}
	}

	/*
	 * This method clicks on the given web element if it is clickable within a
	 * specified timeout.
	 *
	 * @param element The web element to be clicked.
	 * 
	 * @param description A description of the element being clicked (for reporting
	 * purposes).
	 */

	public void clickButton(WebElement element, String description) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportStep("Clicked on the element " + description, "pass");

		} catch (Exception e) {
			reportStep("Unable to click on the element " + description, "fail");
			System.err.println(e);
		}
	}

	/*
	 * This method enters the given data into a web element representing a textbox.
	 * 
	 * @param element The web element representing the textbox.
	 * 
	 * @param data The text data to be entered into the textbox.
	 * 
	 * @param description A brief description of the textbox or the purpose of data
	 * entry (optional).
	 * 
	 */
	public void enterText(WebElement element, String data, String description) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(data);
			reportStep("Entered " + data + " in the textbox" + description, "pass");

		} catch (Exception e) {
			reportStep("Unable to enter " + data + " in the textbox" + description, "fail");
			System.err.println(e);
			throw new RuntimeException();
		}
	}

	/*
	 * This method takes a snapshot of the current browser window and saves it as an
	 * image file.
	 * 
	 * @return The randomly generated unique number used to name the snapshot file.
	 * 
	 */
	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File source = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
			throw new RuntimeException();
		}
		return number;
	}

	/*
	 * This method adds the specified number of days to the current date and returns
	 * the resulting future date.
	 * 
	 * @param daysToAdd The number of days to be added to the current date.
	 * 
	 * @return The future date after adding the specified number of days to the
	 * current date.
	 * 
	 */
	public LocalDate addDaysToCurrentDate(int daysToAdd) {
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(daysToAdd);
		return futureDate;
	}

	/* This method returns the current month as an integer value. */
	public int getCurrentDateMonth() {
		LocalDate currentDate = LocalDate.now();
		int currentDateMonth = currentDate.getMonthValue();
		return currentDateMonth;
	}

	/*
	 * This method calculates the month value of a future date by adding the
	 * specified number of days to the current date.
	 * 
	 * @param daysToAdd The number of days to add to the current date.
	 */
	public int getFutureDateMonth(int daysToAdd) {

		LocalDate futureDate = this.addDaysToCurrentDate(daysToAdd);
		int futureDateMonth = futureDate.getMonthValue();
		return futureDateMonth;
	}

	/*
	 * This method clicks the next button in a date picker for a specified number of
	 * months ahead.
	 * 
	 * @param datePickerNextButton The WebElement representing the "Next" button in
	 * the date picker.
	 * 
	 * @param daysToAdd The number of days to add to the current date to calculate
	 * the target month. A positive value will move the date picker forward, while a
	 * negative value will move it backward.
	 */
	public void clickDatePickerNextButton(WebElement datePickerNextButton, int daysToAdd) {
		int count = getFutureDateMonth(daysToAdd) - getCurrentDateMonth();
		for (int i = 1; i <= count; i++) {
			clickButton(datePickerNextButton, " datepicker 'Right Arrow'");
		}
	}

	/*
	 * This method retrieves the border color of the current date button and
	 * validates it.
	 * 
	 * @param currentDate The WebElement representing the current date button
	 * element.
	 * 
	 * @param borderColor The expected border color to be verified against the
	 * current date button's border color.
	 */
	public void getBorderColorOfCurrentDateButton(WebElement currentDate, String borderColor) {
		String color = currentDate.getCssValue("border-color");
		String border = Color.fromString(color).asHex();
		try {
			Assert.assertEquals(border, borderColor);
			reportStep("Message '" + border + "' is verified", "pass");
		} catch (Exception e) {
			failedScreenShot();
			reportStep("Message '" + border + "' is not verified", "fail");
		}
	}

	/*
	 * The method parses the train details with pantry availability from the web
	 * elements and calculates the difference between arrival and departure times to
	 * determine the fastest train.
	 * 
	 * @param trainDetails A list of WebElements representing train details, each
	 * containing JSON data.
	 * 
	 * @return A map containing the train's arrival-departure time difference as the
	 * key and the train information as the value.
	 * 
	 */
	public Map<Integer, String> getFastestTrain(List<WebElement> trainDetails) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String trainInfo = null;
		String arrivalTime = null;
		String departureTime = null;
		for (WebElement element : trainDetails) {
			String name = element.getAttribute("data-train");
			JSONObject jsonObject = new JSONObject(name);
			String trainName = jsonObject.getString("name");
			String trainNumber = jsonObject.getString("num");
			arrivalTime = jsonObject.getString("st");
			departureTime = jsonObject.getString("dt");
			trainInfo = "Train Number: " + trainNumber + " and " + "Train Name: " + trainName;
		}
		map.put(arrivalTimeDepatureTimeDifference(arrivalTime, departureTime), trainInfo);
		return map;
	}

	/*
	 * This method converts a time string to the arrival time in minutes.
	 * 
	 * @param arrivalTime The string representation of the arrival time in "HH:mm"
	 * format.
	 */

	public int getArrivalTime(String arrivalTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime arrTime = !arrivalTime.isEmpty() ? LocalTime.parse(arrivalTime, formatter) : null;
		int arrTimeInMinutes = 0;
		if (arrTime != null) {
			arrTimeInMinutes = arrTime.getHour() * 60 + arrTime.getMinute();
		}
		return arrTimeInMinutes;
	}

	/*
	 * This method converts a time string to the departure time in minutes.
	 * 
	 * @param departureTime The string representation of the departure time in
	 * "HH:mm" format.
	 */

	public int getDepatureTime(String departureTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime depTime = !departureTime.isEmpty() ? LocalTime.parse(departureTime, formatter) : null;
		int deptTimeInMinutes = 0;
		if (depTime != null) {
			deptTimeInMinutes = depTime.getHour() * 60 + depTime.getMinute();
		}
		return deptTimeInMinutes;
	}
	/*
	 * This method calculates the time difference between arrival and departure
	 * times in minutes.
	 * 
	 * @param arrivalTime The string representing the arrival time in "hh:mm" format
	 * (e.g., "09:30").
	 * 
	 * @param departureTime The string representing the departure time in "hh:mm"
	 * format (e.g., "15:45").
	 * 
	 * @return The time difference between arrival and departure times in minutes.
	 */

	public int arrivalTimeDepatureTimeDifference(String arrivalTime, String departureTime) {
		int arrTimeInMinutes = this.getArrivalTime(arrivalTime);
		int deptTimeInMinutes = this.getDepatureTime(departureTime);
		if (deptTimeInMinutes > arrTimeInMinutes) {
			arrTimeInMinutes = arrTimeInMinutes + 1440;
		}
		int minutesAsInt = arrTimeInMinutes - deptTimeInMinutes;
		return minutesAsInt;

	}

	/*
	 * This method sorts the available trains with pantry based on their keys and
	 * returns the code of the fastest train.
	 * 
	 * @param getFastestTrain A Map<Integer, String> containing train IDs as keys
	 * and train names as values.
	 * 
	 * @return The name of the fastest train with a pantry available, or null if no
	 * pantry is available in any train. The returned name will be in the format
	 * "Train_Name", where "Train" is the constant substring at position 14 to 18.
	 * 
	 * @throws NullPointerException If the input map 'getFastestTrain' is null.
	 */
	public String sortPantryAvailableTrains(Map<Integer, String> getFastestTrain) {
		List<Integer> sortedKeys = new ArrayList<Integer>(getFastestTrain.keySet());
		Collections.sort(sortedKeys);
		reportStep("Fastest Train that has pantry in it is : " + getFastestTrain.get(sortedKeys.get(0)), "info");
		String fastestTrain = getFastestTrain.get(sortedKeys.get(0)).substring(14, 19).trim();
		return fastestTrain;
	}

	/*
	 * This method retrieves the validation message from the provided WebElement and
	 * verifies if it matches the expected value.
	 * 
	 * @param verificationMessage The WebElement whose text needs to be verified.
	 * 
	 * @param expectedVerificationMessage The expected verification message to
	 * compare with the WebElement's text.
	 */
	public void getValidationMessage(WebElement verificationMessage, String expectedVerificationMessage) {
		String message = verificationMessage.getText();
		try {
			Assert.assertEquals(message, expectedVerificationMessage);
			reportStep("Message '" + message + "' is verified", "pass");
		} catch (Exception e) {
			failedScreenShot();
			reportStep("Message '" + message + "' is not verified", "fail");
		}
	}

	/*
	 * This method captures a screenshot of the failed test case and saves it to a
	 * specified location.
	 */

	public void failedScreenShot() {
		long snapNumber = 100000L;
		snapNumber = takeSnap();
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile,
					new File("C:\\Users\\Windows\\Automation\\reports\\failedscreenshots\\" + snapNumber + ".jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
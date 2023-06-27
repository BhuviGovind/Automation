package etrain.info.seleniumbase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import etrain.info.seleniumdesign.Browser;
import etrain.info.seleniumdesign.Element;
import utils.Reporter;

public class SeleniumBase_etrain extends Reporter implements Browser, Element {
	
public WebDriverWait wait;    
public static RemoteWebDriver driver;

	public void invokeApp(String browser, String url) {
		try {
            if(browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            }else if(browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        } catch (Exception e){
         System.err.println(e);    
        }
	}
	public void closeApp() {
		 try {
			 	wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	            driver.close();
	        }catch (Exception e){
	             System.err.println(e);  
	             }
	}
	public void click(WebElement element) {
		String elementDescription = element.getText();
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportStep("Clicked on the element " +elementDescription, "pass");

       }catch(Exception e) {
           reportStep("Unable to click on the element " +elementDescription, "fail");
           System.err.println(e);
       }
	}		
	public void enterText(WebElement element, String data) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(data);
            reportStep("Entered "+ data +" in the station textbox", "pass");

       } catch(Exception e) {
          	reportStep("Unable to enter "+ data +" in the station textbox", "fail");
          	System.err.println(e);
       }		
	}
	@Override
	public long takeSnap() {
       long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
       try {
       	File source = driver.getScreenshotAs(OutputType.FILE); 
       	FileUtils.copyFile(source, new File("./reports/images/"+number+".jpg"));
       } catch (WebDriverException e) {
           System.out.println("The browser has been closed.");
       } catch (IOException e) {
           System.out.println("The snapshot could not be taken");
       }
       return number;
   }
	
	public LocalDate addDaysToCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(45);
		return futureDate;
		
	}
	public int getCurrentDateMonth() {
		LocalDate currentDate = LocalDate.now();
		int currentDateMonth = currentDate.getMonthValue();
		return currentDateMonth;
	}
	public int getFutureDateMonth() {
		
		LocalDate futureDate = this.addDaysToCurrentDate();
		int futureDateMonth = futureDate.getMonthValue();
		return futureDateMonth;
	}	

	public Map<Integer, String> getFastestTrain(List<WebElement> pantryAvailableTrains, List<WebElement> trainName,
			List<WebElement> trainNumber, List<WebElement> arrivalTime, List<WebElement> departureTime) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < pantryAvailableTrains.size(); i++) {
			String trainDetails = null;
			String trainNames = trainName.get(i).getText();
			String trainNumbers = trainNumber.get(i).getText();
			String arrival = arrivalTime.get(i).getText();
			String departure = departureTime.get(i).getText();
			
			if (!trainNumbers.isEmpty() && !trainNames.isEmpty()) {
				trainDetails = "Train Number: " + trainNumbers + " and " + "Train Name: " + trainNames;
				System.out.println(trainDetails);
			}			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime arrTime = null;
			LocalTime depTime = null;
			int arrTimeInMinutes = 0;
			int deptTimeInMinutes = 0;
			if (!arrival.isEmpty()) {
				arrTime = LocalTime.parse(arrival, formatter);
			}
			if (!departure.isEmpty()) {
				depTime = LocalTime.parse(departure, formatter);
			}
			if (arrTime != null && depTime != null) {
				arrTimeInMinutes = arrTime.getHour() * 60 + arrTime.getMinute();
				deptTimeInMinutes = depTime.getHour() * 60 + depTime.getMinute();
				if (deptTimeInMinutes > arrTimeInMinutes) {
					arrTimeInMinutes = arrTimeInMinutes + 1440;
				}
//				System.out.println("Arrival Time in Minutes: " + arrTimeInMinutes);
//				System.out.println("Departure Time in Minutes: " + deptTimeInMinutes);
				int minutesAsInt = arrTimeInMinutes - deptTimeInMinutes;
				if (minutesAsInt < 0) {
					minutesAsInt = minutesAsInt * -1;
				}
				if (minutesAsInt > 0) {
					System.out.println("Travel Time in Minutes: " + minutesAsInt);
					System.out.println("\n");
					map.put(minutesAsInt, trainDetails);
				}
			}

		}
		return map;
	}
}
	

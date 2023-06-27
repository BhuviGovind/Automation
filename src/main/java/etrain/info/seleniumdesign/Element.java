package etrain.info.seleniumdesign;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

public interface Element {
	
	public void click(WebElement element);

	public void enterText(WebElement element, String data);
	
	public LocalDate addDaysToCurrentDate ();
	
	public int getCurrentDateMonth();
	
	public int getFutureDateMonth();
	
	public Map<Integer,String> getFastestTrain(List<WebElement> pantryAvailableTrains, List<WebElement> trainName, List<WebElement> trainNumber, List<WebElement> arrivalTime, List<WebElement> departureTime);

	
	
}

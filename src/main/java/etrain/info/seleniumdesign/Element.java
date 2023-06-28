package etrain.info.seleniumdesign;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

public interface Element {
	
	public void click(WebElement element, String description);

	public void enterText(WebElement element, String data, String description);
	
	public LocalDate addDaysToCurrentDate ();
	
	public int getCurrentDateMonth();
	
	public int getFutureDateMonth();
	
	public void clickDatePickerNextButton(WebElement element);
	
	public void getBorderColorOfCurrentDateButton(WebElement element);
	
	public void getValidationMessage(WebElement element);
	
	public String sortPantryAvailableTrains(Map<Integer, String> pantryAvailableTrain);
	
	public Map<Integer,String> getFastestTrain(List<WebElement> pantryAvailableTrains, List<WebElement> trainName, List<WebElement> trainNumber, List<WebElement> arrivalTime, List<WebElement> departureTime);
	
}

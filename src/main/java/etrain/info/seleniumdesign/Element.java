package etrain.info.seleniumdesign;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public interface Element {

	public void click(WebElement element, String data, String description);

	public void clickButton(WebElement element, String description);

	public void enterText(WebElement element, String data, String description);

	public LocalDate addDaysToCurrentDate(int daysToAdd);

	public int getCurrentDateMonth();

	public int getFutureDateMonth(int daysToAdd);

	public void clickDatePickerNextButton(WebElement element, int daysToAdd);

	public void getBorderColorOfCurrentDateButton(WebElement element, String borderColor);

	public void getValidationMessage(WebElement element, String expectedVerificationMessage);

	public String sortPantryAvailableTrains(Map<Integer, String> pantryAvailableTrain);

	public Map<Integer, String> getFastestTrain(List<WebElement> trainNamesInRows);

}

package etrain.info.seleniumdesign;

import org.openqa.selenium.WebElement;

public interface Element {
	
	public void click(WebElement element);

	public void enterText(WebElement element, String data);
}

package etrain.info.seleniumbase;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import etrain.info.seleniumdesign.Browser;
import etrain.info.seleniumdesign.Element;

public class SeleniumBase_etrain implements Browser, Element {
	
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
	            driver.close();
	        }catch (Exception e){
	             System.err.println(e);  
	             }
	}
	public void click(WebElement element) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
       }catch(Exception e) {
           System.err.println(e);
       }
	}		
	public void enterText(WebElement element, String data) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(data);
       } catch(Exception e) {
       	System.err.println(e);
       }		
	}
}

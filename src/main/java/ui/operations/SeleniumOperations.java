package ui.operations;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

/**
 * Class that consolidates all Seleinum related operatsions
 */
public class SeleniumOperations {

	Log logger = LogFactory.getLog(this.getClass());
	
	private WebDriver webDriver;
	Wait<WebDriver> wait ;

	public SeleniumOperations() {
		
		// Set path to chrome driver executable - 
		//TODO: see if I can embed it on the framework somehow
		System.setProperty("webdriver.chrome.driver", "D:\\PieGuy\\Programming\\ChromeDriver\\104\\chromedriver.exe"); 
		webDriver = new ChromeDriver();
		webDriver.manage().window().setSize(new Dimension(1600, 1200));
		
		wait = new FluentWait<WebDriver>(webDriver)
			.withTimeout(Duration.ofSeconds(60L))
			.pollingEvery(Duration.ofSeconds(3L))
			.ignoring(NoSuchElementException.class);
	}

	/**
	 * Navigate to a given URL
	 * 
	 * @param url - URL to navigate to
	 * @param elementToWaitFor - By element that is on target page - used
	 * to wait for page to load
	 */
	public void navigateToUrl(String url, By elementToWaitFor) {
		logger.debug(String.format("Navigating to URL:$s", url));
		webDriver.get(url);
		// wait for element on target page to appear
		waitForElementToAppear(elementToWaitFor);
	}

	/**
	 * Click on an element
	 * 
	 * @param selector - Selector of the element
	 */
	public void clickElement(By selector) {
		logger.info(String.format("Attempting to click", selector.toString()));
		wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
	}
	
	/**
	 * Method to click check box. This is not the best approach but the traditional click
	 * was not working. There is no wait on the element for this method. The wait.until
	 * used elsewhere times out on this web element.
	 * TODO: Will need to investigate issue and try to find a way to ensure the element is visible. 
	 * @param selector - checkbox selector
	 */
	public void clickCheckBox(By selector) {
		WebElement webElement = webDriver.findElement(selector);
		logger.info(String.format("Attempting to click checkbox", selector.toString()));
        new Actions(webDriver)
                .click(webElement)
                .perform();
	}
	
    /**
     * Clears field and sends keystroke(s) to element
     * @param selector - Selector of the element
     * @param keysToSend - Key strokes to send to the element
     */
    public void sendKeys(By selector, String keysToSend) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.clear();
        element.sendKeys(keysToSend);
    }
    
    /**
     * Select item from drop down
     * @param selector - drop down element selector
     * @param dropDownText - text of item to select
     */
    public void selectDropDown (By selector, String dropDownText) {
    	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
    	 Select dropDown = new Select(element);
    	 logger.info(String.format("Attempting to select '%s' from drop down menu", dropDownText));
    	 dropDown.selectByVisibleText(dropDownText);
    }
    
	/**
	 * Extract text from a field
	 * @param selector - field selector
	 * @return - value of element field
	 */
	public String extractFieldText(By selector) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
		logger.info(String.format("Attempting to extract text from element: '%s'", selector.toString()));
		return element.getText();
	}
	
	/**
	 * Check to see if a web element exists
	 * @param selector - element selector
	 * @return boolean - true if element exists
	 */
	public boolean elementExists(By selector) {
		logger.info(String.format("Attempting to determine if element:''%s' exists'", selector.toString()));
		return !webDriver.findElements(selector).isEmpty();
	}

	/**
	 * Wait for elements to appear or disappear. 
	 * @param selector - selector whose existence is to be checked
	 */
	public void waitForElementToAppear(By selector) {
		 logger.info(String.format("Checking for existence of selector: '%s'", selector.toString()));
		wait.until(ExpectedConditions.elementToBeClickable(selector));
	}
	
    /**
     * Close the browser
     */
    public void close() {
        webDriver.close();
    }
}

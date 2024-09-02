package Pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import Generic.GenericTest;

public class HomePage {

    WebDriver driver;
    GenericTest generic;
    Logger logger;
    SoftAssert softAssert;

    public HomePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.generic = new GenericTest(driver);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(driver, this);  // Ensure PageFactory.initElements is called after setting driver
    }

    @FindBy(css = ".fd8bdbddd9")
    WebElement popup;

    @FindBy(xpath = "//div[contains(@class,\"cda80ec319 \")]/button")
    WebElement popClose;

    public void check_popup_visibility() {
       generic.waitForVisibility(popup);
       // boolean isPopupDisplayed = popup.isDisplayed();
    	try {
    	System.out.println("inside the functionality");
    	 if (popup.isDisplayed()) {
    		 logger.info("Popup is displayed.");
             System.out.println("Popup displayed");

             // Click the close button
             popClose.click();
             System.out.println("Popup closed");
        }
    	 else
    	 {
    		 logger.info("Popup not displayed.");
             driver.navigate().refresh(); 
    	 }
       
    	}
    	catch(ElementNotInteractableException e)
    	{
    		 logger.error("Popup not interactable: ", e);
    	        System.out.println("Popup not interactable: " + e);    		
    	}
    	catch(NoSuchElementException e)
    	{
    		 logger.error("Element not found: ", e);
    	     System.out.println("Element not found: " + e);
    	}
    	finally
    	{
    		 softAssert.assertAll();
    	}
    }
}
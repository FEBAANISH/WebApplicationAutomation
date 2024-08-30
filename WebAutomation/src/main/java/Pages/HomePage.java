package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import Generic.GenericTest;

public class HomePage {

	WebDriver driver;
	GenericTest generic;
	Logger logger;

	public HomePage(WebDriver driver, Logger logger) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.generic = new GenericTest(driver);
		this.logger = logger;

	}

	@FindBy(css = ".fd8bdbddd9")
	WebElement popup;

	@FindBy(xpath = "//div[contains(@class,\"cda80ec319 \")]/button")
	WebElement popClose;

	public void check_popup_visibility() {

		generic.waitForVisibility(popup);
		boolean isPopupDisplayed = popup.isDisplayed();
		logger.info("Popup visibility: " + isPopupDisplayed);
		Assert.assertTrue(popup.isDisplayed(), "Popup is not displayed");
		if (popup.isDisplayed()) {
			System.out.println("popup dispplayed");
			popClose.click();
			System.out.println("System closed");
		}
	}
}

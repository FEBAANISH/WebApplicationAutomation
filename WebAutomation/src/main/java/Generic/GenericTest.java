package Generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GenericTest {

	WebDriver driver;
	WebDriverWait wait;

	public GenericTest(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	public void waitForVisibility(WebElement element) {
	    wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickability(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

}

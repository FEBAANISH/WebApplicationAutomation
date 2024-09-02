package Pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.GenericTest;

public class SignInPage {

	WebDriver driver;
	GenericTest generic;
	Logger logger;

	public SignInPage(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
		this.generic = new GenericTest(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class=\"d4b174fdfc e143cae27a\"]")
	WebElement SignInButton;
	@FindBy(xpath = "//*[@id=\"username\"]")
	WebElement user;
	@FindBy(xpath = "//button[contains(@class,\"DtlkOQAQuawAsxgcEUCY\")]")
	WebElement LoginButton;
	@FindBy(xpath = "//input[@name=\"password\"]")
	WebElement passwordText;

	public void click_SignIn_Button() {

//	generic.waitForVisibility(SignInButton);
		SignInButton.click();
	}

	public void enterUserName(String username) throws InterruptedException {
		try {
			// generic.waitForVisibility(user);

			user.sendKeys(username);
		} catch (NoSuchElementException e) {
			// driver.navigate().refresh();
			user.sendKeys(username);
		}
	}

	public void enterPassword(String password) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordText));
		// passwordText.clear();
		// generic.waitForVisibility(password);
		passwordText.sendKeys(password);
	}

	public void loginButton() {
		LoginButton.click();
	}

	public void login(String username, String password) throws InterruptedException {
		enterUserName(username);
		loginButton();
		enterPassword(password);
		driver.navigate().refresh();
		
	}
	
	public void Navigate_Back()
	{
		driver.close();
		driver.navigate().to("https://www.booking.com/");
	}

}
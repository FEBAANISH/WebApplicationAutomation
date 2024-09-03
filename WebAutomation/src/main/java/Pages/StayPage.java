package Pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Generic.GenericTest;

public class StayPage {

	WebDriver driver;
	Logger logger;
	GenericTest generic;
	ObjectMapper objectMapper = new ObjectMapper();
	String location;

	public StayPage(WebDriver driver, Logger logger) throws IOException {
		this.driver = driver;
		this.logger = logger;
		this.generic = new GenericTest(driver);
		PageFactory.initElements(driver, this);
		LoadLocationFromJson();
	}

	@FindBy(xpath = "//ul[@class=\"d1b2041e44\"]/child::li[@class=\"fc0b15c9b7 f62c02908f\"]/a")
	WebElement stay_Button;

	@FindBy(xpath = "//input[@class=\"ada65db9b5\"]")
	WebElement stayDropDown;

	@FindBy(xpath = "//div[@class=\"ad3c4dc079 e2f45f66c9\"]")
	List<WebElement> LocationList;

	@FindBy(xpath = "//*[@id=\"calendar-searchboxdatepicker\"]/div/div[1]/div/div[1]/table/tbody/tr")
	List<WebElement> checkDateList;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public void stayButtonClick() {
		generic.waitForVisibility(stay_Button);
		stay_Button.click();
	}

	private void LoadLocationFromJson() throws IOException {
		try {
			File file = new File(
					"C:\\Users\\febaz\\git\\WebApplicationAutomation\\WebAutomation\\src\\test\\StayLocationDetails\\Location.json");
			JsonNode rootNode = objectMapper.readTree(file);
			location = rootNode.path("location").asText();
			logger.info("Location loaded from JSON: " + location);
		} catch (IOException e) {
			logger.error("Failed to load location from JSON", e);
		}
	}

	public String getLocation() {
		return location;
	}

	public void stay_Details() throws IOException {
		/*
		 * if (location == null) { LoadLocationFromJson(); // Ensure location is loaded
		 * before using it }
		 */
		stayDropDown.click();
		stayDropDown.clear();
		stayDropDown.sendKeys(location);
	}

	public void finding_Matching_Location() {
		String expectedLocation = location;
		for (WebElement final_location : LocationList) {
			String actualLocation = final_location.getText();
			if (actualLocation.equalsIgnoreCase(expectedLocation)) {
				final_location.click();
				break;
			}
		}
	}

	public void checkInTime() {
		LocalDate today = LocalDate.now();
		LocalDate futureDate = today.plusDays(10);
		int day = futureDate.getDayOfMonth();

		String dayAsString = Integer.toString(day);
		System.out.println(dayAsString);

		for (WebElement Date : checkDateList) {
			String inDate = Date.getText().trim();
			String[] dates = inDate.split("\\s+"); // Splits by any whitespace

			System.out.println("Found element text: '" + inDate + "'");
			for (String datePart : dates) {
				System.out.println("Comparing '" + datePart + "' with '" + dayAsString + "'");

				if (datePart.equals(dayAsString)) {
					System.out.println("Found matching date. Clicking...");
					WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(Date));
					dateElement.click();
					break;
				}
			}
		}
	}
	public void checkOutTime()
	{
		LocalDate today = LocalDate.now();
		LocalDate futureDate = today.plusDays(10);
		LocalDate checkoutDate =futureDate.plusDays(3);
		int day = checkoutDate.getDayOfMonth();
		String dayAsString = Integer.toString(day);
		System.out.println(dayAsString);
	}
	
	
}

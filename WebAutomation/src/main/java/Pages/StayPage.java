package Pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(xpath="//div[@class=\"ad3c4dc079 e2f45f66c9\"]")
	List<WebElement> LocationList;
	
	

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
		 /*if (location == null) {
			 LoadLocationFromJson();  // Ensure location is loaded before using it
	        }*/
		stayDropDown.click();
		stayDropDown.clear();
		stayDropDown.sendKeys(location); 
	}
	
	
	
	public void finding_Matching_Location()
	{
		String expectedLocation =location;
		for(WebElement final_location:LocationList )
		{
			String actualLocation = final_location.getText();
			if (actualLocation.equalsIgnoreCase(expectedLocation))
			{
				final_location.click();
				break;
			}
		}
	}
}
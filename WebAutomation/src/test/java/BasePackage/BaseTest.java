package BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	@BeforeSuite
	public void setup() throws IOException {

		FileInputStream fis = null;

		try {
			logger.info("Starting setup...");
			fis = new FileInputStream(System.getProperty("user.dir") + "\\Resoruces\\Config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			String Broswer = prop.getProperty("browser");
			String url = prop.getProperty("baseUrl");

			if (Broswer.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				logger.info("Initialized ChromeDriver");
			} else if (Broswer.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				logger.info("Initialized FirefoxDriver");
			} else if (Broswer.equalsIgnoreCase("")) {
				System.out.println("browser not mentioned");
			} else {
				System.out.println("Invalid Browser type specified");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(url);

		} catch (IOException e) {
			logger.error("Failed to load configuration properties", e);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

	}
	
	/*@AfterTest
	public void tearDown()
	{
		driver.quit();
	}*/

}

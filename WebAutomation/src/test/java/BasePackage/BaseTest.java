package BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setup() throws IOException {

        FileInputStream fis = null;

        try {
        	System.out.println("open");
            logger.info("Starting setup...");
            fis = new FileInputStream("C:\\Users\\febaz\\git\\WebApplicationAutomation\\WebAutomation\\src\\main\\resource\\Config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            String browser = prop.getProperty("browser");
            String url = prop.getProperty("baseUrl");

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("Initialized ChromeDriver");
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                logger.info("Initialized FirefoxDriver");
            } else if (browser.equalsIgnoreCase("")) {
                logger.error("Browser not mentioned");
            } else {
                logger.error("Invalid Browser type specified");
            }

            if (driver != null) {
            	System.out.println("open");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get(url);
            }

        } catch (IOException e) {
            logger.error("Failed to load configuration properties", e);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

  /*  @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
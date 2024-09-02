package Testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BasePackage.BaseTest;
import Pages.SignInPage;
import UtilityPackage.ExcelReaderUtility;

public class SignInto extends BaseTest {
	SignInPage sp;

	@BeforeClass
	public void setup() throws IOException {
		super.setup();
		

	}
	
	@BeforeMethod
	public void beforeSetup()
	{
		sp = new SignInPage(driver, logger);
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		Object[][] data = null;
		try {
			data = ExcelReaderUtility.ExcelRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object[] row : data) {
			System.out.println("Username: " + row[0] + ", Password: " + row[1]);
		}
		return data;
	}
	
	@Test(priority=0)
	public void signInButtonClick()
	{
		sp.click_SignIn_Button();
	}

	@Test(dataProvider = "loginData",priority=1)
	public void testLogin(String username, String password) throws InterruptedException {
		
		sp.login(username, password);
		System.out.println(username);

	}
	
	@Test(priority=2)
	public void navigateHomePage()
	
	{
		sp.Navigate_Back();
	}


}

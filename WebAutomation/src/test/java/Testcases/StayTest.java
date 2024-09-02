package Testcases;

import java.io.IOException;


import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import BasePackage.BaseTest;
import Pages.SignInPage;
import Pages.StayPage;

public class StayTest extends BaseTest {
	
	StayPage s;
	
	@BeforeClass
	public void setup() throws IOException
	{
		super.setup();
	}
	
	@BeforeMethod
	public void setupPage() throws IOException
	{
		s = new StayPage(driver,logger);
	}
	
	@Test
	public void StayButtonOnClick()
	{
		s.stayButtonClick();
	}
	
	@Test
	public void StayDetails() throws IOException
	{
		s.stay_Details();
	}
	
	@Test
	  public void testStayPage()
	  {
	 String location = s.getLocation();
	  Assert.assertNotNull(location, "Location should not be null");
    Assert.assertEquals(location, "ooty", "Location should be 'ooty'");
	  }

	@Test
	public void choose_Location()
	{
		s.finding_Matching_Location();
	}
}

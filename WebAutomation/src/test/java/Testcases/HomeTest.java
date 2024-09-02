package Testcases;

import java.io.IOException;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePackage.BaseTest;
import Pages.HomePage;

public class HomeTest extends BaseTest {

    HomePage hp;

    @BeforeMethod
    @Override
    public void setup() throws IOException {
        super.setup();
        hp = new HomePage(driver, logger);
    }

    @Test
    public void handling_Popup() {
       hp.check_popup_visibility();
    	
    }
}
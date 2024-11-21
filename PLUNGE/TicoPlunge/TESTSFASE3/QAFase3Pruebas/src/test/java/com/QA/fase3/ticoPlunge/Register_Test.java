package com.QA.fase3.ticoPlunge;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Register_Test {
	private WebDriver driver;
	LoginPagePlunge registerPage;

	

	@Before
	public void setUp() throws Exception {
		registerPage = new LoginPagePlunge(driver);
		
		driver = registerPage.chromeDriverConnection();
		registerPage.visit("http://localhost:3000/");
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
//Some fields are likely to contain errors. Fix errors and try again.
	@Test
	public void cantRegisterWithMissingSpecialCharacterInPassword() throws InterruptedException {
		registerPage.clickLoginLink();
		Thread.sleep(2000);
		registerPage.clickRegisterButton();
		Thread.sleep(2000);
		registerPage.BadRegister();
		Thread.sleep(2000);
		assertTrue(registerPage.isErrorIconDisplayed());
        
	}
/*	@Test
	public void CantCreateEmptyUser() throws InterruptedException {
		adminDashBoard.SignInAsAdmin();
        Thread.sleep(2000);
        adminDashBoard.clickUserBtn();
        Thread.sleep(2000);
        adminDashBoard.clickAllUsersBtn();
        Thread.sleep(2000);
        adminDashBoard.clickAddUserBtn();
        Thread.sleep(2000);
        adminDashBoard.clickSaveUserBtn();
        Thread.sleep(2000);
        assertEquals("Some fields are likely to contain errors. Fix errors and try again.",adminDashBoard.getErrorSaveUserText());
        
	}*/
	

}

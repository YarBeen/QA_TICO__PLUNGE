package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.QA.fase3.ticoPlunge.Pages.LoginPagePlunge;

public class LoginAsAdmin_Test {
	private WebDriver driver;
	LoginPagePlunge adminDashBoard;

	

	@Before
	public void setUp() throws Exception {
		adminDashBoard = new LoginPagePlunge(driver);
		
		driver = adminDashBoard.chromeDriverConnection();
		adminDashBoard.visit("http://localhost:3000/");
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
//Some fields are likely to contain errors. Fix errors and try again.
	@Test
	public void AdminCanLogin() throws InterruptedException {
		adminDashBoard.clickLoginLink();
		Thread.sleep(2000);
		adminDashBoard.SignInAsAdmin();
		Thread.sleep(2000);
		assertEquals("Lista de Usuarios",adminDashBoard.getListaUsuariosLinkText());
        
	}
	
	@Test
	public void AdminCantLoginWithIncorrectPassword() throws InterruptedException {
		adminDashBoard.clickLoginLink();
		Thread.sleep(2000);
		adminDashBoard.BadSignInAsAdmin();
		Thread.sleep(2000);
		assertTrue(adminDashBoard.isErrorIconDisplayed());
        
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

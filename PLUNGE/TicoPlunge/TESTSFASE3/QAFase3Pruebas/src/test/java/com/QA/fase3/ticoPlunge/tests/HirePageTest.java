package com.QA.fase3.ticoPlunge.tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
/*
import com.QA.fase3.ticoPlunge.Pages.HirePage;
import com.QA.fase3.ticoPlunge.MainPage;
import com.QA.fase3.ticoPlunge.MyProfilePAge;
*/
import com.QA.fase3.ticoPlunge.Pages.HirePage;
import com.QA.fase3.ticoPlunge.Pages.MainPage;
import com.QA.fase3.ticoPlunge.Pages.MyProfilePAge;
public class HirePageTest {
	WebDriver driver;
	HirePage HirePage;
	
	@Before
	public void setUp() throws InterruptedException {
		
		HirePage = new HirePage(driver);
		driver = HirePage.chromeDriverConnection();
		HirePage.setDriver(driver);
		driver.manage().window().maximize();
		HirePage.visit("http://localhost:3000/");
		HirePage.GoToLogIn();
		HirePage.LogIn("TestAdmin@gmail.com", "1234.aD$%");
		HirePage.visit("http://localhost:3000/CreatePlan");
		HirePage.createplan();
		HirePage.visit("http://localhost:3000/");
		HirePage.LogOut();
		HirePage.GoToLogIn();
		HirePage.LogIn("john@prueba.com", "newPassword123!");
	}
	@After
	public void tearDown()  throws InterruptedException {
		HirePage.LogOut();
		HirePage.visit("http://localhost:3000/");
		HirePage.GoToLogIn();
		HirePage.LogIn("TestAdmin@gmail.com", "1234.aD$%");
		HirePage.visit("http://localhost:3000/CreatePlan");
		HirePage.deleteplan();

		driver.quit();
	}
	@Test
		public void HireTest() throws InterruptedException {
		HirePage.visit("http://localhost:3000/HirePlan");
		try {
			HirePage.hirePlan();
			assertTrue(HirePage.itshired());

		} catch (Exception e) {
			fail();
		}
		
	}

	
}
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
	String adminEmail = "TestAdmin@gmail.com";
	String adminPassword = "1234.aD$%";
	String staffEmail2 = "TestStaff2@gmail.com";
	String staffPassword2 = "TestStaff2@gmail.com";
	String staffEmail3 = "TestStaff3@gmail.com";
	String staffPassword3 = "TestStaff3@gmail.com";
	String testClientMail = "john@prueba.com";
	String testClientPassword = "newPassword123!";
	@Before
	public void setUp() throws InterruptedException {
		
		HirePage = new HirePage(driver);
		driver = HirePage.chromeDriverConnection();
		HirePage.setDriver(driver);
		driver.manage().window().maximize();
		HirePage.visit("http://localhost:3000/");
		HirePage.GoToLogIn();
		HirePage.LogIn(adminEmail, adminPassword);
		HirePage.visit("http://localhost:3000/CreatePlan");
		Thread.sleep(2000);
		HirePage.createplan();
		Thread.sleep(2000);
		HirePage.visit("http://localhost:3000/");
		HirePage.LogOut();
		HirePage.visit("http://localhost:3000/");
		HirePage.GoToLogIn();
		HirePage.LogIn("john@prueba.com", "newPassword123!");
	}
	@After
	public void tearDown()  throws InterruptedException {
		HirePage.LogOut();
		HirePage.visit("http://localhost:3000/");
		HirePage.GoToLogIn();
		HirePage.LogIn(adminEmail, adminPassword);
		HirePage.visit("http://localhost:3000/CreatePlan");
		HirePage.deleteplan();

		driver.quit();
	}
	@Test
		public void HireTest() throws InterruptedException {
		HirePage.visit("http://localhost:3000/HirePlan");
		try {
			HirePage.hirePlan();
			Thread.sleep(2000);
			assertTrue(HirePage.itshired());

		} catch (Exception e) {
			fail();
			System.out.println(e);
		}
		
	}

	
}
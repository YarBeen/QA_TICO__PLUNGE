package com.QA.fase3.ticoPlunge.tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import com.QA.fase3.ticoPlunge.Pages.HirePage;
import com.QA.fase3.ticoPlunge.Pages.MainPage;
import com.QA.fase3.ticoPlunge.Pages.MyProfilePAge;
public class MyProfileTests {
	WebDriver driver;
	MyProfilePAge MyProfilePAge;
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
		
		MyProfilePAge = new MyProfilePAge(driver);
		driver = MyProfilePAge.chromeDriverConnection();
		MyProfilePAge.setDriver(driver);
		driver.manage().window().maximize();
		MyProfilePAge.visit("http://localhost:3000/");
		MyProfilePAge.GoToLogIn();
		MyProfilePAge.LogIn(testClientMail, testClientPassword);

	}
	
	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

	@Test
	public void EditMyProfile() throws InterruptedException {

	MyProfilePAge.visit("http://localhost:3000/Profile");
	MyProfilePAge.editProfile();
	assertTrue(MyProfilePAge.CheckProfile());
}
	
	
	
}

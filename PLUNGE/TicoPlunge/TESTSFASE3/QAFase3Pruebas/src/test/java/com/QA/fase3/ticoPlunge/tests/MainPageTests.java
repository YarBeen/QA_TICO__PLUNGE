package com.QA.fase3.ticoPlunge.tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import com.QA.fase3.ticoPlunge.Pages.HirePage;
import com.QA.fase3.ticoPlunge.Pages.MainPage;
import com.QA.fase3.ticoPlunge.Pages.MyProfilePAge;
public class MainPageTests {
	WebDriver driver;
	MainPage MainPage;
	String adminEmail = "TestAdmin@gmail.com";
	String adminPassword = "1234.aD$%";
	String staffEmail2 = "TestStaff2@gmail.com";
	String staffPassword2 = "TestStaff2@gmail.com";
	String staffEmail3 = "TestStaff3@gmail.com";
	String staffPassword3 = "TestStaff3@gmail.com";
	String testClientMail = "john@prueba.com";
	String testClientPassword = "newPassword123!";
	
	@Before
	public void setUp() {
		
		MainPage = new MainPage(driver);
		driver = MainPage.chromeDriverConnection();
		MainPage.setDriver(driver);
		driver.manage().window().maximize();
		MainPage.visit("http://localhost:3000/");

	}
	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
		public void TestGoToInstagram() throws InterruptedException {
		MainPage.GoInstagram();
		Thread.sleep(1000);
		
		assertTrue(MainPage.URLCheck("https://www.instagram.com/ticoplunge/?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw%3D%3D")  ||MainPage.URLCheck("https://www.instagram.com/ticoplunge/?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw%3D%3D#")  );
	}
	@Test
	public void TestGowhatsapp() throws InterruptedException {
	MainPage.Gowhatsapp();
	Thread.sleep(1001);
	assertTrue(MainPage.URLCheck("https://api.whatsapp.com/message/NQD6MTRNSIW5N1?autoload=1&app_absent=0"));
}
	@Test
	public void TestGoTofacebook() throws InterruptedException {
	MainPage.Gofacebook();
	Thread.sleep(1001);
	
	assertTrue(MainPage.URLCheck("https://www.facebook.com/people/Tico-Plunge/61553047905206/"));
}
	
	@Test
	public void StayLogged() throws InterruptedException {
	MainPage.GoToLogIn();
	MainPage.LogIn(adminEmail, adminPassword);
	MainPage.visit("https://www.facebook.com/profile.php?id=61553047905206");
	Thread.sleep(500);
	MainPage.visit("http://localhost:3000/");
	Thread.sleep(1000);

	
	assertTrue(!MainPage.ItsLogOut());
}
	@Test
	public void LogOut() throws InterruptedException {
	MainPage.GoToLogIn();
	MainPage.LogIn(adminEmail, adminPassword);
	MainPage.LogOut();
	Thread.sleep(1000);
	assertTrue(MainPage.ItsLogOut());
}
	@Test
	public void SingInWithEmailAlreadyInUse() throws InterruptedException {
	MainPage.visit("http://localhost:3000/SignIn");
	
	MainPage.SignIn(adminEmail);
	Thread.sleep(1000);
	assertTrue(MainPage.errorMsg());
}
	
	
	
}

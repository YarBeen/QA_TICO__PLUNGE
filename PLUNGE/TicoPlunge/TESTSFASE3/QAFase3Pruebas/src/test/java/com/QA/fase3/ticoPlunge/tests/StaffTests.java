package com.QA.fase3.ticoPlunge.tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;



import com.QA.fase3.ticoPlunge.Pages.HirePage;
import com.QA.fase3.ticoPlunge.Pages.MainPage;
import com.QA.fase3.ticoPlunge.Pages.MyProfilePAge;
import com.QA.fase3.ticoPlunge.Pages.Staffpage;
public class StaffTests {
	WebDriver driver;
	Staffpage Staffpage;
	String adminEmail = "TestAdmin@gmail.com";
	String adminPassword = "1234.aD$%";
	
	@Before
	public void setUp() throws InterruptedException {
		
		Staffpage = new Staffpage(driver);
		driver = Staffpage.chromeDriverConnection();
		Staffpage.setDriver(driver);
		driver.manage().window().maximize();
		Staffpage.visit("http://localhost:3000/");
		Staffpage.GoToLogIn();
		Staffpage.LogIn(adminEmail, adminPassword);
		Staffpage.visit("http://localhost:3000/CreateService");
		Thread.sleep(100);
		Staffpage.createService();
		Staffpage.visit("http://localhost:3000/CreateClass");
		Thread.sleep(100);
		Staffpage.createClass();
		Staffpage.visit("http://localhost:3000/");
		Staffpage.LogOut();
		Staffpage.GoToLogIn();
		Staffpage.LogIn("TestStaff2@gmail.com", "TestStaff2@gmail.com");
	}
	@After
	public void tearDown()  throws InterruptedException {
		Staffpage.visit("http://localhost:3000/");
		Staffpage.LogOut();
		Staffpage.visit("http://localhost:3000/");
		Staffpage.GoToLogIn();
		Staffpage.LogIn("TestAdmin@gmail.com", "1234.aD$%");
		Staffpage.visit("http://localhost:3000/AppointmentForm");
		Staffpage.deleteClass();
		Staffpage.visit("http://localhost:3000/CreateService");
		Staffpage.deleteService();
		
		driver.quit();	
	}
	@Test
		public void searchByDateTest() throws InterruptedException {
		Staffpage.visit("http://localhost:3000/AppointmentForm");
		Staffpage.SetDateSearh();
		assertTrue(Staffpage.itsSearch());
	}
	@Test
	public void searchByServiceTest() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Staffpage.SetDateSearh();
	assertTrue(Staffpage.itsSearch());

}
	@Test
	public void JoinClassWithNotSpace() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Staffpage.JoinClass();
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Staffpage.JoinClass();
	assertTrue(Staffpage.ErrorCeroSpaceShow());

}
	@Test
	public void EditClassWithCustomers() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Staffpage.editClass();
	assertTrue(Staffpage.editClassAlreadyCustomersError());

}
	@Test
	public void JoinClassWithNotCredits() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/");
	Staffpage.LogOut();
	Staffpage.GoToLogIn();
	Staffpage.LogIn("john@prueba.com", "newPassword123!");
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Staffpage.JoinClass();
	Thread.sleep(100);
	assertTrue(Staffpage.NoCreditsError());

}
	@Test
	public void AssignAServiceToMeWhileBeingAStaff() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/");
	Staffpage.LogOut();
	Staffpage.GoToLogIn();
	Staffpage.LogIn("TestStaff3@gmail.com", "TestStaff3@gmail.com");
	Staffpage.visit("http://localhost:3000/CreateService");
	Thread.sleep(100);
	Staffpage.AssignService();
	Thread.sleep(100);
	assertTrue(Staffpage.AssingMade());
}
	@Test
	public void AssignAServiceToMeWhileBeingAStaffAlreadyAssigned() throws InterruptedException {
		Staffpage.visit("http://localhost:3000/");
		Staffpage.LogOut();
		Staffpage.GoToLogIn();
		Staffpage.LogIn("TestStaff3@gmail.com", "TestStaff3@gmail.com");
		Staffpage.visit("http://localhost:3000/CreateService");
		Thread.sleep(100);
		Staffpage.AssignService();
		Thread.sleep(100);
		Staffpage.AssignService();
		Thread.sleep(100);
		assertTrue(Staffpage.AssignError());

}
	

	
}
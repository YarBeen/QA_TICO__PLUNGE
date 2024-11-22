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
	String staffEmail2 = "TestStaff2@gmail.com";
	String staffPassword2 = "TestStaff2@gmail.com";
	String staffEmail3 = "TestStaff3@gmail.com";
	String staffPassword3 = "TestStaff3@gmail.com";
	String testClientMail = "john@prueba.com";
	String testClientPassword = "newPassword123!";
	
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
		Thread.sleep(1000);
		Staffpage.createService();
		Thread.sleep(1000);
		Staffpage.visit("http://localhost:3000/CreateClass");
		Thread.sleep(1000);
		Staffpage.createClass();
		Thread.sleep(1000);
		Staffpage.visit("http://localhost:3000/");
		Thread.sleep(1000);
		Staffpage.LogOut();
		Thread.sleep(1000);
		Staffpage.GoToLogIn();
		Thread.sleep(1000);
		Staffpage.LogIn(staffEmail2, staffPassword2);
	}
	@After
	public void tearDown()  throws InterruptedException {
		Staffpage.visit("http://localhost:3000/");
		Thread.sleep(1000);
		Staffpage.LogOut();
		Thread.sleep(1000);
		Staffpage.visit("http://localhost:3000/");
		Thread.sleep(1000);
		Staffpage.GoToLogIn();
		Thread.sleep(1000);
		Staffpage.LogIn(adminEmail,adminPassword);
		Thread.sleep(1000);
		Staffpage.visit("http://localhost:3000/AppointmentForm");
		Thread.sleep(1000);
		Staffpage.deleteClass();
		Thread.sleep(1000);
		Staffpage.visit("http://localhost:3000/CreateService");
		Thread.sleep(1000);
		Staffpage.deleteService();
		
		driver.quit();	
	}
	@Test
		public void searchByDateTest() throws InterruptedException {
		Staffpage.visit("http://localhost:3000/AppointmentForm");
		Staffpage.SetDateSearh();
		Thread.sleep(2000);
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
	Staffpage.visit("http://localhost:3000/");
	Thread.sleep(1000);
	Staffpage.LogOut();
	Thread.sleep(1000);
	Staffpage.GoToLogIn();
	Thread.sleep(1000);
	Staffpage.LogIn(adminEmail, adminPassword);
	Staffpage.visit("http://localhost:3000/AppointmentForm");
	Thread.sleep(1000);
	Staffpage.editClass();
	 Thread.sleep(1000);
	assertTrue(Staffpage.editClassAlreadyCustomersError());

}
	@Test
	public void JoinClassWithNotCredits() throws InterruptedException {
	Staffpage.visit("http://localhost:3000/");
	Staffpage.LogOut();
	Staffpage.GoToLogIn();
	Staffpage.LogIn(testClientMail,testClientPassword );
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
	Staffpage.LogIn(staffEmail3,staffPassword3 );
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
		Staffpage.LogIn(staffEmail3,staffPassword3 );
		Staffpage.visit("http://localhost:3000/CreateService");
		Thread.sleep(100);
		Staffpage.AssignService();
		Thread.sleep(100);
		Staffpage.AssignService();
		Thread.sleep(100);
		assertTrue(Staffpage.AssignError());

}
	

	
}
package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;

import java.nio.Buffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.QA.fase3.ticoPlunge.Pages.ClassPagePlunge;

public class Class_Test {
	private WebDriver driver;
	ClassPagePlunge classPage;
	@Before
	public void setUp() throws Exception {
		classPage = new ClassPagePlunge(driver);
		
		driver = classPage.chromeDriverConnection();
		classPage.visit("http://localhost:3000/");
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
//Some fields are likely to contain errors. Fix errors and try again.
	@Test
	public void cantCreateEmptyClass() throws InterruptedException {
		classPage.clickLoginLink();
		Thread.sleep(2000);
		classPage.SignInAsAdmin();
		Thread.sleep(2000);
		classPage.clickCreateClassLink();
		Thread.sleep(3000);
		classPage.clickCreateClassBtn();
		Thread.sleep(2000);
		assertFalse(classPage.isSuccessCreateClassDisplayed());
        
	}
	@Test
	public void serviceCanBeCreated() throws InterruptedException {
		classPage.clickLoginLink();
		Thread.sleep(2000);
		classPage.SignInAsAdmin();
		Thread.sleep(2000);
		classPage.clickServiceLink();
		Thread.sleep(3000);
		classPage.createService("Test Service");
		Thread.sleep(2000);
		assertTrue(classPage.isSuccessCreateServiceDisplayed());
		
		//De<div class="success__title">Borrado con éxito</div>lete the class
		classPage.deleteFirstService();
		Thread.sleep(2000);
		assertEquals("Borrado con éxito",classPage.getSuccessDeleteServiceText());
		
		
	
		
	}
	
	@Test
	public void cantCreateServiceWithNoName() throws InterruptedException {
		classPage.clickLoginLink();
		Thread.sleep(2000);
		classPage.SignInAsAdmin();
		Thread.sleep(2000);
		classPage.clickServiceLink();
		Thread.sleep(3000);
		classPage.createServiceWithNoName();
		Thread.sleep(2000);
		assertFalse(classPage.isSuccessCreateServiceDisplayed());
		
		
		
		
	
		
	}
	
	@Test
	public void serviceCanBeDeleted() throws InterruptedException {
		classPage.clickLoginLink();
		Thread.sleep(2000);
		classPage.SignInAsAdmin();
		Thread.sleep(2000);
		classPage.clickServiceLink();
		Thread.sleep(3000);
		classPage.createService("Test Service");
		Thread.sleep(2000);
		assertTrue(classPage.isSuccessCreateServiceDisplayed());
		
		//De<div class="success__title">Borrado con éxito</div>lete the class
		classPage.deleteFirstService();
		Thread.sleep(2000);
		assertEquals("Borrado con éxito",classPage.getSuccessDeleteServiceText());
		
		
	
		
	}
	
	@Test
	public void serviceCanBeRenamed() throws InterruptedException {
		String newName = "New Service";
		classPage.clickLoginLink();
		Thread.sleep(2000);
		classPage.SignInAsAdmin();
		Thread.sleep(2000);
		classPage.clickServiceLink();
		Thread.sleep(3000);
		classPage.createService("Test Service");
		Thread.sleep(2000);
		assertTrue(classPage.isSuccessCreateServiceDisplayed());
		
		
		
		classPage.renameService(newName);
		
		Thread.sleep(2000);
		assertEquals(newName,classPage.getFirstServiceName());
		
		//De<div class="success__title">Borrado con éxito</div>lete the class
		classPage.deleteFirstService();
		Thread.sleep(2000);
		assertEquals("Borrado con éxito",classPage.getSuccessDeleteServiceText());
		
		
	
		
	}
	//<div class="success__title">Se ha actualizado correctamente</div>
	
	
	

}

package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.Pages.LoginAdmin;
import com.QA.fase3.ticoPlunge.Pages.PlanesPage;

public class PlanesTest {
		WebDriver driver;
		PlanesPage planesPage;
		LoginAdmin loginAdmin;
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
	    	planesPage = new PlanesPage(driver);
	        driver = planesPage.chromeDriverConnection();
	        driver.manage().window().maximize();
	        planesPage.visit("http://localhost:3000");

	        loginAdmin = new LoginAdmin(driver);
	        try {
	            loginAdmin.clickMenuIniciarSesion();
	            Thread.sleep(1000);
	            loginAdmin.setEmail(adminEmail);
	            loginAdmin.setPassword(adminPassword);
	            loginAdmin.clickIniciarSesion();
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @After
	    public void tearDown() {
	        driver.quit();
	    }

	    
	    @Test
	    public void testCrearPlanSinNombre() {
	        try {
	            planesPage.clickButtonPlanes();
	            planesPage.setPlanPrice("15000");
	            
	            WebElement nameField = driver.findElement(By.cssSelector("input[required]")); 
	            nameField.clear();
	            
	            planesPage.clickCreateButton();

	            String errorMessage =  planesPage.getValidationMessage(nameField);
	            assertEquals("Please fill out this field.", errorMessage);

	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            fail("Test falló debido a una interrupción inesperada.");
	        }
	    }
	    
	    @Test
	    public void testEliminarPlan() {
	        try {
	            planesPage.clickButtonPlanes();
	            
	            planesPage.setPlanName("Plan de prueba");
	            planesPage.setPlanPrice("15000");

	            planesPage.clickCreatePlanButton();
	            
	            planesPage.clickPlanEdit();
	            
	            planesPage.clickDeletePlanButton();
	            
	            planesPage.verifyDeleteAlert();


	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            fail("Test falló debido a una interrupción inesperada.");
	        }
	    }
	    
}

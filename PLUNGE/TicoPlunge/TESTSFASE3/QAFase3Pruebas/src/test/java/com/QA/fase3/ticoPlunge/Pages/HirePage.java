package com.QA.fase3.ticoPlunge.Pages;

import java.util.Set;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.base;



public class HirePage extends base{
	
	private WebDriver driver;
	
    public HirePage(WebDriver driver) {
		super(driver);
	}
    
    public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

    
    private By LogIn = By.xpath("/html/body/div/div/div/div[1]/div/div[2]/a");
    private By LogOut = By.xpath("/html/body/div/div/div/div[1]/div/div[14]/a");
    private By LogInButton = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button\r\n");
    private By emailField = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[1]");
    private By passwordField = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[2]");
    
    private By planName = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[1]/input");
    private By Planprice = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[2]/div/input");
    
    private By createPlanButton = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/button");
    
    private By hireButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[1]/button");
    private By confirmationButton = By.xpath("/html/body/div[3]/div/div/div[3]/button[2]");

    private By SaveButtonMyProfile = By.xpath("/html/body/div/div/div/div[2]/form/div[1]/div[3]/button");
    
    private By confirmationMsg = By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div[2]");
    
    private By plan = By.xpath("/html/body/div/div/div/div[2]/div[3]/div[1]");
    private By deleteplan = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[2]");

public boolean itshired() {
	return isDisplayed(confirmationMsg);
}
public void hirePlan() {
    try {
        click(hireButton);

        click(confirmationButton);
        
    } catch (UnhandledAlertException e) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Texto de la alerta: " + alert.getText());
            
            alert.accept();
        } catch (NoAlertPresentException ex) {
            System.out.println("No se encontró una alerta abierta después de la excepción.");
        }
    }
}
public void createplan() throws InterruptedException {
    	
    	type("NewPlan",planName);
    	type("10",Planprice);
    	click(createPlanButton);
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	
    	Thread.sleep(10);
		
    }
public void deleteplan() throws InterruptedException {
	
	
	click(plan);
	Thread.sleep(10);
	click(deleteplan);
	Alert alert = driver.switchTo().alert();
	alert.accept();
	
	Thread.sleep(10);
	
}

    
    public void LogIn(String email, String password) throws InterruptedException {
    	
    	type(email,emailField);
    	type(password,passwordField);
    	click(LogInButton);
    	Thread.sleep(100);
		
    }
    
    
    public void LogOut() {
    	click(LogOut);
    	
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	
    }
    
    public void GoToLogIn() throws InterruptedException {
    	click(LogIn);
		Thread.sleep(10);
    }
    
    
 
    
   
}
package com.QA.fase3.ticoPlunge.Pages;

import java.util.Set;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.base;



public class MyProfilePAge extends base{
	
	private WebDriver driver;
	
    public MyProfilePAge(WebDriver driver) {
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


    
    private By editProfileButton = By.xpath("/html/body/div/div/div/div[2]/form/div[1]/div[3]/button");
    private By cellPhone = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[4]/div[2]/input");
    private By celltext = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[4]/div[2]/p");
    private By Height = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[5]/div[2]/input");
    private By Heighttext = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[5]/div[2]/p");
    private By weight = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[6]/div[2]/input");
    private By weighttext = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div[2]/div/div/div[6]/div[2]/p");

    private By SaveButtonMyProfile = By.xpath("/html/body/div/div/div/div[2]/form/div[1]/div[3]/button");
    
    public void LogIn(String email, String password) throws InterruptedException {
    	
    	type(email,emailField);
    	type(password,passwordField);
    	click(LogInButton);
    	Thread.sleep(10);
		
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
    
    
    public void editProfile() {
    	
    	click(editProfileButton);
    	type("123", cellPhone);
    	type("456", Height);
    	type("789", weight);
    	click(SaveButtonMyProfile);
    	
    }
    
    public boolean CheckProfile() {
    	String cellStrin = getText(celltext);
    	String weighStrin = getText(weighttext);
    	String HeighStrin = getText(Heighttext);
    	if(cellStrin.equals("123") && HeighStrin.equals("456") && weighStrin.equals("789") ) {
    		return true;
    	}
    	
    	return false;
    	
    }
    
 
   
    
   
}
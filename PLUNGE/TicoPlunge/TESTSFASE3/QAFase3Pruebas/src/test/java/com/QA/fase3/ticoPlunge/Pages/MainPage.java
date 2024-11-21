package com.QA.fase3.ticoPlunge.Pages;

import java.util.Set;
import java.util.concurrent.StructuredTaskScope.Subtask;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.base;



public class MainPage extends base{
	
	private WebDriver driver;
	
    public MainPage(WebDriver driver) {
		super(driver);
	}
    
    public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

    
    private By Instagram = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[1]/div/a[1]");
    private By whatsapp	 = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[1]/div/a[2]");
    private By facebook	 = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[1]/div/a[3]\r\n");
    private By LogIn = By.xpath("/html/body/div/div/div/div[1]/div/div[2]/a");
    private By LogOut = By.xpath("/html/body/div/div/div/div[1]/div/div[14]/a");
    private By LogInButton = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button\r\n");
    private By emailField = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[1]");
    private By passwordField = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[2]");
    
    private By nameSign = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[1]");
    private By LastNameSign= By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[2]");
    private By EmailSign = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[3]");
    private By PasswordSign = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[4]");
    private By registerButton = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
    
    private By errorMsg = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/div/div");
    
    
    public void SignIn(String Email) {
    	type(Email,nameSign);
    	type(Email,LastNameSign);
    	type(Email,EmailSign);
    	type(Email,PasswordSign);
    	click(registerButton);
    }
    public boolean errorMsg() {
    	
    	return isDisplayed(errorMsg);
    }
    public void LogIn(String email, String password) throws InterruptedException {
    	
    	type(email,emailField);
    	type(password,passwordField);
    	click(LogInButton);
    	Thread.sleep(1000);
		
    }
    
    public void LogOut() {
    	click(LogOut);
    	
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	
    }
    
    public void GoToLogIn() throws InterruptedException {
    	click(LogIn);
		Thread.sleep(1000);
    }
    public void GoInstagram() throws InterruptedException {
    	click(Instagram);
		Thread.sleep(1000);
    }
    public void Gowhatsapp() throws InterruptedException {
    	click(whatsapp);
		Thread.sleep(1000);
    }
    public void Gofacebook() throws InterruptedException {
    	click(facebook);
		Thread.sleep(1000);
    }
    public boolean ItsLogOut() {
    	if(isDisplayed(LogIn)) {
    		return true;
    	}
    	return false;
    }
    
    
    public boolean URLCheck(String URL) {
    		
    		Set<String> handles = driver.getWindowHandles();
            
            for (String handle : handles) {
                driver.switchTo().window(handle);
            }
  
        if(URL.equals(getUrl())) {
        	return true;
        }
		return false;
        
    	
    }
}
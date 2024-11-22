package com.QA.fase3.ticoPlunge.Pages;

import java.util.Set;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.base;

import org.openqa.selenium.Keys;




public class Staffpage extends base{
	
	private WebDriver driver;
	
    public Staffpage(WebDriver driver) {
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
   
    private By servicename = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[1]/input");
    private By selectStaff = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[2]/select");
    private By firstStaff = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[2]/select/option[2]");
    private By createServiceBotton = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/button");
    
    private By serviceCreated = By.xpath("/html/body/div/div/div/div[2]/div[3]/div");
    private By deleteService = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[2]");
    private By assingMessage = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[3]/div/div[2]");

    
    private By classActivitybutton = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[1]/select");
    private By classActivityOption = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[1]/select/option[2]");
    
    private By classStaffbutton = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[2]/div/select");
    private By classStaffOption = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[2]/div/select/option[2]");
    
    private By date = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[3]/input");
    private By time = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[4]/input");
    private By space = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[5]/input");
    
    private By deleteClassButton = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[2]/div/span/button[2]");
    
    private By dateSearch = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[1]/form/div[2]/input");
    private By SearchButton = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[1]/form/button");
    
    private By ServiceSearchButton = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[1]/form/div[1]/select");

    private By ServiceSearchOption = By.xpath(" /html/body/div/div/div/div[2]/span/div/div[2]/div[1]/form/div[1]/select/option[2]");

    private By ItsShow = By.xpath("    /html/body/div/div/div/div[2]/span/div/div[2]/div[2]/div");
    
    private By CerospaceError = By.xpath("/html/body/div/div/div/div[2]/span/div/div[1]/div");
    private By getClassButton = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[2]/div/button");
    
    private By editClassAlreadyCustomersError = By.xpath("/html/body/div/div/div/div[2]/span/div/div[1]/div");
    private By editButton = By.xpath("/html/body/div/div/div/div[2]/span/div/div[2]/div[2]/div/span/button[1]");
  
    private By saveChangesButton = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/button[1]");
    private By NoCreditsError = By.xpath("/html/body/div/div/div/div[2]/span/div/div[1]/div/div/div[2]");

    private By assingServiceButton = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[1]/div/select");
    private By assingServiceOption = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[1]/div/select/option[2]");

    private By assingError = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[3]/div/div[2]");
    
    public boolean AssingMade() {
		return isDisplayed(assingMessage);

	}
    public boolean AssignError() {
		return isDisplayed(assingError);

	}
    public boolean editClassAlreadyCustomersError() {
		return isDisplayed(editClassAlreadyCustomersError);

	}
    
    public boolean ErrorCeroSpaceShow() {
		return isDisplayed(CerospaceError);

	}
    
    public boolean NoCreditsError() {
    	return isDisplayed(NoCreditsError);
		
	}
    
    	public void editClass() throws InterruptedException {
        WebElement editClassButton = driver.findElement(editButton);

    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editClassButton);
        Thread.sleep(1000);
        click(editClassButton);
        //editClassButton.click();
        Thread.sleep(1000);
        click(saveChangesButton);
        
        
    }
    public void JoinClass() throws InterruptedException {
        WebElement JoinClassButton = driver.findElement(getClassButton);

    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", JoinClassButton);
        Thread.sleep(100);
        JoinClassButton.click();
        Alert alert = driver.switchTo().alert();
    	alert.accept();
        
    }
    
    public void SetServiceSearch() {
    	click(ServiceSearchButton);
    	click(ServiceSearchOption);

	}
    public void SetDateSearh() {
    	type("13122024",dateSearch);
    	click(SearchButton);

	}
    public boolean itsSearch() {
		return isDisplayed(ItsShow);
	}
    
    public void deleteClass() throws InterruptedException {
        WebElement deleteButton = driver.findElement(deleteClassButton);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        Thread.sleep(2000);
        deleteButton.click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    

    public void createClass() throws InterruptedException {
    	click(classActivitybutton);
    	Thread.sleep(2000);
    	click(classActivityOption);
    	Thread.sleep(2000);
    	click(classStaffbutton);
    	Thread.sleep(2000);
    	click(classStaffOption);
    	Thread.sleep(2000);
    	type("13122024",date);
    	Thread.sleep(2000);
    	type("1111Am",time);
    	Thread.sleep(2000);
    	type("1",space);

    	 WebElement spaceElement = driver.findElement(space);
    	 spaceElement.sendKeys(Keys.RETURN);

    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	Alert alert2 = driver.switchTo().alert();
    	alert2.accept();


    }
    
    
    
    public void deleteService() {
    	click(serviceCreated);
    	click(deleteService);
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
	}
    public void createService() throws InterruptedException {
    	type("ServiceTest",servicename);
    	click(selectStaff);
    	Thread.sleep(2000);
    	click(firstStaff);
    	Thread.sleep(2000);
    	click(createServiceBotton);
    	Thread.sleep(2000);
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
	}
    
    public void AssignService() throws InterruptedException {
    	click(assingServiceButton);
    	click(assingServiceOption);
    	click(createServiceBotton);
    	Thread.sleep(100);

    	Alert alert = driver.switchTo().alert();
    	alert.accept();
	}
    
    
    
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
    
    
 
    
   
}
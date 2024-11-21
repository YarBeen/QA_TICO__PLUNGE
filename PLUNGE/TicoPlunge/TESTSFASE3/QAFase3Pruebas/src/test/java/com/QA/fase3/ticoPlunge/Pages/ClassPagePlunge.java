package com.QA.fase3.ticoPlunge.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ClassPagePlunge extends LoginPagePlunge{
	public String adminEmail = "yarman2005@gmail.com";
	public String adminPassword = "1234.aD$%";
	public String clientEmail = "yarman2006@gmail.com";
	public String clientPassword = "1234.aD$%";
	public String staffEmail = "yarman2007@gmail.com";
	public String staffPassword = "1234.aD$%";
	By loginLinkLocator =By.xpath("/html/body/div/div/div/div[1]/div/div[2]/a");
	By emailLocator = By.name("email");
	By passwordLocator = By.name("password");
	By signinLocator = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
	By loginBtnLocator= By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
	By listaUsuariosLinkLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[13]/a");
	
	By registerBtnLocator= By.xpath("/html/body/div/div/div/div[2]/div/div[1]/a/button");
	By registerPageNameLocator= By.name("firstName");
	By registerPageLastNameLocator= By.name("lastName");
	By registerPageEmailLocator= By.name("email");
	By registerPagePasswordLocator= By.name("password");
	By registerPageRegisterBtnLocator= By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
	By errorIconLocator=By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/div/div/div[1]");
	
	By logoutBtnLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[14]/a");
	
	//Class 
	By createClassLinkLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[6]/a");
	By successCreateClassLocator = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/div[9]/div/div[2]");
	By createClassBtnLocator = By.xpath("/html/body/div/div/div/div[2]/span/div/div/form/button");
	By serviceLinkLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[8]/a");
	By serviceNameTextBoxLocator = By.id("inputName");
	By inputEncargadoLocator = By.id("inputEncargado");
	By createServiceBtnLocator = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/button");
	By successCreateServiceLocator = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[3]/div/div[2]");
	
	By firstServiceBy = By.xpath("/html/body/div/div/div/div[2]/div[3]/div");
	By deleteServiceBtnLocator = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[2]");
	By successDeleteLocator = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[3]/div/div[2]");
	By newServiceNameLocator = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input");
	By saveServiceChangesLocator = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[1]");
	By firstServiceNameLocator = By.xpath("/html/body/div/div/div/div[2]/div[3]/div/span[1]");
	
	public ClassPagePlunge(WebDriver driver) {
		super(driver);
		
	}

	public void clickCreateClassLink() {
		click(createClassLinkLocator);
	}

	public boolean isSuccessCreateServiceDisplayed() {
		return isDisplayed(successCreateServiceLocator);
	}

	public boolean isSuccessCreateClassDisplayed() {
		return isDisplayed(successCreateClassLocator);
	}

	public void clickCreateClassBtn() {
		click(createClassBtnLocator);
	}

	public void clickServiceLink() {
		click(serviceLinkLocator);
	}
	public void deleteFirstService() {
		click(firstServiceBy);
		click(deleteServiceBtnLocator);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
	}

	public String getSuccessDeleteServiceText() {
		return getText(successDeleteLocator);
	}

	public String getFirstServiceName() {
		return getText(firstServiceNameLocator);
	}

	public void renameService(String newName) {
        click(firstServiceBy);
        clearText(newServiceNameLocator);
        type(newName,newServiceNameLocator);
        click(saveServiceChangesLocator);
        Alert alert = driver.switchTo().alert();

        
        alert.accept();
	}
	public void createService(String serviceName) throws InterruptedException {
        type( serviceName,serviceNameTextBoxLocator);
        WebElement selectElement = driver.findElement(By.id("inputEncargado"));

        
        Select dropdown = new Select(selectElement);

        
        dropdown.selectByIndex(1); 
        click(createServiceBtnLocator);
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();

        
        alert.accept();

       /*
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        System.out.println("Selected option: " + selectedOption.getText());*/
    }
	public void createServiceWithNoName() throws InterruptedException {
       
        WebElement selectElement = driver.findElement(By.id("inputEncargado"));

        
        Select dropdown = new Select(selectElement);

        
        dropdown.selectByIndex(1); 
        click(createServiceBtnLocator);
        

       /*
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        System.out.println("Selected option: " + selectedOption.getText());*/
    }
	
	
	
}


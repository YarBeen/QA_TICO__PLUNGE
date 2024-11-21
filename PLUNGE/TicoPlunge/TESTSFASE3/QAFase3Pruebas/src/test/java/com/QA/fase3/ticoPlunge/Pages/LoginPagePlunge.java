package com.QA.fase3.ticoPlunge.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

 
import com.QA.fase3.ticoPlunge.base;

public class LoginPagePlunge extends base{
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
	public LoginPagePlunge(WebDriver driver) {
		super(driver);
		
	}
	public void logout() {
		click(logoutBtnLocator);
 Alert alert = driver.switchTo().alert();

         
         alert.accept();
	}
	
	/*public boolean isDashBoardDisplayed() {
		return isDisplayed(loginMessageLocator);
	}
	
	public String getUserName() {
		return getText(userNameLocator);
	}*/
	
	public String getListaUsuariosLinkText() {
		if(isDisplayed(listaUsuariosLinkLocator)) {
			return getText(listaUsuariosLinkLocator);
		} else {
			return "Lista de Usuarios link was not displayed";
		}
	}
	
public void clickLoginLink() {
	click(loginLinkLocator);
}
public void clickRegisterButton() {
	click(registerBtnLocator);
	
}
	
	public void SignInAsAdmin() {
		if(isDisplayed(emailLocator)){
			type(adminEmail,emailLocator);
			type(adminPassword,passwordLocator);
			click(loginBtnLocator);
			
		
		}
		else {
			System.out.println("Email textbox was not present");
		
	}
		
	}
	public void SignInAsStaff() {
		if(isDisplayed(emailLocator)){
			type(staffEmail,emailLocator);
			type(staffPassword,passwordLocator);
			click(loginBtnLocator);
			
		
		}
		else {
			System.out.println("Email textbox was not present");
		
	}
		
	}
	public void SignInAsClient() {
		if(isDisplayed(emailLocator)){
			type(clientEmail,emailLocator);
			type(clientPassword,passwordLocator);
			click(loginBtnLocator);
			
		
		}
		else {
			System.out.println("Email textbox was not present");
		
	}
		
	}
	public void BadSignInAsAdmin() {
		if(isDisplayed(emailLocator)){
			type(adminEmail,emailLocator);
			type("$%1234addd",passwordLocator);
			click(loginBtnLocator);
			
		
		}
		else {
			System.out.println("Email textbox was not present");
		
	}
		
	}
	
	public void BadRegister() {
		if(isDisplayed(registerPageNameLocator)){
			type("testEmail@gmail.com",registerPageEmailLocator);
			type("abcd1234",registerPagePasswordLocator);
			type("test",registerPageNameLocator);
			type("test",registerPageLastNameLocator);
			click(registerPageRegisterBtnLocator);
			
		
		}
		else {
			System.out.println("Email textbox was not present");
		
	}
		
	}

	public Boolean isErrorIconDisplayed() {
		return isDisplayed(errorIconLocator);
	}
	
	
}


package com.QA.fase3.ticoPlunge.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.QA.fase3.ticoPlunge.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ListaDeUsuariosPage extends base {

	public ListaDeUsuariosPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By ButtonListaUsuarios = By.xpath("/html/body/div/div/div/div[1]/div/div[13]/a");
	private By textInputUser = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[3]/input");
	private By userNameResult = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[4]/table/tbody/tr/td[2]");
	private By userResult = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[4]/table/tbody");
	private By AddUserButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/button");
	private By AddUserName = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input");
	private By AddUserLastName = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/input");
	private By AddUserEmail = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/input");
	private By AddUserPassword = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[4]/input");
	private By SelectUserRol = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[5]/select");
	private By SaveUserButton = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[6]/button[1]");
	private By DeleteUserButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[4]/table/tbody/tr[1]/td[5]/button[2]");
	private By errorPasswordMessage = By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div");
	private By editUserButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[4]/table/tbody/tr[1]/td[5]/button[1]");
	private By editSaveButton = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[5]/button[1]");
	private By cancelEditButton = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[5]/button[2]");
	private By passwordEditButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[4]/table/tbody/tr[1]/td[5]/button[3]");
	private By newPassword = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input");
	private By confirmPassword = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/input");
	private By saveNewPassword = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[1]");
	private By notSamePaswordMessage = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/span");
	private By passwordChangeMessage = By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div/div[2]");
	private By cancelEditPasswordButton = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[2]");
	

	public void clickListaUsuarios() throws InterruptedException {
    	click(ButtonListaUsuarios);
    }
	
	
	public void setUser(String name) {
	    WebElement inputField = driver.findElement(textInputUser);
	    inputField.clear();
	    inputField.sendKeys(name);
	}
	
	public String getUserNameResult() {
        return getText(userNameResult);
    }
	
	public boolean isUserResultEmpty() {
        WebElement resultElement = findElement(userResult);
        String resultText = resultElement.getText();
        return resultText.isEmpty();
    }
	
    public void clickAddUserButton() throws InterruptedException {
        click(AddUserButton);
    }
    
    public void setAddUserName(String name) {
    	WebElement inputField = driver.findElement(AddUserName);
	    inputField.clear();
	    inputField.sendKeys(name);
    }
    
    public void setAddUserLastName(String lastName) {
        type(lastName, AddUserLastName);
    }

    public void setAddUserEmail(String email) {
        type(email, AddUserEmail);
    }

    public void setAddUserPassword(String password) {
        type(password, AddUserPassword);
    }

    public void selectUserRole(String role) {
        WebElement roleDropdown = findElement(SelectUserRol);
        Select select = new Select(roleDropdown);
        select.selectByVisibleText(role);
    }

    public void clickSaveUserButton() throws InterruptedException {
        click(SaveUserButton);
    }
    
    public void clickDeleteUserButton() throws InterruptedException {
    	click(DeleteUserButton);
    	Thread.sleep(1000);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    
    public String getErrorPasswordMessage() {
        WebElement errorElement = findElement(errorPasswordMessage);
        return errorElement.getText();
    }
    
    public void clickeditUserButton() throws InterruptedException {
    	click(editUserButton);
    }
    
    public void clickeditSaveButton() throws InterruptedException {
    	click(editSaveButton);
    }
    
    public String getValidationMessage(WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }
    
    public void clickcancelEditButton() throws InterruptedException {
    	click(cancelEditButton);
    }
    
    public void clickPasswordEditButton() {
        click(passwordEditButton);
    }

    public void setNewPassword(String password) {
        type(password, newPassword);
    }

    public void setConfirmPassword(String password) {
        type(password, confirmPassword);
    }

    public void clickSaveNewPasswordButton() {
        click(saveNewPassword);
    }

    public String getNotSamePasswordMessage() {
        return findElement(notSamePaswordMessage).getText();
    }

    public String getPasswordChangeMessage() {
        return findElement(passwordChangeMessage).getText();
    }
    
    public void clickcancelEditPasswordButton() throws InterruptedException {
    	click(cancelEditPasswordButton);
    }

}

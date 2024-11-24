package com.QA.fase3.ticoPlunge.Pages;

import org.openqa.selenium.WebDriver;

import com.QA.fase3.ticoPlunge.base;

import org.openqa.selenium.By;

public class LoginAdmin extends base {

	public LoginAdmin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private By ButtonMenuIniciarSesion = By.xpath("/html/body/div/div/div/div[1]/div/div[2]/a");
	private By inputEmail = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[1]");
	private By inputPassword = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/input[2]");
	private By ButtonIniciarSesion = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
	
	public void clickIniciarSesion() throws InterruptedException {
    	click(ButtonIniciarSesion);
    }
	
	public void clickMenuIniciarSesion() throws InterruptedException {
    	click(ButtonMenuIniciarSesion);
    }
	
	public void setEmail(String email) {
    	type(email,inputEmail);
    }
	
	public void setPassword(String password) {
    	type(password,inputPassword);
    }
}
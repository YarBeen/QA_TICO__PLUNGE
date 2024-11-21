package com.QA.fase3.ticoPlunge.Pages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeedBackPage extends LoginPagePlunge{
	public String adminEmail = "yarman2005@gmail.com";
	public String adminPassword = "1234.aD$%";
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
	
	By feedBackLinkLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[4]/a");
	By privateFeedBackLinkLocator = By.xpath("/html/body/div/div/div/div[1]/div/div[5]/a");
	By leavePrivateFeedbackBtnLocator = By.xpath("/html/body/div/div/div/div[2]/span/div/div/a");
	By feedBackTextBoxLocator= By.id("inputComentario");
	By submitFeedBackLocatorBtn= By.xpath("/html/body/div/div/div/div[2]/span/div/form/div[5]/button");
	By submitPrivateFeedBackLocatorBtn= By.xpath("/html/body/div/div/div/div[2]/span/div/div/a");
	
	//From this Class
	By successSubmitFeedbackLocator = By.xpath("/html/body/div/div/div/div[2]/span/div/form/div[4]/div/div/div[2]");
	By successSubmitPrivateFeedbackLocator = By.xpath("/html/body/div/div/div/div[2]/span[1]/div/form/div[3]/div/div/div[2]");
	By fourStarIconLocator = By.xpath("/html/body/div/div/div/div[2]/span/div/form/div[2]/label[2]");
	By deleteCommentBtnLocator = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div/span[4]/div/button");
	
	By successDeletingCommentLocator = By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div");
	By createPrivateFeedbackBtnLocator = By.xpath("/html/body/div/div/div/div[2]/span[1]/div/form/div[4]/button");
	
	By deletePrivateCommentBtnLocator = By.xpath("/html/body/div/div/div/div[2]/span[2]/div/div/div/span[3]/div/button");
	public FeedBackPage(WebDriver driver) {
		super(driver);
		
	}
	
	public void clickFeedBackLink() {
		click(feedBackLinkLocator);
	}

	public void clickPrivateFeedBackLink() {
		click(privateFeedBackLinkLocator);
	}

	public void clickLeavePrivateFeedBackBtn() {
		click(leavePrivateFeedbackBtnLocator);
	}
	public boolean commentsExist(String comment) {
		String pageSource = driver.findElement(By.tagName("body")).getText();
		return pageSource.contains(comment);

		
	}
	public void leavePrivateFeedBack(String comment) {
		type(comment,feedBackTextBoxLocator);
		click(createPrivateFeedbackBtnLocator);
	}

	public boolean isSuccessSubmitPrivateFeedbackDisplayed() {
		if (isDisplayed(successSubmitPrivateFeedbackLocator)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void submitEmptyFeedback() {
		if (isDisplayed(feedBackTextBoxLocator)) {
			type("",feedBackTextBoxLocator);
			click(submitFeedBackLocatorBtn);
		} else {
			System.out.println("FeedBack Text Box was not displayed");
			
		}
	}
	
	public void submitFeedback() {
		if (isDisplayed(feedBackTextBoxLocator)) {
			type("Test Comment",feedBackTextBoxLocator);
			click(fourStarIconLocator);
			click(submitFeedBackLocatorBtn);
		} else {
			System.out.println("FeedBack Text Box was not displayed");
			
		}
	}

	public boolean commentsExist() {
		String pageSource = driver.findElement(By.tagName("body")).getText();
		return pageSource.contains("Test Comment");

		
	}
	public boolean isFeedBackTextBoxDisplayed() {
		if (isDisplayed(feedBackTextBoxLocator)) {
			return true;
		} else {
			return false;
		}
	}
	public void clickSubmitPrivateFeedBackBtn() {
		click(submitPrivateFeedBackLocatorBtn);
		
	}
	public void submitPrivateFeedback() {
		if (isDisplayed(feedBackTextBoxLocator)) {
			type("Test Private Comment",feedBackTextBoxLocator);
			click(fourStarIconLocator);
			click(submitPrivateFeedBackLocatorBtn);
		} else {
			System.out.println("FeedBack Text Box was not displayed");
			
		}
	}
	public void deleteComment() {
		click(deleteCommentBtnLocator);
		 Alert alert = driver.switchTo().alert();

         
         alert.accept();
		
	}

	public void deletePrivateComment() {
		click(deletePrivateCommentBtnLocator);
		Alert alert = driver.switchTo().alert();

		alert.accept();

	}

	public boolean isSuccessDeletingCommentDisplayed() {
		if (isDisplayed(successDeletingCommentLocator)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isSuccessSubmitFeedbackDisplayed() {
		if (isDisplayed(successSubmitFeedbackLocator)) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
}


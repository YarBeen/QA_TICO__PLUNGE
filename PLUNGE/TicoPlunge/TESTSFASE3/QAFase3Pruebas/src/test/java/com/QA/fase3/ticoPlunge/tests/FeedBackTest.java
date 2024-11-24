package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;

import java.nio.Buffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.QA.fase3.ticoPlunge.Pages.FeedBackPage;

public class FeedBackTest {
	private WebDriver driver;
	FeedBackPage feedBackPage;
	@Before
	public void setUp() throws Exception {
		feedBackPage = new FeedBackPage(driver);
		
		driver = feedBackPage.chromeDriverConnection();
		feedBackPage.visit("http://localhost:3000/");
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
//Some fields are likely to contain errors. Fix errors and try again.
	@Test
	public void cantLeaveEmptyFeedback() throws InterruptedException {
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsClient();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.submitEmptyFeedback();
		Thread.sleep(2000);
		assertFalse(feedBackPage.isSuccessSubmitFeedbackDisplayed());
		//assertEquals("Lista de Usuarios",feedBackPage.getListaUsuariosLinkText());
        
	}
	
	@Test
	public void staffCanSubmitPublicFeedBack() throws InterruptedException {
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsStaff();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.submitFeedback();
		Thread.sleep(2000);
		assertTrue(feedBackPage.isSuccessSubmitFeedbackDisplayed());
		Thread.sleep(1000);
		feedBackPage.deleteComment();
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessDeletingCommentDisplayed());
		
		//assertEquals("Lista de Usuarios",feedBackPage.getListaUsuariosLinkText());
        
	}
	
	@Test
	public void notLoggedUsersCanSeePublicFeedBack() throws InterruptedException {
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsStaff();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.submitFeedback();
		Thread.sleep(2000);
		assertTrue(feedBackPage.isSuccessSubmitFeedbackDisplayed());
		Thread.sleep(1000);
		feedBackPage.logout();
		Thread.sleep(1000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(1000);
		assertTrue(feedBackPage.commentsExist());
		Thread.sleep(1000);
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsAdmin();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		
		
		feedBackPage.deleteComment();
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessDeletingCommentDisplayed());
		
		//assertEquals("Lista de Usuarios",feedBackPage.getListaUsuariosLinkText());
        
	}
	
	@Test
	public void staffCanSeePrivateFeedBack() throws InterruptedException {
		String comment = "Test Private Comment";
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsClient();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.clickLeavePrivateFeedBackBtn();
		Thread.sleep(2000);
		feedBackPage.leavePrivateFeedBack(comment);
		Thread.sleep(2000);
		assertTrue(feedBackPage.isSuccessSubmitPrivateFeedbackDisplayed());
		Thread.sleep(1000);
		feedBackPage.logout();
		Thread.sleep(1000);
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsStaff();
		Thread.sleep(2000);
		feedBackPage.clickPrivateFeedBackLink();
		Thread.sleep(2000);
		assertTrue(feedBackPage.commentsExist(comment));
		Thread.sleep(1000);
		feedBackPage.logout();
		Thread.sleep(1000);
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsAdmin();
		Thread.sleep(2000);
		feedBackPage.clickPrivateFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.deletePrivateComment();
		
		
		
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessDeletingCommentDisplayed());
		
	
        
	}
	
	@Test
	public void adminCanDeletePrivateFeedBack() throws InterruptedException {
		String comment = "Test Private Comment";
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsClient();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.clickLeavePrivateFeedBackBtn();
		Thread.sleep(2000);
		feedBackPage.leavePrivateFeedBack(comment);
		Thread.sleep(2000);
		assertTrue(feedBackPage.isSuccessSubmitPrivateFeedbackDisplayed());
		Thread.sleep(1000);
		feedBackPage.logout();
		Thread.sleep(1000);
		
		Thread.sleep(1000);
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsAdmin();
		Thread.sleep(2000);
		feedBackPage.clickPrivateFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.deletePrivateComment();
		
		
		
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessDeletingCommentDisplayed());
		
	
        
	}
	
	@Test
	public void adminCanDeletePublicFeedBack() throws InterruptedException {
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsStaff();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.submitFeedback();
		Thread.sleep(2000);
		assertTrue(feedBackPage.isSuccessSubmitFeedbackDisplayed());
		Thread.sleep(1000);
		feedBackPage.logout();
		Thread.sleep(1000);
		
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsAdmin();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		
		
		feedBackPage.deleteComment();
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessDeletingCommentDisplayed());
		
	
        
	}
	@Test
	//FALLA
	public void staffCanSubmitPrivateFeedBack() throws InterruptedException {
		feedBackPage.clickLoginLink();
		Thread.sleep(2000);
		feedBackPage.SignInAsStaff();
		Thread.sleep(2000);
		feedBackPage.clickFeedBackLink();
		Thread.sleep(2000);
		feedBackPage.clickSubmitPrivateFeedBackBtn();;
		Thread.sleep(2000);
		assertTrue(feedBackPage.isFeedBackTextBoxDisplayed());
		Thread.sleep(1000);
		//No se ejecuta puesto que no aparece el boton
		feedBackPage.submitPrivateFeedback();
		Thread.sleep(1000);
		assertTrue(feedBackPage.isSuccessSubmitFeedbackDisplayed());
		
		
		
        
	}
	


	

}

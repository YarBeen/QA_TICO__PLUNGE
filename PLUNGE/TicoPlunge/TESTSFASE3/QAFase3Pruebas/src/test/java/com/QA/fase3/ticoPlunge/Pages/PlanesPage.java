package com.QA.fase3.ticoPlunge.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.QA.fase3.ticoPlunge.base;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;

public class PlanesPage extends base {

	public PlanesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private By ButtonPlanes = By.xpath("/html/body/div/div/div/div[1]/div/div[10]/a");
	private By PlanNameInput = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[1]/input");
	private By PlanPriceInput = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[2]/div/input");
	private By CreatePlanButton = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/button");
	private By PlanName = By.xpath("/html/body/div/div/div/div[2]/div[3]/div/span[1]");
	private By EditPlanNameInput = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input");
	private By EditPlanPriceInput = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/input");
	private By ButtonEditSavePlan = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[1]");
	private By ButtonDeletePlan = By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/button[2]");
	private By PlanPrice = By.xpath("//span[contains(@class, 'card-subtitle-plan') and contains(text(), 'Precio')]");
	private By successCreateMessage = By.xpath("/html/body/div/div/div/div[2]/div[1]/form/div[3]/div/div/div[2]");
	
	
	 public void clickButtonPlanes() throws InterruptedException {
	        click(ButtonPlanes);
	        Thread.sleep(1000);
	    }

	  public void setPlanName(String name) {
	       type(name, PlanNameInput);
	   }
	  
	  public void clickPlanEdit() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    
		    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(PlanName));
		    
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
		    
		    if (element.isDisplayed() && element.isEnabled()) {
		        try {
		            element.click();
		        } catch (ElementClickInterceptedException e) {
		            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		        }
		    } else {
		        System.out.println("El elemento no está disponible para clic.");
		    }
		}


	    public void setPlanPrice(String price) {
	        type(price, PlanPriceInput);
	    }

	    public void clickCreatePlanButton() throws InterruptedException {
	        click(CreatePlanButton);
	        Thread.sleep(1000);
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    }
	    
	    public void clickCreateButton() throws InterruptedException {
	        click(CreatePlanButton);
	    }

	    public void editPlanName(String newName) {
	        type(newName, EditPlanNameInput);
	    }

	    public void editPlanPrice(String newPrice) {
	    	WebElement inputField = driver.findElement(EditPlanPriceInput);
		    inputField.clear();
		    inputField.sendKeys(newPrice);
	    }

	    public void clickEditSavePlanButton() throws InterruptedException {
	        click(ButtonEditSavePlan);
	        Thread.sleep(1000);
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    }

	    public void clickDeletePlanButton() throws InterruptedException {
	        click(ButtonDeletePlan);
	        Thread.sleep(1000);
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    }
	    
	    public void verifyDeleteAlert() {
	        try {
	        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	            String alertText = alert.getText();
	            if ("Plan eliminado exitosamente.".equals(alertText)) {
	                alert.accept();
	            } else {
	                throw new AssertionError("El mensaje de la alerta no es el esperado: " + alertText);
	            }
	        } catch (NoAlertPresentException e) {
	            throw new AssertionError("No se presentó la alerta esperada.");
	        }
	    }

	    public String getPlanName() {
	        try {
	            return getText(PlanName);
	        } catch (NoSuchElementException e) {
	            return null;
	        }
	    }
	    
	    public String getValidationMessage(WebElement element) {
	        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
	    }
	    
	    public String getPlanPrice() {
	        String fullText = getText(PlanPrice);
	        if (fullText != null && fullText.contains("₡")) {
	            return fullText.split("₡")[1].trim();
	        }
	        return "";
	    }
	    
	    public String getCreateMessage() {
	        String fullText = getText(successCreateMessage);
	        return fullText;
	    }


}

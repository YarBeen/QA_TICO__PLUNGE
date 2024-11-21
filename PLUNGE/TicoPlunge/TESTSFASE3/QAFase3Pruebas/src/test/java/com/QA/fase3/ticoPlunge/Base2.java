package com.QA.fase3.ticoPlunge;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base2 {
	protected WebDriver driver;
	
	public Base2(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe" );
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebElement findElement(By locator) {
		
		return driver.findElement(locator);
	}
	
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	public String getValue(WebElement element) {
		return element.getAttribute("value");
	}
	public String getValue(By locator) {
		return driver.findElement(locator).getAttribute("value");
	}
	public String getText(By locator) {
		return driver.findElement(locator).getText();
		
	}

	public void type (String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
		
	}
	public void clearText ( By locator) {
		driver.findElement(locator).clear();
		
	}
	
	
	public void click(By locator) {
		
		driver.findElement(locator).click();
	}
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
			
		}catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.print("Not found");
			return false;
		}
		
	}
	
	public void visit(String url) {
		driver.get(url);
		
	}
}















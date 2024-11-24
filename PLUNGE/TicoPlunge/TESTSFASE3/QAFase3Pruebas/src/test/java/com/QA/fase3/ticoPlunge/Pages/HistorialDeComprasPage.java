package com.QA.fase3.ticoPlunge.Pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.QA.fase3.ticoPlunge.base;

public class HistorialDeComprasPage extends base {

	public HistorialDeComprasPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private By ButtonHistorialDeCompras = By.xpath("/html/body/div/div/div/div[1]/div/div[9]/a");
	private By ButtonAnadirCompra = By.xpath("/html/body/div/div/div/div[2]/div/button[1]");
	private By SelectCliente = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/div[1]/select");
	private By InputMontoCompra = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/div[2]/input");
	private By InputDetalleCompra = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/div[3]/textarea");
	private By ButtonCompra = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/form/button");
	private By ButtonEliminarCompra = By.xpath("/html/body/div/div/div/div[2]/div/table/tbody/tr/td[5]/button");
	private By InputBuscarCompra = By.xpath("/html/body/div/div/div/div[2]/div/div[2]/span/input");
	private By ButtonExportarExcel = By.xpath("/html/body/div/div/div/div[2]/div/button[2]");
	private By tablaInfoCompra = By.xpath("/html/body/div/div/div/div[2]/div/table/tbody/tr");
	

	public void clickButtonHistorialDeCompras() {
	    click(ButtonHistorialDeCompras);
	}

	public void clickButtonAnadirCompra() {
	    click(ButtonAnadirCompra);
	}

	public void selectFirstCliente() {
	    try {
	        Select dropdown = new Select(driver.findElement(SelectCliente));
	        dropdown.selectByIndex(1);
	    } catch (NoSuchElementException e) {
	        throw new NoSuchElementException("No se pudo encontrar el desplegable o las opciones.");
	    }
	}

	public void setInputMontoCompra(String monto) {
	    type(monto,InputMontoCompra);
	}

	public void setInputDetalleCompra(String detalle) {
	    type(detalle, InputDetalleCompra);
	}

	public void clickButtonCompra() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ButtonCompra));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
	}

	public void clickButtonEliminarCompra() {
	    click(ButtonEliminarCompra);
	}
	
	public String getValidationMessage(WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

	public void searchCompra(String textoBusqueda) {
	    type(textoBusqueda, InputBuscarCompra);
	}

	public void clickButtonExportarExcel() {
	    click(ButtonExportarExcel);
	}
	
	public String getCompraDetails() {
	    WebElement tableRow = driver.findElement(tablaInfoCompra); 
	    return tableRow.getText();
	}
	
	public void selectClientePorNombre(String nombre) {
	    try {
	        Select dropdown = new Select(driver.findElement(SelectCliente));
	        for (WebElement option : dropdown.getOptions()) {
	            if (option.getText().equals(nombre)) {
	                option.click();
	                return;
	            }
	        }
	        throw new NoSuchElementException("Cliente con nombre '" + nombre + "' no encontrado.");
	    } catch (NoSuchElementException e) {
	        throw new NoSuchElementException("No se pudo encontrar el desplegable o las opciones.");
	    }
	}
	
	public boolean isCompraEliminada(String monto, String detalle) {
	    List<WebElement> rows = driver.findElements(tablaInfoCompra);
	    for (WebElement row : rows) {
	        if (row.getText().contains(monto) || row.getText().contains(detalle)) {
	            return false;
	        }
	    }
	    return true;
	}

}

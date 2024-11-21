package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.Pages.HistorialDeComprasPage;
import com.QA.fase3.ticoPlunge.Pages.LoginAdmin;

public class HistorialDeComprasTest {
	WebDriver driver;
	HistorialDeComprasPage historialPage;
	LoginAdmin loginAdmin;
	String adminEmail = "";
	String adminPassword = "";

    @Before
    public void setUp() {
    	historialPage = new HistorialDeComprasPage(driver);
        driver = historialPage.chromeDriverConnection();
        driver.manage().window().maximize();
        historialPage.visit("http://localhost:3000");

        loginAdmin = new LoginAdmin(driver);
        try {
            loginAdmin.clickMenuIniciarSesion();
            Thread.sleep(1000);
            loginAdmin.setEmail(adminEmail);
            loginAdmin.setPassword(adminPassword);
            loginAdmin.clickIniciarSesion();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAnadirCompraExitosamente() {
        try {
            historialPage.clickButtonHistorialDeCompras();
            Thread.sleep(1000);

            historialPage.clickButtonAnadirCompra();
            Thread.sleep(1000);

            historialPage.selectFirstCliente();
            Thread.sleep(500);

            historialPage.setInputMontoCompra("5000");
            historialPage.setInputDetalleCompra("Compra de clase prueba");
            historialPage.clickButtonCompra();
            Thread.sleep(2000);

            historialPage.searchCompra("5000");
            Thread.sleep(1000);

            String historialData = historialPage.getCompraDetails();
            assertTrue(historialData.contains("5000"));
            assertTrue(historialData.contains("Compra de clase prueba"));
            
            historialPage.clickButtonEliminarCompra();
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test falló debido a una interrupción inesperada.");
        }
    }
    
    
    @Test
    public void testAnadirCompraConInformacionFaltante() {
        try {
            historialPage.clickButtonHistorialDeCompras();
            Thread.sleep(1000);

            historialPage.clickButtonAnadirCompra();
            Thread.sleep(1000);

            historialPage.selectFirstCliente();
            Thread.sleep(500);
            
            WebElement montoField = driver.findElement(By.cssSelector("input[required]")); 
            montoField.clear();

            historialPage.clickButtonCompra();
            Thread.sleep(1000);
            
            String errorMessage = historialPage.getValidationMessage(montoField);
            assertEquals("Please fill out this field.", errorMessage);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test falló debido a una interrupción inesperada.");
        }
    }
    
    @Test
    public void testBuscarCompraPorNombreCliente() {
        try {
            historialPage.clickButtonHistorialDeCompras();
            Thread.sleep(1000);
            historialPage.clickButtonAnadirCompra();
            Thread.sleep(1000);
            historialPage.selectClientePorNombre("John Doe");
            Thread.sleep(500);

            historialPage.setInputMontoCompra("1500");
            historialPage.setInputDetalleCompra("Compra para prueba de búsqueda");
            historialPage.clickButtonCompra();
            Thread.sleep(2000);

            String nombreCliente = "John Doe";
            historialPage.searchCompra(nombreCliente);
            Thread.sleep(1000);

            String resultadosBusqueda = historialPage.getCompraDetails();
            assertTrue(resultadosBusqueda.contains(nombreCliente));
            
            historialPage.clickButtonEliminarCompra();
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test falló debido a una interrupción inesperada.");
        }
    }
    
    @Test
    public void testEliminarCompraDelHistorial() {
        try {
            historialPage.clickButtonHistorialDeCompras();
            Thread.sleep(1000);

            historialPage.clickButtonAnadirCompra();
            Thread.sleep(1000);
            historialPage.selectFirstCliente();
            Thread.sleep(500);
            historialPage.setInputMontoCompra("7500");
            historialPage.setInputDetalleCompra("Compra para prueba de eliminación");
            historialPage.clickButtonCompra();
            Thread.sleep(2000);

            historialPage.searchCompra("7500");
            Thread.sleep(1000);
            String historialData = historialPage.getCompraDetails();
            assertTrue(historialData.contains("7500"));
            assertTrue(historialData.contains("Compra para prueba de eliminación"));

            historialPage.clickButtonEliminarCompra();
            Thread.sleep(2000);
            
            assertTrue(historialPage.isCompraEliminada("7500", "Compra para prueba de eliminación"));

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test falló debido a una interrupción inesperada.");
        }
    }


}

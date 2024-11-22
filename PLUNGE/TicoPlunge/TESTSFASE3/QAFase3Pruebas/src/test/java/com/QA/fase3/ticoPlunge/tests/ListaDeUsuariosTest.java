package com.QA.fase3.ticoPlunge.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.QA.fase3.ticoPlunge.Pages.ListaDeUsuariosPage;
import com.QA.fase3.ticoPlunge.Pages.LoginAdmin;

public class ListaDeUsuariosTest {
    WebDriver driver;
    ListaDeUsuariosPage listaDeUsuariosPage;
    LoginAdmin loginAdmin;
   
	String adminEmail = "TestAdmin@gmail.com";
	String adminPassword = "1234.aD$%";
	String staffEmail2 = "TestStaff2@gmail.com";
	String staffPassword2 = "TestStaff2@gmail.com";
	String staffEmail3 = "TestStaff3@gmail.com";
	String staffPassword3 = "TestStaff3@gmail.com";
	String testClientMail = "john@prueba.com";
	String testClientPassword = "newPassword123!";

    @Before
    public void setUp() {
        listaDeUsuariosPage = new ListaDeUsuariosPage(driver);
        driver = listaDeUsuariosPage.chromeDriverConnection();
        driver.manage().window().maximize();
        listaDeUsuariosPage.visit("http://localhost:3000");

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
    public void testBuscarUsuarioPorNombre() {
    	try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);
            
            String nombreUsuario = "John Doe";
            listaDeUsuariosPage.setUser(nombreUsuario);

            String resultado = listaDeUsuariosPage.getUserNameResult();
            assertEquals("El nombre del usuario no coincide", nombreUsuario, resultado);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testBuscarUsuarioRolInexistente() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            String rolInexistente = "Profesor";
            listaDeUsuariosPage.setUser(rolInexistente);

            boolean isEmpty = listaDeUsuariosPage.isUserResultEmpty();
            assertTrue("La tabla de resultados  está vacía para un usuario inexistente", isEmpty);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testAgregarUsuario() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.clickAddUserButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setAddUserName("Josh");
            listaDeUsuariosPage.setAddUserLastName("Doe");
            listaDeUsuariosPage.setAddUserEmail("josh@prueba.com");
            listaDeUsuariosPage.setAddUserPassword("joshPrueba123!");
            listaDeUsuariosPage.selectUserRole("Client");

            listaDeUsuariosPage.clickSaveUserButton();
            Thread.sleep(2000);


            listaDeUsuariosPage.setUser("Josh Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("Josh Doe", userNameResult);
            
            listaDeUsuariosPage.clickDeleteUserButton();
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testAgregarUsuarioConContraseñaIncorrecta() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.clickAddUserButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setAddUserName("Josh");
            listaDeUsuariosPage.setAddUserLastName("Doe");
            listaDeUsuariosPage.setAddUserEmail("josh@prueba.com");
            listaDeUsuariosPage.setAddUserPassword("josh123");
            listaDeUsuariosPage.selectUserRole("Client");

            listaDeUsuariosPage.clickSaveUserButton();
            Thread.sleep(2000);

            String expectedErrorMessage = "\"Password\" should be at least 8 characters long";
            String actualErrorMessage = listaDeUsuariosPage.getErrorPasswordMessage();
            assertEquals(expectedErrorMessage, actualErrorMessage);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }


    
    @Test
    public void testEliminarUsuario() {
    	try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.clickAddUserButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setAddUserName("Juan");
            listaDeUsuariosPage.setAddUserLastName("Doe");
            listaDeUsuariosPage.setAddUserEmail("juan@prueba.com");
            listaDeUsuariosPage.setAddUserPassword("juanPrueba123!");
            listaDeUsuariosPage.selectUserRole("Client");

            listaDeUsuariosPage.clickSaveUserButton();
            Thread.sleep(2000);

            listaDeUsuariosPage.setUser("Juan Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("Juan Doe", userNameResult);

            listaDeUsuariosPage.clickDeleteUserButton();
            Thread.sleep(2000);

            listaDeUsuariosPage.setUser("Juan Doe");
            boolean isUserEmpty = listaDeUsuariosPage.isUserResultEmpty();
            assertTrue(isUserEmpty);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testEditarUsuario() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.clickAddUserButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setAddUserName("Josh");
            listaDeUsuariosPage.setAddUserLastName("Doe");
            listaDeUsuariosPage.setAddUserEmail("josh@prueba.com");
            listaDeUsuariosPage.setAddUserPassword("joshPrueba123!");
            listaDeUsuariosPage.selectUserRole("Client");

            listaDeUsuariosPage.clickSaveUserButton();
            Thread.sleep(2000);

            listaDeUsuariosPage.setUser("Josh Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("Josh Doe", userNameResult);

            listaDeUsuariosPage.clickeditUserButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setAddUserName("Juan");
            listaDeUsuariosPage.clickeditSaveButton();
            Thread.sleep(2000);

            listaDeUsuariosPage.setUser("Juan Doe");
            String editedUserNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("Juan Doe", editedUserNameResult);


            listaDeUsuariosPage.clickDeleteUserButton();
            Thread.sleep(2000);

            listaDeUsuariosPage.setUser("Juan Doe");
            boolean isUserEmpty = listaDeUsuariosPage.isUserResultEmpty();
            assertTrue("El usuario debería haber sido eliminado", isUserEmpty);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testEditarUsuarioSinNombre() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.setUser("John Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("John Doe", userNameResult);

            listaDeUsuariosPage.clickeditUserButton();
            Thread.sleep(1000);

            WebElement nameField = driver.findElement(By.cssSelector("input[required]")); 
            nameField.clear();

            listaDeUsuariosPage.clickeditSaveButton();
            Thread.sleep(1000);

            String errorMessage = listaDeUsuariosPage.getValidationMessage(nameField);
            assertEquals("Please fill out this field.", errorMessage);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }

    @Test
    public void testEditarContraseñaDeUsuario() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.setUser("John Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("John Doe", userNameResult);

            listaDeUsuariosPage.clickPasswordEditButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setNewPassword("newPassword123!");
            listaDeUsuariosPage.setConfirmPassword("newPassword123!");
            listaDeUsuariosPage.clickSaveNewPasswordButton();
            Thread.sleep(2000);

            String passwordChangeMessage = listaDeUsuariosPage.getPasswordChangeMessage();
            assertEquals("User updated successfully", passwordChangeMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }
    
    @Test
    public void testEditarContraseñaIncorrecta() {
        try {
            listaDeUsuariosPage.clickListaUsuarios();
            Thread.sleep(1000);

            listaDeUsuariosPage.setUser("John Doe");
            String userNameResult = listaDeUsuariosPage.getUserNameResult();
            assertEquals("John Doe", userNameResult);

            listaDeUsuariosPage.clickPasswordEditButton();
            Thread.sleep(1000);

            listaDeUsuariosPage.setNewPassword("newPassword123!");
            listaDeUsuariosPage.setConfirmPassword("differentPassword123!");
            listaDeUsuariosPage.clickSaveNewPasswordButton();
            Thread.sleep(2000);

            String errorMessage = listaDeUsuariosPage.getNotSamePasswordMessage();
            assertEquals("Las contraseñas no coinciden", errorMessage);

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test interrumpido debido a una excepción");
        }
    }

}


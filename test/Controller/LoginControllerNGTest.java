/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author PPuarat
 */
public class LoginControllerNGTest {
    
    public LoginControllerNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of login method, of class LoginController.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "ppuarat";
        char[] password = {'1','2','3','4','5','6'};
        LoginController instance = new LoginController();
        boolean result = instance.login(username, password);
        assertTrue(result);
    }
    
     @Test
    public void testLoginFalseCase() {
        System.out.println("login");
        String username = "sawpangseen";
        char[] password = {'1','2','3','4','5','6'};
        LoginController instance = new LoginController();
        boolean result = instance.login(username, password);
        assertFalse(result);
    }
    
}

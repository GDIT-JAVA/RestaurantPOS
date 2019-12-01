/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Food;
import Models.Payment;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class UtilsNGTest {
    
    public UtilsNGTest() {
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
     * Test of calculateTotalPrice method, of class Utils.
     */
    @Test
    public void testCalculateTotalPrice() {
        System.out.println("calculateTotalPrice");
        ArrayList<Food> foods = new ArrayList<>();
        //Expected 10 foods 
        for(int i = 0; i<10;i++){
            Food food = new Food();
            food.setFoodName("Food " + i);
            food.setPrice(10.5);
            foods.add(food);
        }
        double expResult = 105.0;
        double result = Utils.calculateTotalPrice(foods);
        assertEquals(result, expResult);
    }
    @Test
    public void testCalculateTotalPriceFalseCase() {
        System.out.println("calculateTotalPrice");
        ArrayList<Food> foods = new ArrayList<>();
        //Expected 10 foods 
        for(int i = 0; i<10;i++){
            Food food = new Food();
            food.setFoodName("Food " + i);
            food.setPrice(1);
            foods.add(food);
        }
        double expResult = 10.01;
        double result = Utils.calculateTotalPrice(foods);
        assertNotEquals(result, expResult);
    }

    /**
     * Test of caculateTotalPaid method, of class Utils.
     */
    @Test
    public void testCaculateTotalPaid() {
        System.out.println("caculateTotalPaid");
        ArrayList<Payment> payments = new ArrayList<>();
        for(int i = 0; i <100; i++){
            Payment payM = new Payment();
            payM.setTotalPaid(100);
            payments.add(payM);
        }
        Utils instance = new Utils();
        double expResult = 10000;
        double result = instance.caculateTotalPaid(payments);
        assertEquals(result, expResult);
    }
    @Test
    public void testCaculateTotalPaidFalseCase() {
        System.out.println("caculateTotalPaid");
        ArrayList<Payment> payments = new ArrayList<>();
        for(int i = 0; i <100; i++){
            Payment payM = new Payment();
            payM.setTotalPaid(i);
            payments.add(payM);
        }
        Utils instance = new Utils();
        double expResult = 100000;
        double result = instance.caculateTotalPaid(payments);
        assertNotEquals(result, expResult);
    }

    /**
     * Test of getSqlTimestamp method, of class Utils.
     */
    @Test
    public void testGetSqlTimestamp() {
        System.out.println("getSqlTimestamp");
        Timestamp expResult = new Timestamp(System.currentTimeMillis());
        Timestamp result = Utils.getSqlTimestamp();
        assertEquals(result, expResult);
    }
    
    @Test
    public void testGetSqlTimestampCheckInstance() {
        System.out.println("getSqlTimestamp");
        Timestamp result = Utils.getSqlTimestamp();
        assertTrue(result instanceof Timestamp);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAOs.FoodDAO;
import DAOs.FoodTypeDAO;
import Models.Food;
import Models.FoodType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PPuarat
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        // TODO code application logic here
       test2();

    }
    
    public void test1(){
         FoodTypeDAO foodTypeDAO = new FoodTypeDAO();
        ArrayList<FoodType> foodTypes = foodTypeDAO.searchAll();
        System.out.println("Utils.Testing.main()");
        FoodDAO foodDAO = new FoodDAO(foodTypes);
        ArrayList<Food> foods = foodDAO.searchAll();

        for (Food food : foods) {

            System.out.println(food.getFoodName() + " " + food.getFoodType().getTypeName());
        }
    }
    
    public static void test2(){
        System.out.println(Utils.getDate());
    }

}

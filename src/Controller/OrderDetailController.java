/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.FoodDAO;
import DAOs.FoodTypeDAO;
import DAOs.OrderDAO;
import Models.Food;
import Models.FoodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PPuarat
 */
public class OrderDetailController {

    public Map<String, ArrayList<Food>> loadFoods() {
        FoodTypeDAO foodTypeDAO = new FoodTypeDAO();

        ArrayList<FoodType> foodTypes = foodTypeDAO.searchAll();
        FoodDAO foodDAO = new FoodDAO(foodTypes);

        //ArrayList<Food> foods = foodDAO.searchAll();

        Map<String, ArrayList<Food>> map = new HashMap();

        for (int i = 0; i < foodTypes.size(); i++) {
            map.put(foodTypes.get(i).getTypeName(),
                    foodDAO.searchByFoodType(foodTypes.get(i).getId()));

        }
        return map;
    }
    
    public boolean createOrder(){
        OrderDAO orderDAO = new OrderDAO();
        
        return true;
    }
}

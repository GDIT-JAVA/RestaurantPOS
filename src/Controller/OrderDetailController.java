/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.FoodDAO;
import DAOs.FoodTypeDAO;
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
    
    public Map<String, ArrayList> loadFoods(){
        FoodTypeDAO foodTypeDAO = new FoodTypeDAO();
        
        ArrayList<FoodType> foodTypes = foodTypeDAO.searchAll();
        FoodDAO foodDAO = new FoodDAO(foodTypes);
        
        ArrayList<Food> foods = foodDAO.searchAll();
        
        Map<String, ArrayList> map = new HashMap();
        map.put("foodTypes", foodTypes);
        map.put("foods", foods);
        
        return map;
    }
}

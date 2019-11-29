/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.FoodDAO;
import DAOs.FoodTypeDAO;
import DAOs.OrderDAO;
import DAOs.OrderDetailDAO;
import Models.Food;
import Models.FoodType;
import Models.Order;
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

    public Order createOrder(ArrayList<Food> orderedFoods) {
        OrderDAO orderDao;
        orderDao = new OrderDAO();

        OrderDetailDAO orderDetailDao;
        orderDetailDao = new OrderDetailDAO();

        long latestOrderId;
        latestOrderId = orderDao.create();

        Order latestOrder = null;

        //if new order is created
        if (latestOrderId != 0) {
            latestOrder = orderDao.searchById(latestOrderId);

            for (Food food : orderedFoods) {

                orderDetailDao.create(latestOrder, food);
            }

        }
        return latestOrder;
    }
}

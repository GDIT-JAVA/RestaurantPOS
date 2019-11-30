/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.OrderDAO;
import DAOs.OrderDetailDAO;
import Models.Order;
import Models.OrderDetail;
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class OrderManagementController {
    
    public ArrayList<Order> initOrderManagement(){
        ArrayList<Order> orders;
        
        OrderDAO orderDao = new OrderDAO();
        orders = orderDao.searchUnpaid();
        
        return orders;
        
    }
    
    public ArrayList<OrderDetail> getOrderDetails(Order order){
        
        ArrayList<OrderDetail> orderDetails = null;
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        orderDetails = orderDetailDao.searchByOrder(order);
        
        return orderDetails;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.CustomerDAO;
import DAOs.OrderDAO;
import DAOs.PaymentDAO;
import Models.Customer;
import Models.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PPuarat
 */
public class PaymentController {

    public Map<String, ArrayList> initPayment() {

        CustomerDAO customerDao = new CustomerDAO();

        Map<String, ArrayList> returnMap = new HashMap<>();
        returnMap.put("customers", customerDao.searchAll());

        return returnMap;

    }

    public boolean pay(Order order,
            ArrayList<Models.OrderDetail> orderDetails,
            Customer selectedCustomer) {

        OrderDAO orderDao = new OrderDAO();
        PaymentDAO paymentDao = new PaymentDAO();
        double totalAmount = 0;
        //Update customer
        orderDao.updateCustomer(order, selectedCustomer);

        totalAmount = orderDetails.stream().map((orderDetail) -> 
                orderDetail.getFood().getPrice()).reduce(totalAmount, (accumulator, _item) -> 
                        accumulator + _item);
        System.out.println("Total: " + totalAmount);
        boolean isPaid = paymentDao.create(order, totalAmount);
        
        return isPaid;
    }
}

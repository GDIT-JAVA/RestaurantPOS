/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.OrderDAO;
import DAOs.PaymentDAO;
import Models.Order;
import Views.Order.Payment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Aspire2 Student
 */
public class ReportController {
    
    public Map<String, ArrayList<Payment>> loadPayments() {
        OrderDAO orderDAO = new OrderDAO();

        //ArrayList<Order> orders = orderDAO.searchAll();
        //PaymentDAO paymentDAO = new PaymentDAO(orders);

        //ArrayList<Food> foods = foodDAO.searchAll();

        Map<String, ArrayList<Payment>> map = new HashMap();

        /*for (int i = 0; i < orders.size(); i++) {
            map.put(orders.get(i).getId(),
                    paymentDAO.searchAll(orders.get(i).getCustomer()));

        }
        return map;
    }*/
    
}

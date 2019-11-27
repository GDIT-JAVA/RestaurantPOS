/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Order;
import Models.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author Aspire2 Student
 */
public class PaymentDAO {
    
    private final static String TABLE = "payments";
    private ArrayList<Order> orders;
    
    public PaymentDAO(ArrayList<Order> orders){
        this.orders = orders;
        System.out.println("DAOs.PaymentDAO.<init>()");
    }
    
    public ArrayList<Payment> searchAll(){
    
        ArrayList<Payment> payments = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            payments = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return payments;
        
    }
    
    private ArrayList<Payment> map(ResultSet rs) throws SQLException {

        ArrayList<Payment> payments = new ArrayList<>();

        while (rs.next()) {

            Payment payment = new Payment();

            payment.setID(rs.getLong("id"));
            payment.setCreatedAt(rs.getString("created_at"));
            payment.setDescription(rs.getString("description"));
            payment.setTotalPaid(rs.getDouble("price"));
            payment.setIsActive(rs.getBoolean("is_active"));
            //TODO SET ORDER HERE
            payment.setOrder(mapOrder(rs.getLong("order_id")));
            payments.add(payment);
        }

        return payments;
    }
    
     private Order mapOrder(long OrderID) {

        for (Order order : this.orders) {
            if (order.getId() == OrderID) {
                return order;
            }
        }

        return null;
    }
     
}

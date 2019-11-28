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
    private OrderDAO orderDAO = new OrderDAO();
    
    public PaymentDAO(){
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
    
    private ArrayList<Payment> searchByTimeInterval(String timeInterval){
    
    ArrayList<Payment> payments = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.connect();
            String SQL = "SELECT count(1) "
                    + "FROM " + TABLE + " WHERE is_active = true "
                    + " and created_at > now() - interval ?;";
            
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, timeInterval);
            ResultSet rs = stmt.executeQuery();
            
            payments = this.map(rs);
            
            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
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
            payment.setOrder(orderDAO.searchById(rs.getLong("order_id")));
            payment.setCreatedAt(rs.getString("created_at"));
            payment.setTotalPaid(rs.getDouble("total"));
            payment.setDescription(rs.getString("description"));
            payment.setIsActive(rs.getBoolean("is_active"));
            
            payments.add(payment);
        }

        return payments;
    }
    
}

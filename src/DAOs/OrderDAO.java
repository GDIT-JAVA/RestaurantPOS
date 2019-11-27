/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Order;
import Utils.Settings;
import Utils.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class OrderDAO {

    private final static String TABLE = "orders";

    public boolean create() {
        boolean isCreated = false;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO " + TABLE + " (customer_id, user_id, is_takeAway, "
                    + "created_at, is_active)"
                    + "VALUES(?, ?, ?, ?, ?);";

            stmt = conn.prepareStatement(SQL);
            
            //set customer. 6 means INTEGER
            stmt.setNull(1, 6);
            //set user
            stmt.setLong(2, 1);
            //set is_takeAway
            stmt.setBoolean(2, false);
            //set date
            stmt.setString(4, Utils.getDate());
            //set is_active
            stmt.setBoolean(5, true);
            
            //Check query
            //System.out.println(stmt);
            //ResultSet rs = stmt.executeQuery();
            if (stmt.executeUpdate() > 0) {
                isCreated = true;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return isCreated;
    }
    
    public ArrayList<Order> searchAll() {
        ArrayList<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, customer_id, user_id, is_takeaway, created_at, is_active "
                    + "FROM "+ TABLE +" "
                    + "WHERE is_active=?;";

            stmt = conn.prepareStatement(SQL);
            
            //set is_active
            stmt.setBoolean(1, true);
            
            //Check query
            //System.out.println(stmt);
            
            ResultSet rs = stmt.executeQuery();
            orders = map(rs);
           
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return orders;
    }
    
     private ArrayList<Order> map(ResultSet rs) throws SQLException {

        ArrayList<Order> orders = new ArrayList<>();

        while (rs.next()) {

            Order order = new Order();

            order.setId(rs.getLong("id"));
            order.setCreatedAt(rs.getString("created_at"));
            order.setIsTakeAway(rs.getBoolean("is_takeaway"));
            order.setIsActive(rs.getBoolean("is_active"));
            order.setCustomer(null);
            order.setUser(null);
            
            orders.add(order);
        }

        return orders;
    }
}

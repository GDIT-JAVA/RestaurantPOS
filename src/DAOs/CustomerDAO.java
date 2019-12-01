/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Customer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author PPuarat
 */
public class CustomerDAO {

    private final static String TABLE = "customers";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    Date date = Calendar.getInstance().getTime();
    

    public ArrayList<Customer> searchAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, first_name, last_name, phone, email, "
                    + "description, created_at, is_active "
                    + "FROM " + TABLE + " "
                    + "WHERE is_active=?;";

            stmt = conn.prepareStatement(SQL);

            //set is_active
            stmt.setBoolean(1, true);

            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            customers = map(rs);

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return customers;
    }
    
    public void addCustomer(String fName, String lName, String phone, String email, String description){
        
        LocalDate localDate = LocalDate.now();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO public.customers (first_name,last_name,phone,email,description,created_at,is_active) VALUES"
                    + " (?,?,?,?,?,?,?); ";

            stmt = conn.prepareStatement(SQL);
            
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setString(5, description);
            stmt.setObject(6, localDate);
            stmt.setBoolean(7, true);
            
            stmt.executeUpdate();
            
        }
        catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, 
                    e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
    }

    public Customer searchById(long id) {
        Customer customer = new Customer();
        Connection conn = null;
        PreparedStatement stmt = null;
        int index = 1;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, first_name, last_name, phone, email, "
                    + "description, created_at, is_active "
                    + "FROM " + TABLE + " "
                    + "WHERE is_active=? and id=?;";

            stmt = conn.prepareStatement(SQL);

            //set is_active
            stmt.setBoolean(index++, true);
            stmt.setLong(index++, id);

            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = mapSingle(rs);

            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return customer;
    }

    private ArrayList<Customer> map(ResultSet rs) throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        while (rs.next()) {

            customers.add(mapSingle(rs));
        }

        return customers;
    }

    private Customer mapSingle(ResultSet rs) throws SQLException {

        Customer customer = new Customer();

        customer.setId(rs.getLong("id"));
        customer.setCreatedAt(rs.getString("created_at"));
        customer.setIsActive(rs.getBoolean("is_active"));

        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        customer.setDescription(rs.getString("description"));

        return customer;
    }
}

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

/**
 *
 * @author PPuarat
 */
public class CustomerDAO {

    private final static String TABLE = "customers";

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
            customer = mapSingle(rs);

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

        if (rs.next()) {

            customer.setId(rs.getLong("id"));
            customer.setCreatedAt(rs.getString("created_at"));
            customer.setIsActive(rs.getBoolean("is_active"));

            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
            customer.setDescription(rs.getString("description"));

        }

        return customer;
    }
}

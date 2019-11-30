/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Customer;
import Models.Order;
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

    public long create() {
        long latestId = 0;
        int index = 1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO " + TABLE + " (customer_id, user_id, is_takeAway, "
                    + "created_at, is_active)"
                    + "VALUES(?, ?, ?, ?, ?);";

            stmt = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            //set customer. 6 means INTEGER
            stmt.setNull(index++, 6);
            //set user
            stmt.setLong(index++, 1);
            //set is_takeAway
            stmt.setBoolean(index++, false);
            //set date
            stmt.setTimestamp(index++, Utils.getSqlTimestamp());
            //set is_active
            stmt.setBoolean(index++, true);

            //Check query   
            //System.out.println(stmt);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {

                latestId = rs.getLong(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return latestId;
    }

    public long updateCustomer(Order order, Customer customer) {
        long latestId = 0;
        int index = 1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "UPDATE " + TABLE + " "
                    + "SET customer_id=? "
                    + "WHERE id=?;";

            stmt = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            //set customer. 6 means INTEGER
            stmt.setLong(index++, customer.getId());
            //where orderID =
            stmt.setLong(index++, order.getId());

            //Check query   
            //System.out.println(stmt);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {

                latestId = rs.getLong(1);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return latestId;
    }

    public ArrayList<Order> searchAll() {
        ArrayList<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, customer_id, user_id, is_takeaway, created_at, is_active "
                    + "FROM " + TABLE + " "
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

    public ArrayList<Order> searchUnpaid() {
        ArrayList<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();
            //TODO Find unpaid orders
            String SQL = "select o.* "
                    + "from orders o "
                    + "left join payments p on o.id = p.order_id "
                    + "where p.order_id is null "
                    + "and o.is_active = ?;";

            stmt = conn.prepareStatement(SQL);
            int index = 1;
            //set is_active
            stmt.setBoolean(index++, true);

            //Check query
//            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            orders = map(rs);

        } catch (SQLException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return orders;
    }

    public Order searchById(Long id) {
        Order order = new Order();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, customer_id, user_id, is_takeaway, created_at, is_active "
                    + "FROM " + TABLE + " "
                    + "WHERE is_active=? and id = ?;";

            stmt = conn.prepareStatement(SQL);

            //set is_active
            stmt.setBoolean(1, true);

            stmt.setLong(2, id);

            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                order = mapSingle(rs);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return order;
    }

    private ArrayList<Order> map(ResultSet rs) {

        ArrayList<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {

                orders.add(mapSingle(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private Order mapSingle(ResultSet rs) {
        Order order = new Order();
        UserDAO userDAO = new UserDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        try {

            order.setId(rs.getLong("id"));
            order.setCreatedAt(rs.getString("created_at"));
            order.setIsTakeAway(rs.getBoolean("is_takeaway"));
            order.setIsActive(rs.getBoolean("is_active"));
            order.setCustomer(customerDAO.searchById(rs.getLong("customer_id")));
            order.setUser(userDAO.searchById(rs.getLong("user_id")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}

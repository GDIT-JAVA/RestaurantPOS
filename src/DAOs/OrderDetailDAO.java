/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Food;
import Models.Order;
import Models.OrderDetail;
import Utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author PPuarat
 */
public class OrderDetailDAO {

    private final static String TABLE = "order_detail";

    public boolean create(Order order, Food food) {
        boolean isCreated = false;
        int index = 1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO " + TABLE + " (order_id, food_id, "
                    + "created_at, description, is_active) "
                    + "VALUES(?, ?, ?, '', ?);";

            stmt = conn.prepareStatement(SQL);
            //set date
            stmt.setLong(index++, order.getId());//order_id
            stmt.setLong(index++, food.getId());//food_id
            stmt.setTimestamp(index++, Utils.getSqlTimestamp());
            stmt.setBoolean(index++, true);

            //Check query
            //System.out.println(stmt);
            //ResultSet rs = stmt.executeQuery();
            if (stmt.executeUpdate() > 0) {
                isCreated = true;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return isCreated;
    }

    public ArrayList<OrderDetail> searchAll() {
        ArrayList<OrderDetail> orderDetail = new ArrayList<>();
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
            orderDetail = map(rs);

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return orderDetail;
    }

    private ArrayList<OrderDetail> map(ResultSet rs) throws SQLException {

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        while (rs.next()) {
            orderDetails.add(mapSingle(rs));
        }

        return orderDetails;
    }

    private OrderDetail mapSingle(ResultSet rs) throws SQLException {

        OrderDetail user = new OrderDetail();
        OrderDAO orderDao = new OrderDAO();
        FoodTypeDAO foodTypeDao = new FoodTypeDAO();
        FoodDAO foodDao = new FoodDAO(foodTypeDao.searchAll());

        user.setId(rs.getLong("id"));
        user.setCreatedAt(rs.getString("created_at"));
        user.setIsActive(rs.getBoolean("is_active"));
        user.setOrder(orderDao.searchById(rs.getLong("order_id")));
        user.setFood(foodDao.searchById(rs.getLong("food_id")));
        user.setDescription(rs.getString("first_name"));

        return user;
    }

}

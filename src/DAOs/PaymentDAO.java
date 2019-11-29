/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Order;
import Models.Payment;
import Utils.Utils;
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
    private OrderDAO orderDAO = new OrderDAO();

    public PaymentDAO(ArrayList<Order> orders) {
        this.orders = orders;
        System.out.println("DAOs.PaymentDAO.<init>()");
    }

    public ArrayList<Payment> searchAll() {
        Connection conn = null;
        PreparedStatement stmt = null;

        ArrayList<Payment> payments = new ArrayList<>();
        try {
            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            payments = this.map(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {

            PostgreSQLConnection.close(conn, stmt);
        }

        return payments;

    }

    public long create() {
        long latestId = 0;
        int index = 1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO " + TABLE + " (order_id, created_at, "
                    + "total, description, is_active) "
                    + "VALUES(?, ?, ?, ?, ?);";

            stmt = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            //set orderId
            stmt.setLong(index++, 6);
            //set date
            stmt.setTimestamp(index++, Utils.getSqlTimestamp());
            //set total
            stmt.setDouble(index++, 0);
            //set description
            stmt.setString(index++, "");
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
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return latestId;
    }

    private ArrayList<Payment> map(ResultSet rs) throws SQLException {

        ArrayList<Payment> payments = new ArrayList<>();

        while (rs.next()) {

            Payment payment = new Payment();

            payment.setID(rs.getLong("id"));
            //payment.setOrder(orderDAO.searchById(rs.getLong("order_id")));
            payment.setCreatedAt(rs.getString("created_at"));
            payment.setTotalPaid(rs.getDouble("total"));
            payment.setDescription(rs.getString("description"));
            payment.setIsActive(rs.getBoolean("is_active"));

            payments.add(payment);
        }

        return payments;
    }

}

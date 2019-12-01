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
    private OrderDAO orderDAO = new OrderDAO();

    public ArrayList<Payment> searchAll() {

        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            payments = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return payments;

    }

    public ArrayList<Payment> searchByTimeInterval(String timeInterval) {

        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Connection conn = PostgreSQLConnection.connect();
            String SQL = "SELECT * "
                    + "FROM " + TABLE + " WHERE is_active = true "
                    + "AND created_at > now() - ?::INTERVAL;";
            
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, "\'" + timeInterval + "\'");
            ResultSet rs = stmt.executeQuery();

            payments = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return payments;

    }

    public boolean create(Order order, Double total) {
        boolean isSuccess = false;
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
            stmt.setLong(index++, order.getId());
            //set date
            stmt.setTimestamp(index++, Utils.getSqlTimestamp());
            //set total
            stmt.setDouble(index++, total);
            //set description
            stmt.setString(index++, "");
            //set is_active
            stmt.setBoolean(index++, true);

            //Check query   
            //System.out.println(stmt);
            int i = stmt.executeUpdate();
            
            if (i > 0) {

                System.out.println("Success");
                isSuccess = true;
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return isSuccess;
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

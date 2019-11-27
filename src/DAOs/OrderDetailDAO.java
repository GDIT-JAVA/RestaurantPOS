/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Utils.Settings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PPuarat
 */
public class OrderDetailDAO {

    private final static String TABLE = "order_detail";

    public boolean create() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(Settings.DATE_FORMAT);
        boolean isCreated = false;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO " + TABLE + " (order_id, food_id, "
                    + "created_at, description, is_active) "
                    + "VALUES(0, 0, ?, '', true);";

            stmt = conn.prepareStatement(SQL);
            //set date
            stmt.setString(1, dateFormat.format(date));
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
}

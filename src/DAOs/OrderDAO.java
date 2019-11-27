/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Utils.Settings;
import Utils.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
}

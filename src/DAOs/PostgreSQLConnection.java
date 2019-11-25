/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import Utils.Settings;
import java.sql.PreparedStatement;

/**
 *
 * @author PPuarat
 */
public class PostgreSQLConnection {

    public static void main(String[] args) {


        PostgreSQLConnection.connect();

    }

    public static Connection connect() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(Settings.DB_URL + Settings.DB_NAME,
                    Settings.DB_USER,
                    Settings.DB_PASSWORD);

            //System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return conn;
    }

    public static void close(Connection conn, PreparedStatement stmt) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}

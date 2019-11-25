/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class UserDAO {

    private final static String TABLE = "users";

    public ArrayList<User> searchAll() {

        ArrayList<User> users = new ArrayList<>();
        try {

            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            users = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    /**
     * @param username
     * @param password
     */
    public ArrayList<User> search(String username, String password) {

        ArrayList<User> users = new ArrayList<>();
        try {

            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true "
                    + "and user_name = ? and password = ? ;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, username);
            stmt.setString(2, password);
            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();

            users = this.map(rs);
            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    private ArrayList<User> map(ResultSet rs) throws SQLException {

        ArrayList<User> users = new ArrayList<>();

        while (rs.next()) {

            User user = new User();
            
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
            user.setIsActive(rs.getBoolean("is_active"));

            users.add(user);
        }

        return users;
    }
}

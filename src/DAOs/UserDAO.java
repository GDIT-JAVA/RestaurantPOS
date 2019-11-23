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
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class UserDAO {

    private final static String TABLE = "users";

    public ArrayList<User> searchAll() throws SQLException {

        ArrayList<User> users;
        Connection conn = PostgreSQLConnection.connect();

        String SQL = "SELECT * FROM " + TABLE + "WHERE is_active = true;";

        PreparedStatement stmt = conn.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();

        users = this.mapUsers(rs);

        PostgreSQLConnection.close(conn,stmt);

        return users;
    }

    public ArrayList<User> search(String username, String password) throws SQLException {

        ArrayList<User> users;
        Connection conn = PostgreSQLConnection.connect();

        String SQL = "SELECT * FROM " + TABLE + "WHERE is_active = true "
                + "and user_name = ? and password = ? ;";

        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        users = this.mapUsers(rs);

        PostgreSQLConnection.close(conn, stmt);

        return users;
    }

    private ArrayList<User> mapUsers(ResultSet rs) throws SQLException {

        ArrayList<User> users = new ArrayList<>();

        while (rs.next()) {

            User user = new User();

            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));

            users.add(user);
        }
        return users;
    }
}

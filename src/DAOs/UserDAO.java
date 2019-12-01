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
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PPuarat
 */
public class UserDAO {

    private final static String TABLE = "users";

    public ArrayList<User> searchAll() {

        ArrayList<User> users = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            users = this.map(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {

            PostgreSQLConnection.close(conn, stmt);

        }
        return users;
    }

    /**
     * @param username
     * @param password
     */
    public ArrayList<User> search(String username, String password) {

        ArrayList<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true "
                    + "and user_name = ? and password = ? ;";

            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, username);
            stmt.setString(2, password);
            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();

            users = this.map(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);

        }
        return users;
    }

    public User searchById(long id) {
        User user = new User();
        Connection conn = null;
        PreparedStatement stmt = null;
        int index = 1;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT id, user_name, password, first_name, last_name, phone, email, "
                    + " created_at, is_active "
                    + "FROM " + TABLE + " "
                    + "WHERE is_active=? and id=?;";

            stmt = conn.prepareStatement(SQL);

            //set is_active
            stmt.setBoolean(index++, true);
            stmt.setLong(index++, id);

            //Check query
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = mapSingle(rs);

            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
        return user;
    }

    
    public void addUser(String user, String fName, String lName, String phone, String email, String password){
        
        LocalDate localDate = LocalDate.now();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO public.users (user_name,first_name,last_name,phone,email,password,created_at,is_active) VALUES"
                    + " (?,?,?,?,?,?,?,?); ";

            stmt = conn.prepareStatement(SQL);
            
            stmt.setString(1, user);
            stmt.setString(2, fName);
            stmt.setString(3, lName);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.setObject(7, localDate);
            stmt.setBoolean(8, true);
            
            stmt.executeUpdate();
            
        }
        catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, 
                    e.toString());
        } finally {
            PostgreSQLConnection.close(conn, stmt);
        }
    }

    private ArrayList<User> map(ResultSet rs) throws SQLException {

        ArrayList<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(mapSingle(rs));
        }

        return users;
    }

    private User mapSingle(ResultSet rs) throws SQLException {

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

        return user;
    }
}

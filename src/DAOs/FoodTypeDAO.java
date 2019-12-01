/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.FoodType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PPuarat
 */
public class FoodTypeDAO {

    private final static String TABLE = "food_types";

    public ArrayList<FoodType> searchAll() {
        
        ArrayList<FoodType> foodTypes = new ArrayList<>();
        try {

            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            foodTypes = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        return foodTypes;

    }
    
     public void addCategory(String category, String description){
        
        LocalDate localDate = LocalDate.now();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO public.food_types (type_name,description,created_at,is_active) VALUES"
                    + " (?,?,?,?); ";

            stmt = conn.prepareStatement(SQL);
            
            stmt.setString(1, category);
            stmt.setString(2, description);
            stmt.setObject(3,localDate);
            stmt.setBoolean(4, true);
    
            
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
    
        private ArrayList<FoodType> map(ResultSet rs) throws SQLException {

        ArrayList<FoodType> foodTypes = new ArrayList<>();

        while (rs.next()) {

            FoodType foodType = new FoodType();

            foodType.setId(rs.getLong("id"));
            foodType.setTypeName(rs.getString("type_name"));
            foodType.setDescription(rs.getString("description"));
            foodType.setCreatedAt(rs.getString("created_at"));
            foodType.setIsActive(rs.getBoolean("is_active"));

            foodTypes.add(foodType);
        }

        return foodTypes;
    }
}

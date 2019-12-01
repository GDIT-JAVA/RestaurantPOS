/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Food;
import Models.Food;
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
public class FoodDAO {

    private final static String TABLE = "foods";
    private ArrayList<FoodType> foodTypes;

    public FoodDAO(ArrayList<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
    }

    public ArrayList<Food> searchAll() {

        ArrayList<Food> foods = new ArrayList<>();
        try {

            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            foods = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return foods;

    }

    public ArrayList<Food> searchByFoodType(Long foodTypeId) {

        ArrayList<Food> foods = new ArrayList<>();
        try {
            Connection conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true "
                    + " and type_id = ?;";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, foodTypeId);
            ResultSet rs = stmt.executeQuery();

            foods = this.map(rs);

            PostgreSQLConnection.close(conn, stmt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return foods;
    }
    
      public void addMenu(String foodName, String description, String price, String category){
        
        LocalDate localDate = LocalDate.now();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = PostgreSQLConnection.connect();

            String SQL = "INSERT INTO public.foods (food_name,description,created_at,price,is_active,type_id) VALUES"
                    + " (?,?,?,?,?,?); ";

            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, foodName);
            stmt.setString(2, description);
            stmt.setObject(3, localDate);
            stmt.setDouble(4, Double.parseDouble(price));
            stmt.setBoolean(5, true);
            stmt.setString(6, category);
    
            
            stmt.executeUpdate();
            
        }
        catch (SQLException e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, 
                    e.toString());
        } 
        finally {
            PostgreSQLConnection.close(conn, stmt);
        }
    }

    public Food searchById(Long foodId) {

        Food food = new Food();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = PostgreSQLConnection.connect();

            String SQL = "SELECT * FROM " + TABLE + " WHERE is_active = true "
                    + " and id = ?;";

            stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, foodId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                food = mapSingle(rs);

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            PostgreSQLConnection.close(conn, stmt);

        }
        return food;
    }

    private ArrayList<Food> map(ResultSet rs) throws SQLException {

        ArrayList<Food> foods = new ArrayList<>();

        while (rs.next()) {

            foods.add(mapSingle(rs));
        }

        return foods;
    }

    private Food mapSingle(ResultSet rs) throws SQLException {

        Food food = new Food();

        food.setId(rs.getLong("id"));
        food.setFoodName(rs.getString("food_name"));
        food.setDescription(rs.getString("description"));
        food.setPrice(rs.getDouble("price"));
        food.setCreatedAt(rs.getString("created_at"));
        food.setIsActive(rs.getBoolean("is_active"));
        //TODO SET FOODTYPE
        food.setFoodType(mapFoodType(rs.getLong("type_id")));

        return food;
    }

    public FoodType mapFoodType(long foodTypeId) {

        for (FoodType foodType : this.foodTypes) {
            if (foodType.getId() == foodTypeId) {
                return foodType;
            }
        }

        return null;
    }
    
    public FoodType mapFoodTypeId(String foodTypeStr) {

        for (FoodType foodType : this.foodTypes) {
            if (foodType.getTypeName() == foodTypeStr) {
                return foodType;
            }
        }

        return null;
    }
    
}

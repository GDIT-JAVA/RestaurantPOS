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
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class FoodDAO {

    private final static String TABLE = "foods";
    private ArrayList<FoodType> foodTypes;

    public FoodDAO(ArrayList<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
        System.out.println("DAOs.FoodDAO.<init>()");
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

        }
        return foods;
    }

    private ArrayList<Food> map(ResultSet rs) throws SQLException {

        ArrayList<Food> foods = new ArrayList<>();

        while (rs.next()) {

            Food food = new Food();

            food.setId(rs.getLong("id"));
            food.setFoodName(rs.getString("food_name"));
            food.setDescription(rs.getString("description"));
            food.setPrice(rs.getDouble("price"));
            food.setCreatedAt(rs.getString("created_at"));
            food.setIsActive(rs.getBoolean("is_active"));
            //TODO SET FOODTYPE
            food.setFoodType(mapFoodType(rs.getLong("type_id")));
            foods.add(food);
        }

        return foods;
    }

    private FoodType mapFoodType(long foodTypeId) {

        for (FoodType foodType : this.foodTypes) {
            if (foodType.getId() == foodTypeId) {
                return foodType;
            }
        }

        return null;
    }
}

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
import java.util.ArrayList;

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
        }
        
        return foodTypes;

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

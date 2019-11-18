/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author PPuarat
 */
public class PostgreSQLConnection {

    private static final String url = "jdbc:postgresql://localhost:5432/JavaRestaurantPOS";
    private static final String user = "javaApplication";
    private static final String password = "javajava";

    public static void main(String[] args) {
        try{
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the PostgreSQL server successfully.");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
}

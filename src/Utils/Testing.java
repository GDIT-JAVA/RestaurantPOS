/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAOs.PostgreSQLConnection;
import DAOs.UserDAO;
import java.sql.SQLException;

/**
 *
 * @author PPuarat
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        // TODO code application logic here
        UserDAO userDAO = new UserDAO();
        
        PostgreSQLConnection conn = new PostgreSQLConnection();
        
        //System.out.println(userDAO.selectAllUsers(conn.connect()).get(0).toString());
        
    }
    
}

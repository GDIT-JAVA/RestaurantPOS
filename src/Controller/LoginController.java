/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.UserDAO;
import Models.User;
import java.util.ArrayList;

/**
 *
 * @author PPuarat
 */
public class LoginController {

    public boolean login(String username, char[] password) {

        //System.out.println(username +" "+ password);
        UserDAO userDAO = new UserDAO();

        ArrayList<User> users;
        return true; // bypass login
//        users = userDAO.search(username, String.valueOf(password));
//        if (!users.isEmpty()) {
//            return true;
//        }
//        return false;
    }
}

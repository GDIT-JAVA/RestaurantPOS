package Utils;

import Models.Food;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PPuarat
 */
public final class Utils {

    public static double calculateTotalPrice(ArrayList<Food> foods) {
        double total = 0;

        for (int i = 0; i < foods.size(); i++) {
            total += foods.get(i).getPrice();
        }

        return total;
    }

    public static String getDate() {
        
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(Settings.DATE_FORMAT);
        
        return dateFormat.format(date);
    }
}

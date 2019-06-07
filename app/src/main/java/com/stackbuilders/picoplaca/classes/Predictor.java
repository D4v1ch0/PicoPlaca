package com.stackbuilders.picoplaca.classes;

import java.sql.Time;
import java.util.Date;

/**
 * Created by dmaci on 5/6/2019.
 *
 * The vehicle can move if it meets the following
 * Outside this range of time 07h00 to 09h30 and 16h00 to 19h30
 * Depend of the final number of the license plate number and the date. Whether the final number is 2 and the date is Monday. The
 * vehicule won't move. Monday (1-2), Tuesday (3-4), Wednesday (5-6), Thursday(7-8) and Friday (9-0)
 */

public class Predictor {
    private Configuration config;

    public Predictor(){
        this.config = new Configuration();
    };

    public boolean canGo(int day, String time, String plateNumber){
        boolean response = false;
        try {
            int last = Integer.parseInt(plateNumber.trim().substring(plateNumber.length()-1));
            response = (this.config.isOk(day, time, last));
        } catch(Exception e) {
            System.out.println("Could not evaluate if you can go" + e);
        }
        return  response;
    }
}

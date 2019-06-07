package com.stackbuilders.picoplaca.classes;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by dmaci on 5/6/2019.
 */

public class Configuration {
    private List<Horario> horarios = new ArrayList<>();
    private List<NumeroDia> dias= new ArrayList<>();

    public Configuration(){
        this.horarios.add(new Horario("07:00","09:30"));
        this.horarios.add(new Horario("16:00","19:30"));

        this.dias.add(new NumeroDia(Calendar.MONDAY,1,2));
        this.dias.add(new NumeroDia(Calendar.TUESDAY,3,4));
        this.dias.add(new NumeroDia(Calendar.WEDNESDAY,5,6));
        this.dias.add(new NumeroDia(Calendar.THURSDAY,7,8));
        this.dias.add(new NumeroDia(Calendar.FRIDAY,9,0));
    }

    public boolean isOk(int day, String time, int lastNumber) {
        boolean response = true;

        for (Horario horario:horarios) {
            if (horario.atTime(time))
            {
                for (NumeroDia dia:dias) {
                    if(dia.contains(day,lastNumber)){
                        response = true;
                        break;
                    }
                    else
                    {
                        response = false;
                    }
                }
                break;
            }
        }

        return  response;
    }
    
    public class NumeroDia {
        private int day=0;
        private int[] lastNumber;

        public NumeroDia(int day, int... lastNumber){
            this.day = day;
            this.lastNumber = lastNumber;
        }

        public boolean contains(int day, int number)
        {
            boolean response = false;
            try {
                if (this.day == day)
                {
                    for (int ln: lastNumber) {
                        response = (ln == number);
                        break;
                    }
                }
            } catch(Exception e) {
                System.out.println("Could not evaluate day and last number " + e);
            }
            return  response;
        }
    }

    public class Horario {
        private int initialTime=0;
        private int finalTime=0;

        public Horario(String initialTime, String finalTime){
            try {
                this.initialTime = Integer.parseInt(initialTime.replace(":",""));
                this.finalTime = Integer.parseInt(finalTime.replace(":",""));
            } catch(Exception e) {
                System.out.println("Could not set horario " + e);
            }
        }

        public boolean atTime(String time)
        {
            boolean response=false;
            try {
                int t = Integer.parseInt(time.replace(":",""));
                response = (t>=this.initialTime && t<=this.finalTime);
            } catch(Exception e) {
                System.out.println("Could not evaluate time " + e);
            }
            return response;
        }
    }
}
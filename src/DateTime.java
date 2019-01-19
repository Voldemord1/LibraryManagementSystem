/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hours = 0;
    private int minutes = 0;

    public DateTime(int day, int month, int year){
        if ((year > 1939) && (year<2099) && (month > 0) && (month < 13) && (day > 0) && (day<32)){
            this.day = day;
            this.month = month;
            this.year = year;
        }
        else{
            System.out.println("Not correct date");
        }
    }

    public DateTime(int day, int month, int year, int hours, int minutes ){
        if ((year > 1939) && (year<2099) && (month > 0) && (month < 13)
                && (day > 0) && (day<32) && (hours < 24) && (hours > -1) && (minutes > -1) && (minutes < 60)){
            this.day = day;
            this.month = month;
            this.year = year;
            this.hours = hours;
            this.minutes = minutes;
        }  
        else{
            System.out.println("Not correct date");
        }
    }
    public void setYear(int year){
        if ((year > 1939) && (year<2099)){
        this.year = year;
        }
        else{
            System.out.println("Not correct range");
        }   
    }
    
    public void setMonth(int month){
        if ((month > 0) && (month<13)){
            this.month = month;
        }else{
            System.out.println("Not correct range");
        }
    }
    
    public void setDay(int day){
        if ((day > 0) && (day<32)){
            this.day = day;
        }else{
            System.out.println("Not correct range");
        }
    }

    public void setHours(int hours) {
        if(hours > -1 && hours < 24) {
            this.hours = hours;
        }else{
            System.out.println("Not correct range");
        }
    }

    public void setMinutes(int minutes) {
        if(minutes > -1 && minutes < 60) {
            this.minutes = minutes;
        }else{
            System.out.println("Not correct range");
        }
    }

    public int getYear(){
        return year;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getDay(){
        return day;
    }
    public String getDate(){
        String dateStr = String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hours, minutes);
        return dateStr;
    }
    
    public String toString(){
        String dateStr = String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hours, minutes);
        return dateStr;
    }    
}

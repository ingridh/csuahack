package com.lelandcs.platformer;
 
 
public class Date implements Comparable {
    private String day; //1-31
    private String hour;
    private String month; // day = month * 24 * 31
    public Date (String hour, String day, String month) {
        this.day = day;
        this.month = month;
        this.hour = hour;
    }
     
    public String getDay() {
        return day;
    }
    public String getHour() {
        return hour;
    }
    public String getMonth() {
        return month;
    }
     
    public int compareTo(Object object) {
        Date date = (Date)object;
        int hours1 = Integer.parseInt(hour) + 24 * Integer.parseInt(day) + 24 * 31 * Integer.parseInt(month); 
        int hours2 = Integer.parseInt(date.hour) + 24 * Integer.parseInt(date.day) + 24 * 31 * Integer.parseInt(date.month); 
        if (hours1 < hours2) {
            return -1;
        }
        if (hours1 > hours2) {
            return 1;
        }
        else {
            return 0;
        }
    } 
}


package file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Calculate {
    private final Float money_per_hour_normal;

    private Float times_of_non_OT;
    private Float times_of_OT;
    public Calculate(Float money_per_hour){
        this.money_per_hour_normal = money_per_hour;
    }

    public Float getCost(String start_date, String start_time, String end_time){
        float money = 0f;
        String text_date = getDate(start_date);

        if(text_date.equals("Sat") || text_date.equals("Sun")){
            times_of_non_OT = 1.5f;
            times_of_OT = 2f;
        }else{
            times_of_non_OT = 1f;
            times_of_OT = 1.5f;
        }
        String get_two_time = getHour(start_time, end_time);
        //System.out.println(get_two_time);
        int i = 0;
        String hour_normal = "", hour_special = "";
        String[] split_time = get_two_time.split(":");
        for(String string : split_time){
            if(i == 0){ hour_normal = string; }
            if(i == 1){ hour_special = string; }
            i++;
        }
        //8.00 - 17.00 = 9 hour
        money = (Float.parseFloat(hour_normal) * money_per_hour_normal * times_of_non_OT) + (Float.parseFloat(hour_special) * money_per_hour_normal * times_of_OT);
        //money =(Float.parseFloat(hour_normal) * money_per_hour_normal * times_of_non_OT);
        //money = (Float.parseFloat(hour_special) * money_per_hour_normal * times_of_OT);
        //System.out.print (hour_normal/* +"  "+ hour_special*/);
        //System.out.println(text_date + " " + hour_special + " " + money);
        return money;
    }
    public String getDate(String start_date){ // Mon Tue Wed Thu Fri Sat Sun
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String text_date = null;
        try {
            Date date = simpleDateFormat.parse(start_date);
            text_date = date.toString();
            text_date = text_date.substring(0,3);
            //System.out.println(text_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return text_date;
    }

    public String getHour(String start_time, String end_time){
        float hour_normal = 0f, hour_special = 0f, late_minute = 0f;
        int i = 0;
        String hour_start = "", minute_start = "";
        String[] split_time = start_time.split(":");
        for(String string : split_time){
            if(i == 0){ hour_start = string; }
            if(i == 1){ minute_start = string; }
            i++;
        }
        i = 0;
        String hour_end = "", minute_end = "";
        split_time = end_time.split(":");
        for(String string : split_time){
            if(i == 0){ hour_end = string; }
            if(i == 1){ minute_end = string; }
            i++;
        }
        // check time late to minute
        if((Float.parseFloat(hour_start) * 60 + Float.parseFloat(minute_start)) > (8f * 60 + 5f)){

            late_minute = (Float.parseFloat(hour_start) * 60 + Float.parseFloat(minute_start)) - (8f * 60 + 5f);
            //System.out.print(late_minute + "  ");
        }
        //check time for do OT
        if((Float.parseFloat(hour_end) * 60 + Float.parseFloat(minute_end)) > (17f * 60 + 30f) ){
            //calculate to minute
            hour_normal = (17f * 60) - (8f * 60);
            hour_special = (Float.parseFloat(hour_end) * 60 + Float.parseFloat(minute_end)) - (17f * 60 + 30f);
            //System.out.println( hour_special);
        }else{
            hour_normal = (17f * 60) - (8f * 60);
            //System.out.println(hour_normal);
        }
        hour_normal = hour_normal - late_minute;
        //System.out.println(hour_normal);
        hour_normal = minuteTohour(hour_normal);
        //change to hour

        hour_special = minuteTohour(hour_special);
        //System.out.println(late_minute + " " +hour_normal + " " + hour_special);
        return String.valueOf(hour_normal) + ":" + String.valueOf(hour_special);
    }
    public Float minuteTohour(float minute){
        float hour = 0f;
        int full_hour = 0;

        full_hour = (int)minute/60;
        hour =  minute - full_hour * 60;
        hour = (float)full_hour  + hour/100;
        //System.out.println(hour);

        return hour;
    }

}

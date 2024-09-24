package com.shiyiwan.taskcard.util;

/**
 * @Classname Utils
 * @Description TODO
 * @Date 9/23/24 11:51 AM
 * @Created by shiyiwan
 */
public class Utils {

    public static String secondsToTime(Integer total){
        if(total < 0){
            // todo customer exception handler
            throw new RuntimeException("remain time must be positive");
        }
        int seconds = total % 60;
        int minutes =  total / 60 % 60;
        int hours = total /60 /60;

        return "" + convertToTwoDigits(hours) + ":" + convertToTwoDigits(minutes) + ":" + convertToTwoDigits(seconds);
    }

    private static String convertToTwoDigits(int num){
        if(num < 10){
            return "0" + num;
        }
        return String.valueOf(num);
    }

}

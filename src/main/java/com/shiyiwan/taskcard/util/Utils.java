package com.shiyiwan.taskcard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname Utils
 * @Description TODO
 * @Date 9/23/24 11:51 AM
 * @Created by shiyiwan
 */
public class Utils {

    public static String secondsToTime(Integer total) {
        if (total < 0) {
            // todo customer exception handler
            throw new RuntimeException("remain time must be positive");
        }
        int seconds = total % 60;
        int minutes = total / 60 % 60;
        int hours = total / 60 / 60;

        return "" + convertToTwoDigits(hours) + ":" + convertToTwoDigits(minutes) + ":" + convertToTwoDigits(seconds);
    }

    /**
     * parse 1h23m45s to seconds
     *
     * @param input remain time desc
     * @return
     */
    public static Integer parserRemainTime(String input) {
        // valid
        input = input.toLowerCase();
        String regex = "(\\d+h)?(\\d+m)?(\\d+s)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.matches();
        if (!matches) {
            throw new RuntimeException("format error");
        }

        // parse
        int hIndex = input.indexOf("h");
        int mIndex = input.indexOf("m");
        int sIndex = input.indexOf("s");
        int hours = 0;

        if (hIndex != -1) {
            hours = Integer.parseInt(input.substring(0, hIndex));
        }

        int minutes = 0;
        if (mIndex != -1) {
            minutes = Integer.parseInt(input.substring(hIndex + 1, mIndex));
        }

        int seconds = 0;
        if (sIndex != -1) {
            int sStart = Math.max(mIndex, hIndex);
            seconds = Integer.parseInt(input.substring(sStart + 1, sIndex));
        }

        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    private static String convertToTwoDigits(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return String.valueOf(num);
    }

}

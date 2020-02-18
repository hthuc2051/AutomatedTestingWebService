package com.fpt.automatedtesting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomUtils {

    public static String getCurDateTime(Date date, String typeFormat) {
        String s = "";

        SimpleDateFormat formatter = null;
        switch (typeFormat) {
            case "Prefix":
                formatter = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
                s = formatter.format(date);
                break;
            default:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                s = formatter.format(date);
                break;
        }
        return s;
    }

}

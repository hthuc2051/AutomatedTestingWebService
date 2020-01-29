package com.fpt.automatedtesting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomUtils {

    public static String getCurDateTime(String typeFormat) {
        String s = "";
        Date date = new Date();
        SimpleDateFormat formatter = null;
        switch (typeFormat) {
            case "Prefix":
                formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
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

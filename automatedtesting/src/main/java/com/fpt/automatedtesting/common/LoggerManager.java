package com.fpt.automatedtesting.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.util.Calendar;

public class LoggerManager {
    public static void writeLogFile(String logFilePath, String text){
        try {
            String newText = Calendar.getInstance() + " : " + text;
            FileWriter writer = new FileWriter(logFilePath, true);
            writer.write(text);
            writer.write("\r\n");   // write new line
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeToFile error :" + ex.getMessage());
        }
    }
}

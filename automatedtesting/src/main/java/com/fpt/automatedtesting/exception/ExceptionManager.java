package com.fpt.automatedtesting.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;

import static com.fpt.automatedtesting.common.PathConstants.PATH_EXCEPTIONS;

public class ExceptionManager {
    public static void writeException() {
        FileWriter writer = null;
        try {
            String curDate = "";
            File file = new File(PATH_EXCEPTIONS + File.separator + "SE"+"a.txt");
            if(file.mkdir() == true){
                writer = new FileWriter(file);
                // convert student point object to JSON
                if (writer != null) {
                    writer.write("SKT");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.util;

import com.practicalexam.student.data.Shoes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Utils {

    private static final String userFolPath = System.getProperty("user.dir");
    private static final String dataFileName = "data.txt";
    private static final String dataFilePath = "src" + File.separator + "file" + File.separator + dataFileName;
    private static final String filePath = userFolPath + File.separator + dataFilePath;
    private static File fileData = new File(filePath);
    private static String stopSign = ":";

    public static Shoes readFile(String functionName) {
        Shoes shoes = new Shoes();
        try {
            Scanner sc = new Scanner(fileData);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains(functionName + stopSign)) {
                    line = line.replace(functionName + stopSign, "");
                    String[] value = line.split(";");
                    for (String string : value) {
                        String[] temp = string.split("=");
                        if (temp.length == 2) {
                            String field = temp[0];
                            String data = temp[1];
                            switch (field) {
                                case "code":
                                    shoes.setCode(data);
                                    break;
                                case "model":
                                    shoes.setModel(data);
                                    break;
                                case "size":
                                    shoes.setSize(Integer.parseInt(data));
                                    break;
                                case "price":
                                    shoes.setPrice(Double.parseDouble(data));
                                    break;
                            }
                        }
                    }
                }
            }
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
          return shoes;
    }
}

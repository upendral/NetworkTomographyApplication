/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author uppi
 */
public class Logger {

    public static void log(String message) {
        Date time = new Date();
        PrintWriter output = null;
        FileWriter fw = null;
        try {
            File sampleFile = new File("Log.txt");
            if (sampleFile.exists()) {
                fw = new FileWriter(sampleFile, true);
            } else {
                sampleFile.createNewFile();
                fw = new FileWriter(sampleFile);
            }
            output = new PrintWriter(fw);
            output.println(message + " " + time.toString());
        } 
        catch (Exception e) {
            System.out.println("log");
        }
        finally {
            output.close();
        }

    }
}

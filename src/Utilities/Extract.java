/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author uppi
 */
public class Extract implements Runnable {
    private String inputIp = null;
    private int sampleCount = 0;

    public void setIpAddress(String ipAddress, int countNumber) {
        inputIp = ipAddress;
        sampleCount = countNumber;
    }

    public void run() {
        PrintWriter output = null;
        Scanner _scanner = null;
        File sampleFile = null;
        FileWriter fileWriter = null;
        String message = null;
        
        try {
            _scanner = new Scanner(new File(inputIp + " PingStats " + sampleCount + ".txt"));
            if (_scanner.hasNextLine()) {
                sampleFile = new File(inputIp + " AverageTimes.txt");
                if (sampleFile.exists()) {
                    fileWriter = new FileWriter(sampleFile, true);
                } else {
                    sampleFile.createNewFile();
                    fileWriter = new FileWriter(sampleFile);
                }                
                output = new PrintWriter(fileWriter);                
                while (_scanner.hasNextLine()) {
                    message = _scanner.nextLine();
                    if (message != null && message.contains("Average")) {
                        message = processMessage(message);
                        if (message != null) {
                            output.println(message + sampleCount);
                        }
                    }
                }
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace(output);
        } catch (Exception e) {
            Logger.log(e.getStackTrace().toString());
        } finally {
            output.close();
        }
    }

    private static String processMessage(String message)  {
        if (message.contains("Average"))  {
            return message.substring(message.indexOf("Average") + 10, message.length()-2);
        }
        return null;
    }
}

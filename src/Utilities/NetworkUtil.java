/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author uppi
 */
public class NetworkUtil implements Runnable {
    private String ipAddress;
    private String noOfPackets;
    private int sampleCount;

    public void setIpAddress(String inputIP, String numberOfPackets, int countNumber) {
        ipAddress = inputIP;
        noOfPackets = numberOfPackets;
        sampleCount = countNumber;
    }

    @Override
    public void run() {
        Logger.log("Pinging " + ipAddress );
        PrintWriter output = null;
        Scanner pingMessage = null;
        ProcessBuilder pingBuilder = null;
        ArrayList<String> command = null;
        Process ping = null;  
        File pingFile = null;
        FileWriter pingWriter = null;
        try {
            command = new ArrayList<String>();
            command.add("ping");
            command.add("-n");
            command.add(noOfPackets);
            command.add(ipAddress);
            pingBuilder = new ProcessBuilder(command);
            ping = pingBuilder.start();
            pingFile = new File(ipAddress + " PingStats "+ sampleCount +".txt");            
            if (pingFile.exists()) {
                pingWriter = new FileWriter(pingFile, true);
            } else {
                pingFile.createNewFile();
                pingWriter = new FileWriter(pingFile);
            }
            output = new PrintWriter(pingWriter);
            pingMessage = new Scanner(ping.getInputStream());            
            if (pingMessage.hasNextLine()) {
                while (pingMessage.hasNextLine()) {
                    output.println(pingMessage.nextLine());
                }
            } else {
                pingMessage = new Scanner(ping.getErrorStream());
                while (pingMessage.hasNextLine()) {
                    output.println(pingMessage.nextLine());
                }
            }
        } catch (IOException e) {
            Logger.log(e.getMessage());
        } catch (Exception e) {
            Logger.log(e.getMessage());
        } finally {
            dispose(output, pingMessage);
        }
    }

    private void dispose(PrintWriter writer, Scanner message) {
        if(writer != null) {
            writer.close();
        }
        if(message != null) {
            message.close();
        }
    }
}

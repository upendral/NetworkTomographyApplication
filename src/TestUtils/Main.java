/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUtils;

import Utilities.*;
import java.util.Scanner;

/**
 *
 * @author uppi
 */
public class Main {

    private final static String numberOfPackets = "10";
    private final static int numberOfSamples = 2;

    public static void main(String[] args) {
        while(args.length != 2) {
            System.out.println("Enter two ip addresses");
        }
        try {
            String ipAddress1 = args[0];
            String ipAddress2 = args[1];
            for(int i = 0; i < numberOfSamples; i++) {
                NetworkUtil netUtil1 = new NetworkUtil(); //init Runnable, and pass arg to thread 1
                NetworkUtil netUtil2 = new NetworkUtil();
                netUtil1.setIpAddress(ipAddress1, numberOfPackets, i);// pass arg to thread 2
                Runnable run1 = netUtil1;
                netUtil2.setIpAddress(ipAddress2, numberOfPackets, i);
                Runnable run2 = netUtil2;
            
                Thread thread1 = new Thread(run1);
                Thread thread2 = new Thread(run2);
                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();
                
                Extract extract1 = new Extract();
                Extract extract2 = new Extract();
            
                extract1.setIpAddress(ipAddress1, i);
                extract2.setIpAddress(ipAddress2, i);
                run1 = extract1;
                run2 = extract2;

                thread1 = new Thread(run1);
                thread2 = new Thread(run2);
                thread1.start();                
                thread2.start();
                
                thread1.join();
                thread2.join(); 
            }
       } catch (Exception e) {
            Logger.log(e.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.DatagramSocket;

/**
 * The implementation of the Netowrking layer of the application.
 * 
 * @author Griffone
 */
public class Network {
    
    private static DatagramSocket sendSocket;
    private static DatagramSocket recSocket;
    
    /**
     * Initialize the Networking layer.
     * 
     * Should be called on startup.
     */
    public static void initialize() {
        
    }
}

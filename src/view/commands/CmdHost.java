/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.commands;

import controller.NetworkHandler;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;
import view.Display;

/**
 * This command will initialize the first Node in a network.
 * Prints the hostring address, so others may join.
 * 
 * Can take a port number as the first parameter, will default to 6942 otherwise.
 * 
 * @author Griffone
 */
public class CmdHost implements Consumer<String[]> {

    @Override
    public void accept(String[] params) {
        short port = 6942;
        // Try to read a port as the first param.
        if (params.length > 2)
            try {
                port = Short.valueOf(params[1]);
            } catch (NumberFormatException ex) {}
        
        Display.putLine("Hostring on port " + String.valueOf(port));
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkHandler.host(port);
            Display.putLine("Address: " + address.getHostAddress());
        } catch (UnknownHostException ex) {
            Display.error("Could not find host! Try resetting your network.");
        }
    }
    
}

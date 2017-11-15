/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.InetSocketAddress;
import network.protocol.Packet;

/**
 * An interface to avoid upward dependency within the project
 * 
 * @author Griffone
 */
public interface NetworkHandler {
    
    /**
     * Called when the network receives a packet different from PT_ANSWER.
     * 
     * The implementation should generallt send an answer packet to the recieved one, but not always.
     * 
     * @param address
     * @param packet 
     */
    public void onPacketReceived(InetSocketAddress address, Packet packet);
}

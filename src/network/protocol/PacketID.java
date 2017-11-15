/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol;

import network.protocol.payload.Payload;
import java.nio.ByteBuffer;
import network.protocol.payload.PLInteger;

/**
 * A packet's unique (to a certain degree) identification.
 * 
 * Can also act as a packet payload
 *
 * @author Griffone
 */
public class PacketID extends PLInteger implements Comparable<PacketID> {
    
    public PacketID(int id) {
        super(id);
    }

    // An overflow-safe comparison
    @Override
    public int compareTo(PacketID o) {
        if (number == o.number)
            return 0;
        if (Math.abs(number - o.number) >= 65536)
            return number > o.number ? 1 : -1;
        else
            return number > o.number ? -1 : 1;
    }
   
}

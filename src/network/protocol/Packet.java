/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import network.protocol.payload.Payload;
import static network.protocol.PacketType.*;
import network.protocol.payload.PLAddress;
import network.protocol.payload.PLInteger;

/**
 *
 * @author Griffone
 */
public class Packet {
    
    // The type of the packet
    public final PacketType   type;
    // The id of the packet
    public final PacketID      id;
    // Packet's payload. Usually is the useful part to code outside network
    public final Payload       payload;
    
    
    private Packet(PacketType type, PacketID id, Payload payload) {
        this.type = type;
        this.id = id;
        this.payload = payload;
    }
    
    public static Packet fromByteArray(byte[] array) {
        if (array == null || array.length < 5)
            return null;
        
        PacketType type = PacketType.fromByte(array[0]);
        // I love java
        PacketID id = new PacketID(0);
        id = (PacketID) id.fromByteArray(array, 1);
        
        Payload payload = null;
        
        switch (type) {
            case PT_INIT:
                if (array.length != 9)
                    return null;
                // There is no way a namespace implementation can solve this static abstract stuff
                payload = new PLInteger(0);
                payload = payload.fromByteArray(array, 5);
                break;
                
            case PT_NODE:
                if (array.length < 10 || array.length != array[5] + 10)
                    return null;
                // And it's impossible for someone to have enough understanding of pointers to create similar behavior cleaner with explicit pointer derefrencing!
                payload = new PLAddress(null);
                payload = payload.fromByteArray(array, 5);
                break;

                
        }
        return new Packet(type, id, payload);
    }
    
    /**
     * Transform this packet to a byte array for sending.
     * 
     * @return a byte array of the packet.
     */
    public byte[] toByteArray() {
        byte[] payloadBytes = null;
        if (payload != null)
            payloadBytes = payload.toByteArray();
        
        byte[] bytes;
        if (payloadBytes != null)
            bytes = new byte[5 + payloadBytes.length];
        else
            bytes = new byte[5];
        
        bytes[0] = type.toByte();
        for (int i = 1; i < 5; i++)
            bytes[1 + i] = (byte)(id.number >> (i * 8));
        for (int i = 5; i < bytes.length; i++)
            bytes[i] = payloadBytes[i - 5];
        return bytes;
    }
    
    public static Packet Init(PacketID id, InetSocketAddress address) {
        Packet packet = new Packet(PT_INIT, id, new PLAddress(address));
        return packet;
    }
    
    /**
     * Create an answer-packet for a given packet.
     * 
     * @param packet    the packet to answer
     * @param id        the id of the new packet
     * @return new answer-packet
     */
    public static Packet Answer(Packet packet, PacketID id) {
        return new Packet(PT_ANSWER, packet.id, null);
    }
}

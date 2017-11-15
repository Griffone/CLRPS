/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol.payload;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An InetSocketAddress payload.
 * 
 * Used by PT_NODE packet.
 * 
 * @author Griffone
 */
public class PLAddress extends Payload {
    
    public final InetSocketAddress address;
    
    public PLAddress(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public byte[] toByteArray() {
        byte[] bytesAddr = address.getAddress().getAddress();
        byte[] bytes = new byte[bytesAddr.length + 5];
        System.arraycopy(bytesAddr, 0, bytes, 1, bytesAddr.length);
        bytes[0] = (byte) bytesAddr.length;
        for (int i = 0; i < 4; i++)
            bytes[i + bytesAddr.length] = (byte)(address.getPort() >> (i * 8));
        return bytes;
    }
    
    @Override
    public Payload fromByteArray(byte[] bytes, int index) {
        int size = bytes[index];
        byte[] bytesAddr = new byte[size];
        System.arraycopy(bytes, index + 1, bytesAddr, 0, size);
        int port = 0;
        for (int i = index + 1 + size; i < index + 5 + size; i++)
            port |= bytes[i] << ((i - index - 1 - size) * 8);
        try {
            InetSocketAddress address = new InetSocketAddress(InetAddress.getByAddress(bytesAddr), port);
            return new PLAddress(address);
        } catch (UnknownHostException ex) {
            return null;
        }
    }
}

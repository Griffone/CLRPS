/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol.payload;

/**
 * A single integer payload.
 * 
 * Used by PT_INIT payload to signify port.
 *
 * @author Griffone
 */
public class PLInteger extends Payload {
    
    public final int number;
    
    public PLInteger(int number) {
        this.number = number;
    }

    @Override
    public byte[] toByteArray() {
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte)(number >> (i * 8));
        return bytes;
    }
    
    @Override
    public Payload fromByteArray(byte[] array, int index) {
        int num = 0;
        for (int i = index; i < index + 4; i++)
            num |= array[i] << ((i - index) * 8);
        return new PLInteger(num);
    }
}

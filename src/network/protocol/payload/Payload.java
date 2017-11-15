/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol.payload;

/**
 * The abstract base class for all types of packet payloads.
 * 
 * @author Griffone
 */
public abstract class Payload {
    
    public abstract byte[] toByteArray();
    /*
    Really should just be static abstract
    But Java is so great, we don't need static classes or static-like behavior for pointer derefrencing.
    It's not like that exists in any other language (C#, C++, C, Python and Lua just for easy examples)
    */
    public abstract Payload fromByteArray(byte[] array, int index);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.protocol;

/**
 * A simple enum for different packet types.
 * In the future might be used as a message-type instead (multiple per packet).
 * 
 * @author Griffone
 */
public enum PacketType {
    PT_NULL,        // A hack for future-proofing the networking
    PT_ANSWER,      // An answer message for any given packet, except the answer-to-answer one. Should be followed by packet id that the answe belongs to.
    
    PT_INIT,        // The first packet from any node trying to connect to a network. Should be answered by a bunch of PT_NODE packets and then the new node should send PT_CONNECT packets.
    PT_CONNECT,     // The connection-establish packet.
    PT_DISCONNECT,  // An optional help-packet. This one is to hopefully reduce round times when a player leaves.
    PT_NODE,        // A network-synchronazation packet
    
    // Game-related packets
    PT_MOVE,        // A player's move
    PT_ROUND,       // A new round agreement-packet
    PT_MESSAGE,     // A string-message packet
    PT_NICKNAME;    // A player-nickname change packet
    
    /**
     * A java-specific hack for packed enum serialization.
     * 
     * @return a byte corresponding to this enum
     */
    public byte toByte() {
        switch (this) {
            case PT_ANSWER:
                return 1;
                
            case PT_INIT:
                return 2;
                
            case PT_CONNECT:
                return 3;
                
            case PT_DISCONNECT:
                return 4;
                
            case PT_NODE:
                return 5;
                
            case PT_MOVE:
                return 6;
                
            case PT_ROUND:
                return 7;
                
            case PT_MESSAGE:
                return 8;
                
            case PT_NICKNAME:
                return 9;
                
            default:
                return 0;
        }
    }
    
    /**
     * The second half of a java-specific hack for enum serialization.
     * 
     * @param b the byte to serialize from
     * @return the corresponding PacketType
     */
    public static PacketType fromByte(byte b) {
        switch (b) {
            case 1:
                return PT_ANSWER;
                
            case 2:
                return PT_INIT;
                
            case 3:
                return PT_CONNECT;
                
            case 4:
                return PT_DISCONNECT;
                
            case 5:
                return PT_NODE;
                
            case 6:
                return PT_MOVE;
                
            case 7:
                return PT_ROUND;
                
            case 8:
                return PT_MESSAGE;
                
            case 9:
                return PT_NICKNAME;
                
            default:
                return PT_NULL;
        }
    }
}
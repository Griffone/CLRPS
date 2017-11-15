/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetSocketAddress;

/**
 * The represintation of a player in a game.
 * 
 * @author Griffone
 */
public class Player {
    
    public Move lastMove;
    public InetSocketAddress address;
    public String nickname;
    public int points;
    
    /**
     * Java-required explicit default constructor, because I have different one
     */
    public Player() {}
    
    /**
     * Create a copy of a player
     * 
     * @param original 
     */
    public Player(Player original) {
        this.lastMove = original.lastMove;
        this.address = original.address;
        this.nickname = original.nickname;
    }
    
    @Override
    public String toString() {
        if (nickname == null)
            return address.toString();
        else
            return nickname;
    }
}

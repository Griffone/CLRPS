/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * The represintation of a move in the game.
 * 
 * @author Griffone
 */
public enum Move {
    M_ROCK,
    M_PAPER,
    M_SCISSORS;
    
    public int toInt() {
        switch (this) {
            case M_ROCK:
                return 1;
                
            case M_PAPER:
                return 2;
                
            case M_SCISSORS:
                return 3;
                
            default:
                return 0;
        }
    }
    
    public static Move fromInt(int i) {
        switch (i) {
            case 1:
                return M_ROCK;
                
            case 2:
                return M_PAPER;
                
            case 3:
                return M_SCISSORS;
                
            default:
                return null;
        }
    }
}

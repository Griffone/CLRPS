/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * An interface for avoiding upward dependencies within the project.
 * 
 * @author Griffone
 */
public interface RoundHandler {
    
    /**
     * Called when a round ends.
     * 
     * Implementation should output the provided info.
     * 
     * @param players the list of players (with updated points)
     * @param round the new current round number
     */
    public void onRoundEnd(List<Player> players, int round);
}

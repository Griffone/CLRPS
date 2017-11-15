/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The actual represintation of the current client's view of the game's state.
 * 
 * @author Griffone
 */
public class GameState {
    
    public static RoundHandler handler;
    
    private static int round = 0;
    private static Player localPlayer;
    private static final HashMap<InetSocketAddress, Player> PLAYERS = new HashMap();
    
    private static int rocks;
    private static int papers;
    private static int scissors;
    
    /**
     * Add a new player
     * 
     * @param player the new player
     */
    public static void addPlayer(Player player) {
        if (!PLAYERS.containsKey(player.address))
            synchronized(PLAYERS) {
                if (!PLAYERS.containsKey(player.address))
                    PLAYERS.put(player.address, player);
            }
    }
    
    /**
     * Drop a player with provided address
     * 
     * @param address the address of the player to drop
     */
    public static void dropPlayer(InetSocketAddress address) {
        if (PLAYERS.containsKey(address))
            synchronized(PLAYERS) {
                if (PLAYERS.containsKey(address))
                    PLAYERS.remove(round);
            }
    }
    
    /**
     * Perform a move for a player
     * 
     * @param address the address of remote player or null for local player
     * @param move the player's move
     */
    public static void playerMove(InetSocketAddress address, Move move) {
        if (address == null)
            synchronized(localPlayer) {
                localPlayer.lastMove = move;
            }
        else
            if (PLAYERS.containsKey(address))
                synchronized(PLAYERS) {
                    PLAYERS.get(address).lastMove = move;
                }
    }
    
    /**
     * Change a player's nickname.
     * 
     * @param address the address of remote player or null for local player
     * @param nickname the player's new nickname
     */
    public static void changeNickname(InetSocketAddress address, String nickname) {
        if (address == null)
            synchronized(localPlayer) {
                localPlayer.nickname = nickname;
            }
        else
            if (PLAYERS.containsKey(address))
                synchronized(PLAYERS) {
                    PLAYERS.get(address).nickname = nickname;
                }
    }
    
    /**
     * Get a copy of a player (changes to this object will not be saved by the GameState)
     * 
     * @param address player's address
     * @return the copy of a player at given address or null if the player doesn't exist
     */
    public static Player getPlayer(InetSocketAddress address) {
        Player player = null;
        if (PLAYERS.containsKey(address)) {
            synchronized(PLAYERS) {
                if (PLAYERS.containsKey(address))
                    player = new Player(PLAYERS.get(address));
            }
        }
        return player;
    }
    
    /**
     * Show the current state of the game.
     * 
     * Can be called when not everything is synchronized, but will display wrong information.
     * 
     * Will call handler.onRoundEnd()
     */
    public static void showState() {
        if (handler != null) {
            List<Player> players = new LinkedList();
            PLAYERS.forEach((address, player) -> {
                Player newPlayer = new Player(player);
                newPlayer.lastMove = null;  // No cheating!
                players.add(newPlayer);
            });
            handler.onRoundEnd(players, round);
        }
    }
    
    /**
     * End the current round.
     * 
     * Should only be called once everything is syncrhonized.
     * 
     * Will call handler.onRoundEnd()
     */
    public static void endRound() {
        List<Player> players = new LinkedList();
        rocks = 0;
        papers = 0;
        scissors = 0;
        PLAYERS.forEach((address, player) -> {
            switch (player.lastMove) {
                case M_ROCK:
                    rocks++;
                    break;
                    
                case M_PAPER:
                    papers++;
                    break;
                    
                case M_SCISSORS:
                    scissors++;
                    break;
            }
        });

        PLAYERS.forEach((address, player) -> {
            switch (player.lastMove) {
                case M_ROCK:
                    player.points += scissors;
                    break;
                    
                case M_PAPER:
                    player.points += rocks;
                    break;
                    
                case M_SCISSORS:
                    player.points += papers;
                    break;
            }
            players.add(new Player(player));
        });
        
        round++;
        if (handler != null)
            handler.onRoundEnd(players, round);
    }
}

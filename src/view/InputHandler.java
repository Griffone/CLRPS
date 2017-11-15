/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.HashMap;
import java.util.function.Consumer;

import view.commands.CmdHost;

/**
 *
 * @author Griffone
 */
public class InputHandler {
    
    public static final HashMap<String, Consumer<String[]>> COMMANDS = new HashMap();
    
    public static void initializeCommands() {
        COMMANDS.put("host", new CmdHost());
    }
    
    public static void parseLine(String line) {
        String[] words = line.split(" ");
        words[0] = words[0].toLowerCase();
        Consumer<String[]> command = COMMANDS.get(words[0]);
        if (command != null)
            command.accept(words);
    }
}

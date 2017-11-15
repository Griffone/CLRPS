/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Griffone
 */
public class Display {
    
    public static void putLine(String line) {
        System.out.println(line);
    }
    
    public static void put(String string) {
        System.out.print(string);
    }
    
    public static void error(String error) {
        System.err.println(error);
    }
}

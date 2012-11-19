package edu.x3m.kas.core;


import edu.x3m.kas.utils.ConsoleTest;


/**
 *
 * @author Hans
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main (String[] args) {
        HammingDecoder.debug = Boolean.TRUE;
        HammingEncoder.debug = Boolean.TRUE;


        while (!new ConsoleTest ().run ());
    }
}

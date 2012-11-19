package edu.x3m.kas.utils;


import edu.x3m.kas.core.Hamming;
import edu.x3m.kas.core.HammingDecoder;
import edu.x3m.kas.core.HammingEncoder;
import edu.x3m.kas.utils.ErrorUtil.BitError;
import java.util.Scanner;


/**
 *
 * @author Hans
 */
public class ConsoleTest {


    private Scanner sc = new Scanner (System.in);



    public boolean run () {
        try {
            return runTest ();
        } catch (Exception ex) {
            System.out.println ("Duh..., " + ex.toString ());
            return false;
        }
    }



    private boolean runTest () {
        int choice;
        String data;


        do {
            printMenu ();
            choice = sc.nextInt ();

            switch (choice) {
                case -1:
                    break;


                case 0:
                    demo ();
                    break;


                case 1:
                    System.out.println ("Enter phrase: ");
                    data = sc.next ();

                    System.out.println ("Enter mistake mode: ");
                    printManual ();
                    while ((choice = sc.nextInt ()) != -1) {
                        manual (data, choice);
                        System.out.println ("Enter mistake mode: ");
                        printManual ();
                    }

                    printMenu ();
                    choice = sc.nextInt ();
                    break;


                case 2:
                    HammingDecoder.debug = !HammingDecoder.debug;
                    choice = 0;
                    break;


                case 3:
                    HammingEncoder.debug = !HammingEncoder.debug;
                    choice = 0;
                    break;


                default:
                    System.out.println ("Huh?");
                    break;
            }
        } while (choice != -1);
        return true;
    }



    private void demo () {
        byte[] result;
        String data;
        BitError error;
        data = "Hans";


        //HammingDecoder.debug = Boolean.FALSE;
        //HammingEncoder.debug = Boolean.FALSE;
        error = ErrorUtil.BitError.NO_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");



        //HammingDecoder.debug = Boolean.TRUE;
        //HammingEncoder.debug = Boolean.TRUE;
        error = ErrorUtil.BitError.ONE_BIT_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");



        //HammingDecoder.debug = Boolean.TRUE;
        //HammingEncoder.debug = Boolean.TRUE;
        error = ErrorUtil.BitError.TWO_BIT_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");



        //HammingDecoder.debug = Boolean.TRUE;
        //HammingEncoder.debug = Boolean.TRUE;
        error = ErrorUtil.BitError.THREE_BIT_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");



        //HammingDecoder.debug = Boolean.TRUE;
        //HammingEncoder.debug = Boolean.TRUE;
        error = ErrorUtil.BitError.RANDOM_ZERO_OR_ONE_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");



        //HammingDecoder.debug = Boolean.TRUE;
        //HammingEncoder.debug = Boolean.TRUE;
        error = ErrorUtil.BitError.RANDOM_ZERO_TO_THREE_ERROR;
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");
    }



    private void printMenu () {
        System.out.println ("HELP:");
        System.out.println ("\t0 demo");
        System.out.println ("\t1 manual");
        System.out.println ("\t2 turn " + (HammingDecoder.debug ? "off" : "on") + " decoder debug");
        System.out.println ("\t3 turn " + (HammingEncoder.debug ? "off" : "on") + " encoder debug");
        System.out.println ("\t-1 exit");
        System.out.println ();
    }



    private void printManual () {
        System.out.println (")");
        System.out.println ("\t? NO_ERROR");
        System.out.println ("\t0 NO_ERROR");
        System.out.println ("\t1 ONE_BIT_ERROR");
        System.out.println ("\t2 TWO_BIT_ERROR");
        System.out.println ("\t3 THREE_BIT_ERROR");
        System.out.println ("\t4 RANDOM_ZERO_TO_THREE_ERROR");
        System.out.println ("\t5 RANDOM_ZERO_OR_ONE_ERROR");
        System.out.println ("\t-1 back to menu");
        System.out.println (")");
    }



    private void manual (String data, int errorCode) {
        byte[] result;
        BitError error = getError (errorCode);
        result = Hamming.encode (data);
        ErrorUtil.putMistakes (result, error);
        result = Hamming.decode (result);
        System.out.format ("Result (%s): %s%n", error, new String (result).equals (data) ? "Match" : "Do not match");
    }



    private BitError getError (int errorCode) {
        switch (errorCode) {
            case 1:
                return BitError.ONE_BIT_ERROR;
            case 2:
                return BitError.TWO_BIT_ERROR;
            case 3:
                return BitError.THREE_BIT_ERROR;
            case 4:
                return BitError.RANDOM_ZERO_TO_THREE_ERROR;
            case 5:
                return BitError.RANDOM_ZERO_OR_ONE_ERROR;
            case 0:
            default:
                return BitError.NO_ERROR;
        }
    }
}

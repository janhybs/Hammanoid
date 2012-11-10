package edu.x3m.kas.utils;


/**
 *
 * @author Hans
 */
public class BinaryUtil {


    public static int getParity (int b) {
        int result = 1;

        //# xor of i-th bit which is extracted and shifted back to base
        for (int i = 0; i < 13; i++)
            result ^= (b & (1 << i)) >> i;

        return result;
    }



    public static void print (int b) {
        System.out.println (format (b));
    }



    public static void print (int b, String msg) {
        System.out.println (msg + ": " + format (b));
    }



    public static void print (int b, int n) {
        System.out.println (format (b, n));
    }



    public static String format (int b) {
        return format (b, 13);
    }



    public static String format (int b, int n) {
        return format (b, n, "0");
    }



    public static String format (int b, int n, String c) {
        String res = Integer.toString (b, 2);
        while (res.length () < n) res = c + res;
        return res;
    }
}

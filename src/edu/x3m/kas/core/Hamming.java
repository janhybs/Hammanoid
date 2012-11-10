package edu.x3m.kas.core;


/**
 *
 * @author Hans
 */
public class Hamming {


    public static byte[] encode (byte[] bytes) {
        return HammingEncoder.encode (bytes);
    }



    public static byte[] encode (String data) {
        return HammingEncoder.encode (data);
    }



    public static byte[] decode (byte[] bytes) {
        return HammingDecoder.decode (bytes);
    }



    public static byte[] decode (String data) {
        return HammingDecoder.decode (data);
    }
}

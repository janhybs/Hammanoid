package edu.x3m.kas.core;


import edu.x3m.kas.utils.ErrorUtil;
import edu.x3m.kas.utils.ErrorUtil.BitError;


/**
 *
 * @author Hans
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        byte[] result;
        String data;
        BitError error;
        
        data = "Hans";
        
        
        HammingDecoder.debug = Boolean.FALSE;
        HammingEncoder.debug = Boolean.FALSE;
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

}

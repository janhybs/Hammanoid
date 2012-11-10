package edu.x3m.kas.core;


import edu.x3m.kas.utils.BinaryUtil;


/**
 *
 * @author Hans
 */
public class HammingEncoder {


    public static boolean debug = Boolean.FALSE;
    //#
    private static final int[] MASK = {
        0b1111111111111,
        0b0101010101010,
        0b0011001100110,
        0b0000111100001,
        0b0000000011111
    };
    private static final int[] SHIFT = {
        9, 7, 6, 5, 3, 2, 1, 0
    };
    private static final int[] PARITY = {
        12, 11, 10, 8, 4
    };



    /**
     * Method encode data to humming code
     *
     * @param data to be encoded
     * @return encoded data
     */
    public static byte[] encode (byte[] bytes) {
        byte[] result = new byte[bytes.length * 2];

        int n;
        if (debug)
            System.out.println ("Encode: ");
        for (int i = 0; i < bytes.length; i++) {
            n = byte2HummByte (bytes[i] & 255);

            result[2 * i] = (byte) (n >> 5);
            result[2 * i + 1] = (byte) (n & 31);

            if (debug)
                System.out.format ("%5d. %s b (%03d) => %s b (%05d)%n", i,
                        BinaryUtil.format (bytes[i] & 255, 13, " "),
                        bytes[i] & 255,
                        BinaryUtil.format (n), n);
        }

        if (debug)
            System.out.println ("");
        return result;
    }



    /**
     * Method encode data to humming code
     *
     * @param data to be encoded
     * @return encoded data
     */
    public static byte[] encode (String data) {
        return encode (data.getBytes ());
    }


    //--------------------------------------
    //# Privates
    //--------------------------------------

    private static int getParity (int b) {
        int result = 1;

        //# xor of i-th bit which is extracted and shifted back to base
        for (int i = 0; i < 13; i++)
            result ^= (b & (1 << i)) >> i;

        return result;
    }



    private static int createHummByte (int b) {
        int result = 0;

        //# i-th bit is extracted and shifted back to base and shifted to final position
        for (int i = 0; i < 8; i++)
            result += (((b & (1 << (7 - i)))) >> (7 - i)) << SHIFT[i];

        return result;
    }



    private static int byte2HummByte (int b) {
        int n = createHummByte (b);

        //# calculating parity of unmasked part
        for (int i = 1; i <= 4; i++)
            n += getParity (n & MASK[i]) << PARITY[i];
        n += getParity (n & MASK[0]) << PARITY[0];
        return n;
    }
}

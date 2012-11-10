package edu.x3m.kas.core;


import edu.x3m.kas.utils.BinaryUtil;


/**
 *
 * @author Hans
 */
public class HammingDecoder {


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



    /**
     * Method decode data from humming code
     *
     * @param data to be decoded
     * @return decoded data
     */
    public static byte[] decode (byte[] bytes) {

        int n, sP, sX;
        byte[] result = new byte[bytes.length / 2];

        if (debug)
            System.out.println ("Decode: ");
        for (int i = 0; i < bytes.length / 2; i++) {
            n = ((bytes[2 * i] & 255) << 5) + (bytes[2 * i + 1] & 255);
            sP = (getParity (n & MASK[1]) << 0)
                    + (getParity (n & MASK[2]) << 1)
                    + (getParity (n & MASK[3]) << 2)
                    + (getParity (n & MASK[4]) << 3);
            sX = getParity (n);

            if (debug)
                System.out.format ("%5d. syndrom = %s, sx = %d%n", i, BinaryUtil.format (sP, 4), sX);


            //# no error || all error
            if (sP == 0 && sX == 0) {
                result[i] = (byte) parseHummByte (n);

                if (debug) {
                    System.out.format ("%5d. NO-ERROR%n", i);
                    System.out.format ("%5d. %s b (%03d) => %s b (%05d)%n", i,
                            BinaryUtil.format (bytes[i] & 255, 13, " "),
                            bytes[i] & 255, BinaryUtil.format (n), n);
                }


                //# one bit error || even bumber of error bits
            } else if (sP != 0 && sX == 1) {
                //# correction
                n ^= (1 << (12 - sP));
                result[i] = (byte) parseHummByte (n);

                if (debug) {
                    System.out.format ("%5d. ONE-BIT-ERROR, Error @ %d%n", i, sP);
                    System.out.format ("%5d. %s b (%03d) => %s b (%05d)%n", i,
                            BinaryUtil.format (bytes[i] & 255, 13, " "),
                            bytes[i] & 255, BinaryUtil.format (n), n);
                }



                //# three error bits
            } else if (sP == 0 && sX == 1) {
                result[i] = (byte) parseHummByte (n);

                if (debug) {
                    System.out.format ("%5d. THREE-BIT-ERROR%n", i);
                    System.out.format ("%5d. %s b (%03d) => %s b (%05d) (???)%n", i,
                            BinaryUtil.format (bytes[i] & 255, 13, " "),
                            bytes[i] & 255, BinaryUtil.format (n), n);
                }


                //# odd number of error bits
            } else if (sP != 0 && sX == 0) {
                result[i] = (byte) parseHummByte (n);

                if (debug) {
                    System.out.format ("%5d. TWO-BIT-ERROR%n", i);
                    System.out.format ("%5d. %s b (%03d) => %s b (%05d) (???)%n", i,
                            BinaryUtil.format (bytes[i] & 255, 13, " "),
                            bytes[i] & 255, BinaryUtil.format (n), n);
                }
            }

            if (debug)
                System.out.println ("");
        }

        return result;
    }



    /**
     * Method decode data from humming code
     *
     * @param data to be decoded
     * @return decoded data
     */
    public static byte[] decode (String data) {
        return decode (data.getBytes ());
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



    private static int parseHummByte (int b) {
        int result = 0;

        //# i-th bit is extracted and shifted back to base and shifted to final position
        for (int i = 0; i < 8; i++)
            result += (((b & (1 << SHIFT[i]))) >> SHIFT[i]) << (7 - i);

        return result;
    }
}

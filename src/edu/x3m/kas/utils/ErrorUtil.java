package edu.x3m.kas.utils;


/**
 *
 * @author Hans
 */
public class ErrorUtil {


    private static final int[] RANDOM = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


    public static enum BitError {


        NO_ERROR,
        ONE_BIT_ERROR,
        TWO_BIT_ERROR,
        THREE_BIT_ERROR,
        RANDOM_ZERO_TO_THREE_ERROR,
        RANDOM_ZERO_OR_ONE_ERROR
    };



    public static byte[] putMistakes (byte[] bytes, BitError error) {
        error = error == null ? BitError.NO_ERROR : error;


        int n = -1, r;
        int[] errors;
        BitError tmpError;
        for (int i = 0; i < bytes.length / 2; i++) {

            //# random mistakes
            if (error == BitError.RANDOM_ZERO_TO_THREE_ERROR || error == BitError.RANDOM_ZERO_OR_ONE_ERROR)
                tmpError = (n = (int) (Math.random () * (error == BitError.RANDOM_ZERO_TO_THREE_ERROR ? 4 : 2))) == 0
                        ? BitError.NO_ERROR : n == 1
                        ? BitError.ONE_BIT_ERROR : n == 2
                        ? BitError.TWO_BIT_ERROR : BitError.THREE_BIT_ERROR;
            else tmpError = error;

            //# 2 bytes into 1
            n = ((bytes[2 * i] & 255) << 5) + (bytes[2 * i + 1] & 255);

            //# certain bit(s) negation
            switch (tmpError) {
                case TWO_BIT_ERROR:
                case THREE_BIT_ERROR:
                    errors = getRandoms (tmpError == BitError.TWO_BIT_ERROR ? 2 : 3);
                    for (int j = 0; j < errors.length; j++)
                        n ^= (1 << errors[j]);
                    break;

                case ONE_BIT_ERROR:
                    n ^= (1 << (int) (Math.random () * 12));
                    break;

                default:
                case NO_ERROR:
                    break;
            }

            //# putting it back
            bytes[2 * i] = (byte) (n >> 5);
            bytes[2 * i + 1] = (byte) (n & 31);

        }
        return bytes;
    }



    private static int[] getRandoms (int n) {
        int k, m;

        //# shuffle
        for (int i = 0, l = RANDOM.length; i < l; i++) {
            k = (int) (Math.random () * l);

            m = RANDOM[i];
            RANDOM[i] = RANDOM[k];
            RANDOM[k] = m;
        }

        int[] result = new int[n];
        System.arraycopy (RANDOM, 0, result, 0, n);
        return result;
    }
}

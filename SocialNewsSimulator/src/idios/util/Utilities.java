package idios.util;

import java.util.Random;

public class Utilities {
    public static Random rand = new Random();

    public static int randIntRange(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        return rand.nextInt(b - a) + a;
    }
    
    public static boolean percentChance(double chance) {
        return rand.nextDouble() < chance;
    }
}

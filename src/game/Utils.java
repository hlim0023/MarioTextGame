package game;
import java.util.ArrayList;
import java.util.Random;
public class Utils {
    /**
     * @author Huiying LIN
     * @version 23th April 2022
     * @see  2099 Assignment 2
     */
    public static int generateID(int num) {
        /**
         * @param empty
         * @return int random number
         * @exception no
         * @throws no
         */
        Random r = new Random();
        int low = 0;
        int high = num;
        return (r.nextInt(high - low) + 1);
    }

    public static boolean idRateCheck(int rate){
        int n = generateID(100);
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 1; i <=rate; i++)
            lst.add(i);

        if (lst.contains(n))
            return true;
        else
            return false;

    }
}

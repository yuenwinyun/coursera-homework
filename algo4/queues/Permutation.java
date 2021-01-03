/* *****************************************************************************
 *  Name:              Yuen
 *  Coursera User ID:  123456
 *  Last modified:     06/12/2002
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        for (String s : randomizedQueue) {
            System.out.println(s);
            k--;

            if (k == 0) {
                break;
            }
        }
    }
}

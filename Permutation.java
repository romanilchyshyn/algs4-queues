/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (true) {
            try {
                String s = StdIn.readString();
                rq.enqueue(s);
            } catch (NoSuchElementException e) {
                break;
            }
        }

        for (String e : rq) {
            if (k == 0) {
                break;
            }

            System.out.println(e);
            k--;
        }
    }
}

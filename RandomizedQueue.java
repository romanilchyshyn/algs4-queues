/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private ArrayList<Item> a = new ArrayList<>();

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return a.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return a.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        a.add(item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return a.remove(0);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return a.get(StdRandom.uniform(a.size()));
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private ArrayList<Item> shuffled;
        private Iterator<Item> ai;

        RandomizedQueueIterator() {
            Item[] tempA = (Item[]) a.toArray();
            StdRandom.shuffle(tempA);
            shuffled = new ArrayList<Item>(Arrays.asList(tempA));
            ai = shuffled.iterator();
        }

        public boolean hasNext() {
            return ai.hasNext();
        }

        public Item next() {
            return ai.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        rq.enqueue("5");
        rq.enqueue("6");

        RandomizedQueue.dumpDeque(rq);

        System.out.println(rq.dequeue());
        System.out.println(rq.sample());

        RandomizedQueue.dumpDeque(rq);

    }

    private static void dumpDeque(RandomizedQueue<String> rq) {
        System.out.println();
        System.out.printf("size = %d\n", rq.size());
        for (String e : rq) {
            System.out.printf(" %s->", e);
        }
        System.out.println();
    }
}

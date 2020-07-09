/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (N == a.length) {
            resize(2 * a.length);
        }

        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(N);
        Item item = a[randomIndex];

        Item lastItem = a[N-1];
        a[randomIndex] = lastItem;

        if (N > 0 && N == a.length/4) {
            resize(a.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return a[StdRandom.uniform(N)];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] shuffled;
        private int current = 0;

        RandomizedQueueIterator() {
            shuffled = (Item[]) new Object[a.length];
            for (int i = 0; i < N; i++)
                shuffled[i] = a[i];

            StdRandom.shuffle(shuffled);
        }

        public boolean hasNext() {
            return shuffled.length > 0 && current != shuffled.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return shuffled[current++];
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

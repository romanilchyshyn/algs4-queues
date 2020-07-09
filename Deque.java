/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            init(item);
        } else {
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            init(item);
        } else {
            Node newLast = new Node();
            newLast.item = item;
            newLast.prev = last;
            last.next = newLast;
            last = newLast;
        }

        size++;
    }

    private void init(Item item) {
        Node init = new Node();
        init.item = item;
        init.prev = null;
        init.next = null;
        first = init;
        last = init;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item result = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        size--;

        return result;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item result = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        size--;

        return result;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item result = current.item;
            current = current.next;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();

        d.addLast("1");
        Deque.dumpDeque(d);
        d.removeLast();
        Deque.dumpDeque(d);

        System.out.println("-----");

        d.addFirst("1");
        Deque.dumpDeque(d);
        d.removeFirst();
        Deque.dumpDeque(d);

        System.out.println("-----");

        d.addLast("1");
        Deque.dumpDeque(d);
        d.removeFirst();
        Deque.dumpDeque(d);

        System.out.println("-----");

        d.addFirst("1");
        Deque.dumpDeque(d);
        d.removeLast();
        Deque.dumpDeque(d);

        System.out.println("-----");

        // d.removeLast();
        // d.removeFirst();
        // d.addLast(null);
        // d.addFirst(null);

        System.out.println("-----");

        d.addFirst("0");
        Deque.dumpDeque(d);

        d.addLast("1");
        Deque.dumpDeque(d);

        d.addLast("2");
        Deque.dumpDeque(d);

        d.addFirst("-1");
        Deque.dumpDeque(d);

        d.removeLast();
        Deque.dumpDeque(d);

        d.removeLast();
        Deque.dumpDeque(d);

        d.removeFirst();
        Deque.dumpDeque(d);

        d.addLast("2");
        Deque.dumpDeque(d);

        d.addFirst("-2");
        Deque.dumpDeque(d);

    }

    private static void dumpDeque(Deque<String> d) {
        System.out.println();
        System.out.printf("size = %d\n", d.size());
        for (String e : d) {
            System.out.printf(" %s->", e);
        }
        System.out.println();
    }

}

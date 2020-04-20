package week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] elements;
    private int first;
    private int last;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    // construct an empty deque
    public Deque() {
        elements = (Item[]) new Object[16];
    }

    public Deque(int numElements) {
        elements = (Item[])new Object[(numElements < 1) ? 1 : (numElements == Integer.MAX_VALUE) ? Integer.MAX_VALUE : numElements + 1];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == last;
    }

    // return the number of items on the deque
    public int size() {
        return elements.length;
    }

    // add the item to the front
    public void addFirst(Item item)  {
        if (item == null)
            throw new IllegalArgumentException();

        elements[first = dec(first, elements.length)] = item;
        if (last == first)
            grow(1);
    }

    private static int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    private static int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        elements[last] = item;
        if (first == (last = inc(last, elements.length)))
            grow(1);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = elements[first];
        final int f = first;
        if (item != null) {
            elements[f] = null;
            first = inc(f, elements.length);
        }
        else throw new NoSuchElementException();

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        final int t;
        t = dec(last, elements.length);
        Item item = elements[t];
        if (item != null)
            elements[last = t] = null;
        else throw new NoSuchElementException();

        return item;
    }

    private void print() {
        System.out.println("Deque:");
        Iterator<Item> it  = iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item != null)
                StdOut.print(item + " ");
        }
    }

    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = elements.length;
        int newCapacity;
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        final Object[] es = elements = Arrays.copyOf(elements, newCapacity);
        if (last < first || (last == first && es[first] != null)) {
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(es, first,
                    es, first + newSpace,
                    oldCapacity - first);
            for (int i = first, to = (first += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elements.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private int cursor;
        int remaining = size();

        public LinkedIterator() {
            cursor = first;
        }

        public boolean hasNext()  {
            return remaining > 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            Item e = elements[cursor];
            cursor = inc(cursor, elements.length);
            remaining--;
            return e;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>(5);
        deque.addFirst("a");
        deque.addFirst("c");
        deque.addLast("x");
        deque.addLast("z");
        deque.print();
        deque.removeFirst();
        deque.removeLast();
        deque.print();
    }

}
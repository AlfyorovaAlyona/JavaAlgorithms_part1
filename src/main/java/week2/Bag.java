package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private static class Node<Item> {
        Item item;
        Node<Item> next;
    }

    private Node<Item> first;
    private int n;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Bag() {
        first = null;
    }
    public void add(Item x) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = x;
        first.next = oldFirst;
        n++;
    }

    public int size() {
        return n;
    }
}

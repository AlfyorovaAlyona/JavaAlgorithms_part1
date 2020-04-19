package week2;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
    }
    private Node first;

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        Node x = first;
        int count = 1;
        while (x.next != null) {
            x = x.next;
            count++;
        }
        return count;
    }
}

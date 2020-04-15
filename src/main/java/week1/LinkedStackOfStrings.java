package week1;

import java.util.LinkedList;

public class LinkedStackOfStrings {

    private class Node {
        String item;
        Node next;
    }

    private Node first = null;

    public LinkedStackOfStrings() {

    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop() {
        String item = first.item;
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

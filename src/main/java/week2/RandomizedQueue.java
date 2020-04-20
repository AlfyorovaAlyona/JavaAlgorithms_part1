package week2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;


    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[])new Object[2];
        n = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if(n == q.length)
        {
            resize(2*q.length);
        }
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = q[rand];
        if (rand != n - 1) {
            q[rand] = q[n-1];
        }
        q[n-1] = null;
        n--;
        if(n > 0 && n <= q.length/4)
        {
            resize(q.length/2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = q[rand];
        if(n > 0 && n == q.length/4)
        {
            resize(q.length/2);
        }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    private class RandomIterator implements Iterator<Item> {

        private int randLoc = 0;
        private int copySize = n;
        private Item[] copy = (Item[]) new Object[copySize];

        private RandomIterator()
        {
            for(int i = 0; i < copySize;i++)
            {
                copy[i] = q[i];
            }
        }

        public boolean hasNext()
        {
            return copySize > 0;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if(copySize == 0)
            {
                throw new NoSuchElementException();
            }
            randLoc = StdRandom.uniform(copySize);
            Item currentItem = copy[randLoc];
            if(randLoc != copySize-1)
            {
                copy[randLoc] = copy[copySize-1];
            }
            copy[copySize-1] = null;
            copySize--;
            return currentItem;
        }

    }

    private void print() {
        Iterator<Item> it = iterator();
        StdOut.println("RandomizedQueue:");
        while (it.hasNext()) {
            Item item = it.next();
            if (item != null)
                StdOut.println(item);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        rq.enqueue("d");
        rq.enqueue("e");
        rq.enqueue("f");
        String str = rq.sample();
        StdOut.println("RandomSample:" + str);
        rq.print();

        rq.dequeue();
        rq.print();
    }
}

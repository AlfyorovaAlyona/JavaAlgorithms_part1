package week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String str = StdIn.readLine();
        String[] items = str.split(" ");
        for (String item : items) {
            rq.enqueue(item);
        }
        rq.print();
    }
}

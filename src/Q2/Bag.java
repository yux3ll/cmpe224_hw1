package Q2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
//-----------------------------------------------------
// Title: Bag
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Bag implementation from the book.
//-----------------------------------------------------
    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Item get(int index) {
            if (index >= n) {
                throw new IndexOutOfBoundsException();
            }
            Node<Item> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
}
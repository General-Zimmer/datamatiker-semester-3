package myset;

public class MyHashSetChaining<E> implements MySet<E> {
    // The number of elements in the set
    private int size;

    // Hash table is an array with each cell that is a linked list
    private Node<E>[] table;

    public MyHashSetChaining(int bucketsLength) {
        table = (Node<E>[])new Node[bucketsLength];
        size = 0;
    }

    /** Hash function */
    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    /** Remove all elements from this set */
    @Override
    public void clear() {
        size = 0;
        table = (Node<E>[])new Node[table.length];
    }

    /** Return true if the element is in the set */
    @Override
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        return found;
    }

    /** Add an element to the set */
    @Override
    public boolean add(E e) {

        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
                // Already in the set
            } else {
                current = current.next;
            }
        }

        if (!found) {
            if ((double) size / table.length > 0.75) {
                rehash();
                bucketIndex = hash(e.hashCode());
            }
            Node<E> newNode = new Node<>();
            newNode.data = e;
            newNode.next = table[bucketIndex];
            table[bucketIndex] = newNode;
            size++;
        }
        return !found;
    }

    /** Remove the element from the set */
    @Override
    public boolean remove(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        Node<E> previous = null;
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;

                if (previous == null) {
                    table[bucketIndex] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
            } else {
                previous = current;
                current = current.next;
            }
        }
        return !found;
    }

    private void rehash() {
        Node<E>[] tempArray = table;
        table = (Node<E>[]) new Node[table.length * 2 + 1];
        size = 0;

        for (Node<E> node : tempArray) {
            while (node != null) {
                add(node.data);
                node = node.next;
            }
        }
    }

    /** Return true if the set contains no elements */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of elements in the set */
    @Override
    public int size() {
        return size;
    }

    // method only for test purpose
    void writeOut() {
        for (int i = 0; i < table.length; i++) {
            Node<E> temp = table[i];
            if (temp != null) {
                System.out.print(i + "\t");
                while (temp != null) {
                    System.out.print(temp.data + "\t");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }
   private static class Node<E>{
        public E data;
        public Node<E> next;
    }

}

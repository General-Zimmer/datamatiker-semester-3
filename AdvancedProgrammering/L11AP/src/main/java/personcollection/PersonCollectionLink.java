package personcollection;

import java.util.Iterator;

public class PersonCollectionLink implements PersonCollectionI{
    // start is a refrerence to a sentinel in the linked list of persons
    private Node start;
    // number of entries in the list;
    private int size;

    /**
     * Creates an Collection with capacity 16.
     */
    public PersonCollectionLink() {
        start = new Node();
        size = 0;
    }



    /**
     * Adds the entry at the end of this list.
     */
    public void add(Person person) {
       Node temp = this.start;
       while (temp.next != null){
           temp = temp.next;
       }
       Node newNode = new Node();
       newNode.person = person;
       temp.next = newNode;
       this.size++;
    }

    /**
     * Adds the person at the index. Throws IndexOutOfBoundsException if index is
     * not in [0, size()].
     */
    public void add(int index, Person person) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = start;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        Node newNode = new Node();
        newNode.person = person;
        newNode.next = temp.next;
        temp.next = newNode;
        this.size++;
    }

    /**
     * Removes and returns the person at the index. Throws IndexOutOfBoundsException
     * if this list is empty or index is not in [0, size()-1].
     */
    public Person remove(int index) {
        if (index < 0 || index > this.size - 1 /* || size == 0 */) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = start;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        Person person = temp.next.person;
        temp.next = temp.next.next;
        this.size--;
        return person;
    }

    /**
     * Returns the person at the index. Throws IndexOutOfBoundsException if this
     * list is empty or index is not in [0, size()-1].
     */
    public Person get(int index) {
        if (index < 0 || index > this.size - 1 /* || this.size == 0 */) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = start.next;
        for (int i =0; i < index; i++){
            temp = temp.next;
        }

        return temp.person;
    }

    /**
     * Return true if the entry is in this list.
     */
    public boolean contains(Person person) {
        boolean found = false;
        Node temp = start.next;
        while (!found && temp !=null) {
            if (temp.person.equals(person)) {
                found = true;
            }
            else{
                temp = temp.next;
            }
        }
        return found;
    }

    /**
     * Returns the number of entries in this list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this list is empty.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all entries from this list.
     */
    public void clear() {
        start = new Node();
        this.size = 0;
    }

    @Override
    public Iterator<Person> iterator() {
        // todo
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return ("[]");
        }

        StringBuilder sb = new StringBuilder("[");
        Node temp = start.next;
        while (temp != null) {
            sb.append(", " + temp.person);
            temp = temp.next;
        }
        sb.append("]");
        sb.delete(1, 3);
        return sb.toString();
    }

    private class Node{
        Node next;
        Person person;
    }

    // -------------------------------------------------------------------------
    // Ex. Iterator

    // TODO

}

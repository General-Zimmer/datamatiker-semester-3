package myset;

public interface MySet <E>{
  /** Remove all elements from this set */
  void clear();

    /** Return true if the element is in the set */
    boolean contains(E e);

    /** Add an element to the set */
    boolean add(E e);

    /** Remove the element from the set */
    boolean remove(E e);

    /** Return true if the set contains no elements */
    boolean isEmpty();
    /** Return the number of elements in the set */
    int size();

}

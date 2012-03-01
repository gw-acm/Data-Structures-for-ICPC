/**
 * An interface detailing the basic constructs all data structures should have. 
 * You shouldn't be implementing this directly, but using the other interfaces below.
 */
public interface IDS<T implements Comparable<T>> extends Iterable{
    public void add(T toAdd);
    public boolean contains(T t);
    public List<T> asList();
    public String toString();
    public int size();
    public void empty(); //Removes all elements from the data structure
}

/**
 * A linked list interface. It describes a list built from individual parts.
 * Please implement both a singly and doubly linked list.
 */
public interface ILinkedList<T implements Comparable<T>> extends IDS<T> {
    public void sort();
    public T getFirst();
    public T getLast();
    public T get(int i); //Zero-based
}

/**
 * A Queue interface. Think of a queue at the grocery store.
 * Please write a queue and a priority queue.
 * The priority queue should access smaller elements first.
 */
public interface IQueue<T implements Comparable<T>> extends IDS<T> {
    public void enqueue(T t);
    public T dequeue();
}

/**
 * A stack interface. Think of a stack of plates.
 */
public interface IStack<T implements Comparable<T>> extends IDS<T> {
    public void push(T t);
    public T pop();
}

/** 
 * A binary tree interface.
 * Please use this to implement a binary search tree.
 */
public interface IBTree<T implements Comparable<T>> extends IDS<T> {
    public Iterator<T> inOrder();
    public Iterator<T> postOrder();
    public Iterator<T> preOrder();
}

/**
 * A map interface. It associates a Key with a Value.
 * Please write (a) a map that uses hashes and (b) a map that uses your IBTree implementation.
 */
public interface IMap<K implements Comparable<T>, V> extends IDS<K> {
    public void set(K key, V val);
    public V get(K key);
}

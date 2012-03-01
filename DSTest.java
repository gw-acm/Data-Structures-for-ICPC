import java.util.Random;
import java.util.Collections;

/**
 * Lists suites of tests for data structures using the GW ICPC data structure interface. 
 */
//TODO: Get rid of boilerplate code filling the data structure
public class DSTest {

    private int numTests = 10000;
    private Random rand = new Random();

    /***************************************
     *         Generic Test Suite          *
     * To be run over all data structures  *
     ***************************************/

    /**
     * Runs suite of all generic tests. To be run over all datastructures.
     * Note that it runs empty() between each test.
     */
    public void runGenericTestSuite(IDS<Integer> ds) {
	sizeAdded();
	ds.empty();
	emptiedSize();
	ds.empty();
	sizeIterated(ds);
	ds.empty();
	addContains(ds);
	ds.empty();
	listContains(ds);
	ds.empty();
    }

    /**
     * Tests that the size == number of elements added.
     */
    public void sizeAdded(IDS<Integer> ds) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		ds.add(rand.nextInt());
	    }

	    if(ds.size() != r) { 
		System.out.printf("Found bug in sizeAdded after %d tests: added %d elements, but size() returns %d.\n", t, r, ds.size());
	    }
	    ds.empty();
	}
    }

    /**
     * Tests that after empty(), size() == 0.
     */
    public void emptiedSize(IDS<Integer> ds) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		ds.add(rand.nextInt());
	    }
	    ds.empty();
	    if(ds.size() != 0) {
		System.out.printf("Found bug in emptiedSize after %d tests: size() after empty() returns %d", t, ds.size());
	    }
	}
    }
	
    /**
     * Tests that number of elements iterated through is the same as the size().
     */
    public void sizeIterated(IDS<Integer> ds) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int j = 0; j < r; j++) {
		ds.add(rand.nextInt());
	    }

	    int i = 0; // represents number of iterations
	    for(Integer n : ds) {
		i++;
	    }
	    if( i != ds.size()) { //num iterations should equal size()
		printf("Found bug in sizeIterated after %d tests: iterator iterates over %d elements, but size() returns %d.\n", t, i, ds.size());
	    }
	    ds.empty();
	}
    }

    /**
     * Tests that if an element is added to the data structure, then it is contained in the data structure, and if it's not added to the data structure, it is not contained by the data structure.
     */
    public void addContains(IDS<Integer> ds) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt(); //number of elements to add
	    int[] is = new int[r];  //Cache of elements
	    for(int i = 0; i < r; i++) { //add r elements to both the cache and the data structure
		is[i] = rand.nextInt();
		ds.add(is[i]);
	    }
	
	    for(int i = 0; i < r * r; i++) { //check r^2 times
		double prob = rand.nextDouble();
		if(prob < 0.75) { //probability 3/4
		    int k = rand.nextInt(r);
		    if(!(ds.contains(is[k]))) { //stored something, not contained
			System.out.printf("Found bug in addContains after %d tests: added %d as %dth element to datastructure, contains(%i) is false.\n", t, is[k], k, is[k]);
		    }
		} else { //probability 1/4
		    int j = rand.nextInt();
		    boolean b = ds.contains(j); 
		    boolean b2 = true; //true => j not in cache
		    for(int k = 0; k < r; k++) {
			if(is[k] = j) { //j in cache
			    if(!b) {//but, not in data structure
				System.out.printf("Found bug in addContains after %d tests: added %d as %dth element to datastructure, contains(%i) is false.\n", t, is[k], k, is[k]);
			    }
			    b2 = false; //false => j in cache
			}
		    }
		    if(b2 && b) {//j not in cache /\ found
			System.out.printf("Found bug in addContains after %d tests: did not add %d to the datastructure, contains(%d) is true.\n", i, j, j);
		    }

		}
	    }
	    ds.empty();
	}
    }
    
    /**
     * Tests that the list contains exactly those elements that are contained by the data structure.
     */
    public void listContains(IDS<Integer> ds) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int j = 0; j < r; j++) {
		ds.add(rand.nextInt());
	    }

	    List<Integer> lst = ds.asList();
	    for(Integer x : lst) {
		if(!ds.contains(x)) { //x is in lst
		    System.out.printf("Found bug in listContains after %d tests: %d is in asList(), but contains(%d) is false.\n", t, x, x);
		}
	    }
	    for(Integer x : ds) {
		i++;
		if(!ds.contains(x)) { //probably not necessary, but not going to get rid of it now that it's here
		    System.out.printf("Found bug in listContains after %d tests: %d is in iterator, but contains(%d) is false.\n", t, x, x);
		}
		if(!lst.contains(x)) { //x in ds
		    System.out.printf("Found bug in listContains after %d tests: %d is in iterator, but asList().contains(%d) is false.\n", t, x, x);
		}
	    }
	    ds.empty();
	}
    }
    
    /************************************************************
     *                ILinkedList Test Suite                    *
     * To be run over datastructures that implement ILinkedList *
     ************************************************************/

    public void runLinkedListTestSuite(ILinkedList<Integer> lst) {
	runGenericTestSuite(lst);
	lst.empty();
	getIterator(lst);
	lst.empty();
	sortedSame(lst);
	lst.empty();
	first0(list);
	lst.empty();
	lastSize();
	lst.empty();
    }


    /**
     * Tests that when iterated over, the ith element iterated is get(i).
     */
    public void getIterator(ILinkedList<Integer> lst) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int j = 0; j < r; j++) {
		lst.add(rand.nextInt());
	    }

	    int i = 0;
	    for(Integer n : lst) {
		if(n != lst.get(i)) {
		    System.out.printf("Found bug in getItertator after %d tests: %d is the %dth iterated, but lst.get(%d) = %d\n", t, n, i, i, lst.get(i));
		    i++;
		}
	    }
	    lst.empty();
	}
    }

    /**
     * Tests that when the two lists are sorted, the two lists are iterated the same.
     */
    public void sortedSame(ILinkedList<Integer> lst) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int j = 0; j < r; j++) {
		lst.add(rand.nextInt());
	    }

	    List<Integer> lst2 = lst.asList();
	    Collections.sort(lst2);
	    lst.sort();
	    int i = 0;
	    for(Integer n : lst) {
		if(lst2.get(i) != n) {
		    System.out.printf("Found bug in sortedSame after %d tests: %d is the %dth element in lst, but lst2.get(%d) = %d\n", i, n, i, i, lst2.get(i));
		    i++;
		}
	    }
	    lst.empty();
	}
    }

    /**
     * Tests that get(0) == getFirst()
     */
    public void first0(ILinkedList<Integer> lst) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		lst.add(rand.nextInt());
	    }

	    if(lst.getFirst() != lst.get(0)) {
		System.out.printf("Found bug in first0: getFirst() returns %d, get(0) returns %d\n", lst.getFirst(), lst.get(0));
	    }
	    lst.empty();
	}
    }

    /**
     * Tests that get(size() - 1) == getLast().
     */
    public void lastSize(ILinkedList<Integer> lst) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		lst.add(rand.nextInt());
	    }
	    if(lst.getLast() != lst.get(lst.size() - 1)) {
		System.out.printf("Found bug in lastSize: getLast() returns %d, but size() is %d and get(%d) return %d.\n" lst.getLast(), lst.size(), lst.size() - 1, lst.get(lst.size() - 1));
	    }
	    lst.empty();
	}
    }

    /**********************************************************
     *                  Queue Test Suite                      *
     * To be run on classes that work on the IQueue interface *
     **********************************************************/

    public void runQueueSuite(IQueue<Integer> q) {
	runGenericTestSuite(q);
	q.empty();
	enqueue_in_order(q);
    }
    public void runPriorityQueueSuite(IQueue<Integer> q) {
	runGenericQueueSuite(q);
	q.empty();
	enqueue_in_order_priority(q);
    }

    public void runGenericQueueSuite(IQueue<Integer> q) {
	runGenericTestSuite(q);
	q.empty();
	dequeue_iterate(q);
    }

    public void enqueue_in_order(IQueue<Integer> q) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    int[] is = new int[r];
	    for(int i = 0; i < r; i++) {
		is[i] = r.nextInt();
		q.enqueue(is[i]);
	    }
	    
	    int k = 0;
	    for(Integer n : q) {
		if(is[k] != n) {
		    System.out.printf("Found bug in enqueue_in_order after %d tests: the %dth item added is %d, but the iterator accessed %d.\n", t, k, is[k], n);
		}
	    }
	    q.empty();
	}
    }

    public void enqueue_in_order_priority(IQueue<Integer> q) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		q.enqueue(r.nextInt());
	    }

	    int last = q.dequeue();
	    for(Integer n : q) {
		if(n < last) {
		    System.out.printf("Found bug in enqueue_in_order_priority after %d tests: %d was found in the queue, but %d was in the queue before it.\n", t, n, last);
		}
		else {
		    last = n;
		}
	    }
	    q.empty();
	}
    }

    public void dequeue_iterate(IQueue<Integer> q) {
	for(int t = 0; t < numTests; t++) {
	    int r = r.nextInt();
	    for(int i = 0; i < r; i++) {
		q.enqueue(r.nextInt());
	    }

	    for(Integer n : q) {
		int m = q.dequeue();
		if(n != m) {
		    System.out.printf("Found bug in dequeue_iterate after %d tests: iterator returns %d, but dequeue() returns %d.\n", t, n, m);
		}
	    }
	    q.empty();
	}
    }
	
    
    /*********************************************
     *              Stack Test Suite             *
     * For data structures that implement IStack *
     *********************************************/
    
    public void runStackTestSuite(IStack<Integer> s) {
	runGenericTestSuite(s);
	s.empty();
	pop_iterate(s);
	s.empty();
	pop_order(s);
	s.empty();
    }

    public void pop_iterate(IStack<Integer> s) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    for(int i = 0; i < r; i++) {
		s.push(p);
	    }
	    
	    for(Integer n : s) {
		int m = s.pop();
		if(n != m) {
		    System.out.printf("Found bug in pop_iterate after %d tests: Iterator returns %d, while pop returns %d.\n", t, n, m);
		}
	    }
	    
	    s.empty();
	}
    }

    public void pop_order(IStack<Integer> s) {
	for(int t = 0; t < numTests; t++) {
	    int r = rand.nextInt();
	    int[] is = new int[r];
	    for(int i = 0; i < r; i++) {
		is[i] = rand.nextInt();
		s.push(is[i]);
	    }
	    
	    int k = r - 1;
	    for(Integer n : s) {
		if(k < 0) {
		    System.out.printf("Found bug in pop_order after %d tests: iterator iterated over more elements then were added.\n", t);
		    break;
		}
		if(n != is[k]) {
		    System.out.printf("Found bug in pop_order after %d tests: Iterator returns %d, it should return %d.\n", t, n, is[k]);
		}
		k--;
	    }
	    
	    s.empty();
	}
    }
}

			
	
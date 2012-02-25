import java.util.Random;

public class DSTest {

    private int numTests = 10000;
    private Random rand = new Random();

    public void runGenericTestSuite(IDS<Integer> ds) {
	addContains(ds);
	listContains(ds);
    }

    public void addContains(IDS<Integer> ds) {
	int[] is = new int[numTests];
	for(int i = 0; i < numTests; i++) {
	    is[i] = r.nextInt();
	    ds.add(i);
	}
	
	for(int i = 0; i < numTests * numTests; i++) {
	    double prob = r.nextDouble();
	    if(prob < 0.75) {
		int k = r.nextInt(numTests);
		if(!(ds.contains(is[k]))) {
		    System.out.printf("Found bug after %d tests: added %d as %dth element to datastructure, contains(%i) is false.\n", i, is[k], k, is[k]);
		}
	    } else {
		int j = r.nextInt();
		boolean b = ds.contains(j);
		for(int k = 0; k < numTests; k++) {
		    if(is[k] = j) {
			if(!b) {
			    System.out.printf("Found bug after %d tests: added %d as %dth element to datastructure, contains(%i) is false.\n", i, is[k], k, is[k]);
			}
		    } else {
			if(b) {
			    System.out.printf("Found bug after %d tests: did not add %d to the datastructure, contains(%d) is true.\n", i, j, j);
			}
		    }
		}
	    }
	}
    }
    
    public void listContains(IDS<Integer> ds) {
	List<Integer> lst = ds.asList();
	int i = 0;
	for(Integer x : lst) {
	    i++;
	    if(!ds.contains(x)) {
		System.out.printf("Found bug after %d tests: %d is in asList(), but contains(%d) is false.\n", i, x, x);
	    }
	}
	for(Integer x : ds) {
	    i++;
	    if(!ds.contains(x)) {
		System.out.printf("Found bug after %d tests: %d is in iterator, but contains(%d) is false.\n", i, x, x);
	    }
	    if(!lst.contains(x)) {
		System.out.printf("Found bug after %d tests: %d is in iterator, but asList().contains(%d) is false.\n", i, x, x);
	    }
	}
    }
    
    
}
			
	
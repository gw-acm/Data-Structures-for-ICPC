import java.util.ArrayList;

/* used in DSTest.java to test IBTree
 * 
 */

public class BinaryTree {
  static class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
      this.value = value;
    }
  }

  public void insert(Node node, int value) {
    if (value < node.value) {
      if (node.left != null) {
        insert(node.left, value);
      } else {
        node.left = new Node(value);
      }
    } else if (value > node.value) {
      if (node.right != null) {
        insert(node.right, value);
      } else {
        node.right = new Node(value);
      }
    }
  }

  public void getInOrder(Node node, ArrayList<Integer> a) {
    if (node != null) {
      getInOrder(node.left,a);
	  a.add(node.value);
      getInOrder(node.right, a);
    }
  }
  public void getPostOrder(Node node, ArrayList<Integer> a) {
    if (node != null) {
      getPostOrder(node.left, a);
      getPostOrder(node.right, a);
      a.add(node.value); 
    }
  }
  public void getPreOrder(Node node, ArrayList<Integer> a) {
    if (node != null) {
      a.add(node.value); 
      getPreOrder(node.left, a);
      getPreOrder(node.right, a);
    }
  }
}
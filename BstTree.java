/* 
 * 
 * @author ARP
 * @version 2.0
 */ 

import java.util.Scanner;

class BSTNode {

	/* member variables */
	BSTNode left, right; 
	int data; // the individual nodes 

	/* constructor */ 
    public BSTNode() {

        left = null;
        right = null;
        data = 0;

    }

    /* constructor */
    public BSTNode(int n) {

        left = null;
        right = null;
        data = n;

    }

	/* method to set left node */
	public void setLeft(BSTNode n) { left = n; }

	/* method to set right node */
	public void setRight(BSTNode n) { right = n; }

	/* method to get left node */
	public BSTNode getLeft() { return left; }

	/* method to get right node */
	public BSTNode getRight() { return right; }

	/* method to set data to node */ 
	public void setData(int d) { data = d; }

	/* method to get data from node */
	public int getData() { return data; }

}

class BST {

	private BSTNode root;

	/* constructor */
	public BST() { root = null; }

	public boolean isEmpty() { return root == null; }

	public void insert(int data) { root = insert(root, data); }
	

	/* method to insert data recursively
	@param node: the left and right branches 
	@param data: the actual numbers involved with the bst tree
	*/
	private BSTNode insert(BSTNode node, int data) {
		if(node == null) {
			node = new BSTNode(data);
		} else {
			if(data <= node.getData())
				node.left = insert(node.left, data); 
			else 
				node.right = insert(node.right, data);
		}
		return node;
	}

	/* method to delete data if search() returns true */
	public void delete(int n_delete) {
		if(isEmpty()) {
			System.out.println("Tree is empty");

		} else if(search(n_delete) == false) {
			System.out.println("I'm sorry, but " + n_delete + " is empty");
		} else {
			root = delete(root, n_delete);
			System.out.println(root + " is deleted from the tree");
		}
	}

	/* method to delete the actual node */ 
	private BSTNode delete(BSTNode root, int n_delete) {
		BSTNode p, p2, n;
		if(root.getData() == n_delete) {
			BSTNode lt, rt; // left root, right root
			lt = root.getLeft();
			rt = root.getRight();
			/* set the left and right nodes, just in case they're already null */ 
			if(lt == null && rt == null) return null;
			else if(lt == null) {
				p = rt; // the var, p, takes right node if LEFT ROOT is empty
				return p;
			} else if(rt == null) {
				p = lt; // the var, p, takes left node if RIGHT ROOT is empty 
				return p;
			} else {
				p2 = rt;
				p = rt;
				while(p.getLeft() != null)
					p = p.getLeft();
				p.setLeft(lt);
				return p2;
			}
		}
		if(n_delete < root.getData()) {
			n = delete(root.getLeft(), n_delete);
			root.setLeft(n);
		

		} else {
			n = delete(root.getLeft(), n_delete);
			root.setRight(n);
		}
		return root;
	}

	public int countNodes() { return countNodes(root); }

	private int countNodes(BSTNode s_node) {
		if(s_node == null) { return 0; }
		else {
			int sum_nodes = 1;
			sum_nodes += countNodes(s_node.getLeft());
			sum_nodes += countNodes(s_node.getRight());
			return sum_nodes;
		}
	}

	public boolean search(int val) { return search(root, val); }

	private boolean search(BSTNode s_node, int val) {
		boolean found = false;
		while((s_node != null) && !found) {

			int sval = s_node.getData();
			if(val < sval) { s_node.getLeft(); }
			else if(val > sval) { s_node = s_node.getRight(); }
			else {
				found = true;
				break; 
			}
			found = search(s_node, sval);
		}
		return found; // if found
	}
	
	public void inorder() { inorder(root); }
	
	private void inorder(BSTNode s_node) {
		if(s_node != null) {
			inorder(s_node.getLeft());
			System.out.print(s_node.getData() + " ");
			inorder(s_node.getRight());
		}
	}

	public void preorder() { 
		preorder(root);
	}

	private void preorder(BSTNode s_node) {
		if(s_node != null) {

			System.out.println(s_node.getData() + " ");
			preorder(s_node.getLeft());
			preorder(s_node.getRight());
		}
	}
	
	
	public void postorder() { postorder(root); }

	private void postorder(BSTNode s_node) {
		if(s_node != null) {
			postorder(s_node.getLeft());
			postorder(s_node.getLeft());
			System.out.print(s_node.getData() + " ");
		}
	}

}

class BinarySearchTree {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		BST bst = new BST();
		System.out.println("Binary Search Tree Test\n");
		char ch;
		/* this performs all tree operations: insert, delete, search, count nodes, checm empty */
		do {
			System.out.println("\nBinary Search Tree Operations\n");
			System.out.println("1. insert");
			System.out.println("2. delete");
			System.out.println("3. search");
			System.out.println("4. count nodes");
			System.out.println("5. check empty");

			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter integer element to insert");
					bst.insert(scan.nextInt());
					break;

				case 2:
					System.out.println("Enter integer element to delete");
					bst.delete(scan.nextInt());
					break;

				case 3:
					System.out.println("Enter integer element to search\n" +
										" Search result:\n");
					bst.search(scan.nextInt());
					break;

				default:
					System.out.println("Wrong Entry\n");
					break;
				}

				/* display tree */
				System.out.println("\nPre order: ");
				bst.preorder();
				System.out.println("\nPost order: ");
				bst.postorder();
				System.out.println("\nIn order: ");
				bst.inorder();

				System.out.println("\nDo you wnat to continue (Type y or n)");
				ch = scan.next().charAt(0);
			} while(ch == 'Y' || ch == 'y'); /* keep on prompting user until the choice is something other than Y or y */
		}
}

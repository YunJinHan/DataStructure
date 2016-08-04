
public class AVL {
	private Node root = null;

	private class Node {
		private int key;
		private int balanece;
		private int rightheight;
		private int leftheight;
		private Node right;
		private Node left;
		private Node parents;

		Node(int key, Node temp) {
			this.key = key;
			right = null;
			left = null;
			parents = temp;
		}
	}

	void setheight(Node temp){
		if (temp != null){
			temp.leftheight = height(temp.left);
			temp.rightheight = height(temp.right);
			setheight(temp.left);
			setheight(temp.right);
		}
	}
	
	int depth(Node temp){
		return (temp != root) ? 1+depth(temp.parents) : 0;
	}
	
	int height(Node temp){
		return (temp != null) ? 1+Math.max(height(temp.left),height(temp.right)) : 0;
	}
	
	void setbalance(Node ... temp){
		for (Node e : temp){
			e.balanece = height(e.right) - height(e.left);
		}
	}
	
	void rebalance(Node temp){
		setbalance(temp);
		if (temp.balanece == 2){
			if (height(temp.right.right) >= height(temp.right.left)){
				temp = rotateRR(temp);
			} // RR
			else {
				temp = rotateRL(temp);
			} // RL
		} // RR RL
		else if (temp.balanece == -2){
			if (height(temp.left.left) >= height(temp.left.right)){
				temp = rotateLL(temp);
			} // LL
			else {
				temp = rotateLR(temp);
			} // LR
		} // LL LR
		if (temp.parents != null){
			rebalance(temp.parents);
		}
		else {
			root = temp;
		}
		setheight(root);
	}
	
	Node rotateRR(Node a){
		Node b = a.right;
		b.parents = a.parents;
		a.right = b.left;
		if (a.right != null){
			a.right.parents = a;
		}
		b.left = a;
		a.parents = b;
		if (b.parents != null){
			if (b.parents.right == a){
				b.parents.right = b;
			}
			else {
				b.parents.left = b;
			}
		}
		setbalance(a,b);
		return b;
	}
	
	Node rotateLL(Node a){
		Node b = a.left;
		b.parents = a.parents;
		a.left = b.right;
		if (a.left != null){
			a.left.parents = a;
		}
		b.right = a;
		a.parents = b;
		if (b.parents != null){
			if (b.parents.right == a){
				b.parents.right = b;
			}
			else {
				b.parents.left = b;
			}
		}
		setbalance(a,b);
		return b;
	}
	
	Node rotateRL(Node a){
		a.right = rotateLL(a.right);
		return rotateRR(a);
	}
	
	Node rotateLR(Node a){
		a.left = rotateRR(a.left);
		return rotateLL(a);
	}
	
	boolean empty() {
		return root == null;
	}

	boolean search(int key) {
		if (empty()) {
			return false;
		} else {
			Node temp = root;
			while (key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				if (key == temp.key) {
					return true;
				}
				temp = (key > temp.key) ? temp.right : temp.left;
			}
			return false;
		}
	}

	void insert(int key) {
		if (empty()) {
			root = new Node(key, null);
		} else {
			Node temp = root;
			while (key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				temp = (key > temp.key) ? temp.right : temp.left;
			}
			if (key > temp.key) {
				temp.right = new Node(key, temp);
			} else {
				temp.left = new Node(key, temp);
			}
			rebalance(temp);
		}
	}

	void delete(int key) {
		if (empty()) {
			return;
		} else {
			if (search(key)) {
				return;
			}
			Node parent = root;
			Node temp = root;
			boolean R = false;
			boolean L = false;
			while (key == root.key || key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				if (key == temp.key) {
					break;
				}
				parent = temp;
				if (key < temp.key) {
					temp = temp.left;
					L = true;
					R = false;
				} else {
					temp = temp.right;
					L = false;
					R = true;
				}
			}
			if (temp.left == null && temp.right == null) {
				if (temp == root) {
					root = null;
				} else if (R) {
					parent.right = null;
				} else if (L) {
					parent.left = null;
				}
			} else if (temp.left == null) {
				if (temp == root) {
					root = temp.right;
				} else if (R) {
					parent.right = temp.right;
				} else if (L) {
					parent.left = temp.right;
				}
			} else if (temp.right == null) {
				if (temp == root) {
					root = temp.left;
				} else if (R) {
					parent.right = temp.left;
				} else if (L) {
					parent.left = temp.left;
				}
			} else if (temp.left != null && temp.right != null) {
				Node Min = temp.right;
				Node MinParent = temp;
				while (Min.left != null) {
					MinParent = Min;
					Min = Min.left;
				}
				if (MinParent != temp) {
					MinParent.left = Min.right;
					Min.right = temp.right;
				}

				if (temp == root) {
					root = Min;
				} else if (R) {
					parent.right = Min;
				} else if (L) {
					parent.left = Min;
				}
				Min.left = temp.left;
			}
			rebalance(parent);
		}
	}

	void preorder_diff() {
		System.out.print("Preorder Diff : ");
		preorder_diff(root);
		System.out.println();
	}

	void preorder_diff(Node temp) {
		if (temp != null) {
			System.out.print(temp.key + "(" + temp.leftheight + "," + temp.rightheight +","+depth(temp)+") ");
			preorder_diff(temp.left);
			preorder_diff(temp.right);
		}
	}

	void preorder() {
		System.out.print("Preorder : ");
		preorder(root);
		System.out.println();
	}

	void preorder(Node temp) {
		if (temp != null) {
			System.out.print(temp.key + " ");
			preorder(temp.left);
			preorder(temp.right);
		}
	}

	void postorder() {
		System.out.print("Postorder : ");
		postorder(root);
		System.out.println();
	}

	void postorder(Node temp) {
		if (temp != null) {
			postorder(temp.left);
			postorder(temp.right);
			System.out.print(temp.key + " ");
		}
	}

	void inorder() {
		System.out.print("Inorder : ");
		inorder(root);
		System.out.println();
	}

	void inorder(Node temp) {
		if (temp != null) {
			inorder(temp.left);
			System.out.print(temp.key + " ");
			inorder(temp.right);
		}
	}
}

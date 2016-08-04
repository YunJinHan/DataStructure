
public class BST {

	private Node root;

	private class Node {
		private int key;
		private Node right;
		private Node left;

		Node(int key) {
			this.key = key;
			right = null;
			left = null;
		}
	}

	boolean empty() {
		return root == null;
	}

	boolean search(int key) {
		if (empty()) {
			return false;
		} else {
			Node temp = root;
			if (key == temp.key) {
				return true;
			}
			while (key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				temp = (key > temp.key) ? temp.right : temp.left;
				if (key == temp.key) {
					return true;
				}
			}
			return false;
		}
	}

	void insert(int key) {
		if (empty()) {
			root = new Node(key);
		} else {
			if (search(key)) {
				return;
			}
			Node temp = root;
			while (key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				temp = (key > temp.key) ? temp.right : temp.left;
			}
			if (key > temp.key) {
				temp.right = new Node(key);
			} else if (key < temp.key) {
				temp.left = new Node(key);
			}
		}
	}

	void delete(int key) {
		if (empty()) {
			return;
		} else {
			if (!search(key)) {
				return;
			}
			Node temp = root;
			Node parent = root;
			boolean R = false;
			boolean L = false;
			while (key > temp.key && temp.right != null || key < temp.key && temp.left != null) {
				if (key == temp.key) {
					break;
				}
				parent = temp;
				if (key > temp.key) {
					temp = temp.right;
					R = true;
					L = false;
				} else if (key < temp.key) {
					temp = temp.left;
					R = false;
					L = true;
				}
			}
			if (temp.right == null && temp.left == null) {
				if (temp == root) {
					root = null;
				} else if (R) {
					parent.right = null;
				} else if (L) {
					parent.left = null;
				}
			} else if (temp.right == null) {
				if (temp == root) {
					root = temp.left;
				} else if (R) {
					parent.right = temp.left;
				} else if (L) {
					parent.left = temp.left;
				}
			} else if (temp.left == null) {
				if (temp == root) {
					root = temp.right;
				} else if (R) {
					parent.right = temp.right;
				} else if (L) {
					parent.left = temp.right;
				}
			} else if (temp.right != null && temp.left != null) {
				// ########################## //
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
				// ########################## //
				if (temp == root) {
					root = Min;
				} else if (R) {
					parent.right = Min;
				} else if (L) {
					parent.left = Min;
				}
				// ########################## //
				Min.left = temp.left;
				// ########################## //
			}
		}
	}

	void preorder() {
		System.out.print("Preorder : [ ");
		preorder(root);
		System.out.println("]\n");
	}

	void preorder(Node temp) {
		if (temp != null) {
			System.out.print(temp.key + " ");
			preorder(temp.left);
			preorder(temp.right);
		}
	}

	void inorder() {
		System.out.print("Inorder : [ ");
		inorder(root);
		System.out.println("]\n");
	}

	void inorder(Node temp) {
		if (temp != null) {
			inorder(temp.left);
			System.out.print(temp.key + " ");
			inorder(temp.right);
		}
	}

	void postorder() {
		System.out.print("Postorder : [ ");
		postorder(root);
		System.out.println("]\n");
	}

	void postorder(Node temp) {
		if (temp != null) {
			postorder(temp.left);
			postorder(temp.right);
			System.out.print(temp.key + " ");
		}
	}

}
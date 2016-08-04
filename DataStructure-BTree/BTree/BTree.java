
// DataBase - BTree
// BTree
// Department : Computer Science and Engineering
// SID : 2012036901
// NAME : 윤진한
// 2016.05.14

public class BTree {

	private final static int SIZE = 4;
	private Node root;
	private int height;
	private int total_count;

	public BTree() {
		this.root = null;
		this.height = 1;
		this.total_count = 0;
	}

	private static class Node {
		private int count;
		private Node parent;
		private Key[] Line = new Key[SIZE];

		private Node() {
			for (int i = 0; i < SIZE; i++) {
				Line[i] = new Key();
			}
			this.count = 0;
		}

		private boolean isFull() {
			return count == SIZE;
		}

		private void EqualPointer() {
			for (int i = 0; i < SIZE - 1; i++) {
				Line[i + 1].SmallChild = Line[i].LargeChild;
			}
		}

		private boolean NullPointerCheck() {
			for (int i = 0; i < SIZE; i++) {
				if (Line[i].SmallChild != null) {
					return false;
				}
			}
			if (Line[SIZE - 1].LargeChild != null) {
				return false;
			}
			return true;
		}

		private void setClear(int start, int end) {
			for (int i = start; i <= end; i++) {
				Line[i] = new Key();
			}
		}

		private void sort(int size) {
			for (int i = size - 1; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					if (Line[j].data > Line[j + 1].data) {
						Key temp = Line[j];
						Line[j] = Line[j + 1];
						Line[j + 1] = temp;
					}
				}
			}
			EqualPointer();
		}
	}

	private static class Key {
		private int data;
		private Node LargeChild;
		private Node SmallChild;

		private Key() {
			this.data = -1;
			this.LargeChild = null;
			this.SmallChild = null;
		}

		private Key(int data, Node temp1, Node temp2) {
			this.data = data;
			this.LargeChild = temp1;
			this.SmallChild = temp2;
		}
	}

	private void Sort(Key[] a) {
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j].data > a[j + 1].data) {
					Key temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public Node RootSplit(Node a, int key) {
		Node newNode = new Node();
		Node newNode2 = new Node();
		Key[] temp = new Key[SIZE + 1];
		temp[SIZE] = new Key(key, null, null);
		for (int i = 0; i < SIZE; i++) {
			temp[i] = a.Line[i];
		}
		Sort(temp);
		// new Key and Keys in Node a inserts new Key array
		// new Key array be sorted
		if (temp[SIZE / 2 + 1].data == a.Line[SIZE / 2].data && temp[SIZE / 2].data == key) {
			temp[SIZE / 2 + 1].SmallChild = null;
		}
		newNode.Line[newNode.count++] = temp[SIZE / 2];
		root = newNode;
		a.setClear(0, SIZE - 1);
		a.count = 0;
		for (int i = 0; i < SIZE / 2; i++) {
			a.Line[a.count++] = temp[i];
			newNode2.Line[newNode2.count++] = temp[SIZE / 2 + i + 1];
			if (a.Line[a.count - 1].SmallChild != null) {
				a.Line[a.count - 1].SmallChild.parent = a;
			}
			if (a.Line[a.count - 1].LargeChild != null) {
				a.Line[a.count - 1].LargeChild.parent = a;
			}
			if (newNode2.Line[newNode2.count - 1].SmallChild != null) {
				newNode2.Line[newNode2.count - 1].SmallChild.parent = newNode2;
			}
			if (newNode2.Line[newNode2.count - 1].LargeChild != null) {
				newNode2.Line[newNode2.count - 1].LargeChild.parent = newNode2;
			}
		}
		// new Keys distribute to Node a and Node newNode2
		root.Line[0].SmallChild = a;
		root.Line[0].LargeChild = newNode2;
		a.parent = root;
		newNode2.parent = root;
		a.EqualPointer();
		newNode2.EqualPointer();
		root.EqualPointer();
		height++;
		// pointers rearrange
		if (a.Line[a.count - 1].data >= key) {
			return a;
		} else {
			return newNode2;
		}
	}

	public Node Split(Node a, int key) {
		if (a.parent != null) {
			Node newNode = new Node();
			Node newNode2 = new Node();
			Key[] temp = new Key[SIZE + 1];
			temp[SIZE] = new Key(key, null, null);
			for (int i = 0; i < SIZE; i++) {
				temp[i] = a.Line[i];
			}
			Sort(temp);
			if (temp[SIZE / 2 + 1].data == a.Line[SIZE / 2].data && temp[SIZE / 2].data == key) {
				temp[SIZE / 2 + 1].SmallChild = null;
			}
			if (a.parent.isFull()) {
				if (a.parent == root) {
					newNode2 = RootSplit(a.parent, temp[SIZE / 2].data);
				} else if (a.parent != root) {
					newNode2 = Split(a.parent, temp[SIZE / 2].data);
				}
				a.setClear(0, SIZE - 1);
				a.count = 0;
				for (int i = 0; i < SIZE / 2; i++) {
					a.Line[a.count++] = temp[i];
					newNode.Line[newNode.count++] = temp[SIZE / 2 + i + 1];
					if (a.Line[a.count - 1].SmallChild != null) {
						a.Line[a.count - 1].SmallChild.parent = a;
					}
					if (a.Line[a.count - 1].LargeChild != null) {
						a.Line[a.count - 1].LargeChild.parent = a;
					}
					if (newNode.Line[newNode.count - 1].SmallChild != null) {
						newNode.Line[newNode.count - 1].SmallChild.parent = newNode;
					}
					if (newNode.Line[newNode.count - 1].LargeChild != null) {
						newNode.Line[newNode.count - 1].LargeChild.parent = newNode;
					}
				}
				a.parent.sort(a.parent.count);
				// case 1,4 - child node position is left edge or right edge
				if (newNode2.Line[0].data == temp[SIZE / 2].data) {
					newNode2.Line[0].SmallChild = a;
					newNode2.Line[0].LargeChild = newNode;
					a.parent = newNode2;
					newNode.parent = newNode2;
				}
				// case 2,5 - child node position is second left edge or second
				// right edge
				else if (newNode2.Line[1].data == temp[SIZE / 2].data) {
					newNode2.Line[1].SmallChild = a;
					newNode2.Line[1].LargeChild = newNode;
					a.parent = newNode2;
					newNode.parent = newNode2;
				}
				// case 3 - child node position is mid position
				else {
					a.parent.Line[a.parent.count - 1].LargeChild = a;
					newNode2.Line[0].SmallChild = newNode;
					newNode.parent = newNode2;
				}
			} else {
				a.parent.Line[a.parent.count++] = temp[SIZE / 2];
				a.setClear(0, SIZE - 1);
				a.count = 0;
				for (int i = 0; i < SIZE / 2; i++) {
					a.Line[a.count++] = temp[i];
					newNode.Line[newNode.count++] = temp[SIZE / 2 + i + 1];
					if (a.Line[a.count - 1].SmallChild != null) {
						a.Line[a.count - 1].SmallChild.parent = a;
					}
					if (a.Line[a.count - 1].LargeChild != null) {
						a.Line[a.count - 1].LargeChild.parent = a;
					}
					if (newNode.Line[newNode.count - 1].SmallChild != null) {
						newNode.Line[newNode.count - 1].SmallChild.parent = newNode;
					}
					if (newNode.Line[newNode.count - 1].LargeChild != null) {
						newNode.Line[newNode.count - 1].LargeChild.parent = newNode;
					}
				}
				a.parent.Line[a.parent.count - 1].SmallChild = a;
				a.parent.Line[a.parent.count - 1].LargeChild = newNode;
				newNode.parent = a.parent;
			}
			a.parent.sort(a.parent.count);
			a.sort(a.count);
			newNode.sort(newNode.count);
			if (a.Line[a.count - 1].data >= key) {
				return a;
			} else {
				return newNode;
			}
		}
		return null;
	}

	public void Insert(int key) {
		if (Search(key, 0) != null) {
			System.out.println("There is already " + key + " key.");
		} else {
			Node temp = root;
			Key temp2 = new Key(key, null, null);
			if (temp == null) {
				// there is no root
				root = new Node();
				root.Line[root.count++] = temp2;
				root.EqualPointer();
			} else {
				if (temp.NullPointerCheck() && !temp.isFull()) {
					// root is not full and no child node
					temp.Line[temp.count++] = temp2;
					temp.sort(temp.count);
				} else if (temp.NullPointerCheck() && temp.isFull()) {
					// root is full and no child node
					RootSplit(temp, key);
				} else {
					// root is not full and has child node
					int check = 1;
					while (!temp.NullPointerCheck()) {
						if (temp.Line[0].data > key) {
							if (temp.Line[0].SmallChild == null) {
								check = 0;
							} else {
								temp = temp.Line[0].SmallChild;
							}
						} else if (temp.Line[temp.count - 1].data < key) {
							if (temp.Line[temp.count - 1].LargeChild == null) {
								check = 0;
							} else {
								temp = temp.Line[temp.count - 1].LargeChild;
							}
						} else {
							for (int i = 0; i < temp.count; i++) {
								if (temp.Line[i].data < key && temp.Line[i + 1].data > key) {
									if (temp.Line[i].LargeChild == null) {
										check = 0;
									} else {
										temp = temp.Line[i].LargeChild;
									}
									break;
								}
							}
						}
						if (check == 0) {
							break;
						}
					}
					if (temp.isFull()) {
						// child node is full
						Split(temp, key);
					} else {
						// child node is not full
						temp.Line[temp.count++] = temp2;
						temp.sort(temp.count);
					}
				}
			}
			total_count++;
		}
	}

	public void Delete(int key) {
		if (Search(key, 0) == null) {
			System.out.println(key + " is not in this B-Tree");
		} else {
			Node Del_Key_Node = Search(key, 0);
			Node r;
			int Del_index = 0;
			for (int i = 0; i < Del_Key_Node.count; i++) {
				if (Del_Key_Node.Line[i].data == key) {
					Del_index = i;
					break;
				}
			} // Find index of deleted key in that's Node
			if (!Del_Key_Node.NullPointerCheck()) {
				r = SwapKey(Del_Key_Node, Del_index);
				for (int i = 1; i < r.count; i++) {
					r.Line[i - 1] = r.Line[i];
				}
			} // In not leaf Node, Swap Key and Rearrange
			else {
				r = Del_Key_Node;
				for (int i = Del_index; i < r.count - 1; i++) {
					r.Line[i] = r.Line[i + 1];
				}
			} // In leaf Node, Delete and Rearrange
			r.Line[r.count - 1] = new Key();
			r.EqualPointer();
			r.count--;
			if (r != null && r.count < SIZE / 2 && r.parent != null) {
				int i, index = -1;
				for (i = 0; i < r.parent.count; i++) {
					if (r.parent.Line[i].SmallChild != null && r.parent.Line[i].SmallChild == r) {
						index = i;
						break;
					}
				}
				if (i != 0 && i == r.parent.count && r.parent.Line[i - 1].LargeChild != null
						&& r.parent.Line[i - 1].LargeChild == r) {
					index = i;
				}
				// Find index of r in r.parent
				ClearUnderFlow(r.parent, index);
				// After deleting, if r.parent is underflow , ClearUnderflow
			} else if (r.count == 0 && r == root) {
				root = null;
			}
			total_count--;
			System.out.println(key + " is removed in B-Tree.");
		}
	}

	private void ClearUnderFlow(Node temp, int index) {
		if (!BorrowKey(temp, index)) {
			temp = MergeNode(temp, index);
		}
		if (temp != null && temp.count < SIZE / 2 && temp.parent != null) {
			int i, index2 = -1;
			for (i = 0; i < temp.parent.count; i++) {
				if (temp.parent.Line[i].SmallChild != null && temp.parent.Line[i].SmallChild == temp) {
					index2 = i;
					break;
				}
			}
			if (i != 0 && i == temp.parent.count && temp.parent.Line[i - 1].LargeChild != null
					&& temp.parent.Line[i - 1].LargeChild == temp) {
				index2 = i;
			}
			// Find index of temp in temp.parent
			ClearUnderFlow(temp.parent, index2);
			// After BorrowKey or MergeNode, if temp.parent is underflow ,
			// ClearUnderflow
		}
	}

	private Node MergeNode(Node temp, int index) {
		if (index == (temp.count)) {
			index -= 1;
		}
		Node left = temp.Line[index].SmallChild;
		Node right = temp.Line[index].LargeChild;
		left.Line[left.count] = new Key(temp.Line[index].data, right.Line[0].SmallChild,
				left.Line[left.count - 1].LargeChild);
		left.count++;
		// copy parent key to left Node
		for (int i = index; i < temp.count - 1; i++) {
			temp.Line[i] = temp.Line[i + 1];
		}
		for (int i = 0; i < right.count; i++) {
			left.Line[left.count++] = right.Line[i];
		}
		// copy right key to left Node
		temp.Line[temp.count - 1] = new Key();
		temp.count--;
		left.EqualPointer();
		right = null;
		if (temp.count == 0) {
			temp = null;
			root = left;
		} else {
			temp.Line[index].SmallChild = left;
			if (index != 0) {
				temp.Line[index - 1].LargeChild = left;
			}
			temp.EqualPointer();
			left.parent = temp;
		}
		return temp;
	}

	private boolean BorrowKey(Node temp, int index) {
		int check = 0;
		Node Send_Node = (index == temp.count) ? temp.Line[temp.count - 1].SmallChild : temp.Line[index].LargeChild;
		Node Receive_Node = (index == temp.count) ? temp.Line[temp.count - 1].LargeChild : temp.Line[index].SmallChild;
		// index 0 ~ 4 => 0 ~ 3 Smallchild is Recevie_Node / temp.count + 1
		// LargeChild is Receive_Node
		if ((index != temp.count && (index == 0 || index == 3)) && Send_Node.count <= SIZE / 2) {
			return false;
		} // if Send_Node does not have enough key
		else if (index != temp.count && (index == 1 || index == 2) && Send_Node.count <= SIZE / 2) {
			Send_Node = temp.Line[index - 1].SmallChild;
			check = 1;
			// Send_Node is changed to temp.Lind[index - 1].LargeChild
			if (Send_Node.count <= SIZE / 2) {
				return false;
			} // if Send_Node does not have enough key
		}
		if (index == (temp.count)) {
			Receive_Node.Line[1] = Receive_Node.Line[0];
			Receive_Node.Line[0] = new Key(temp.Line[temp.count - 1].data, Receive_Node.Line[1].SmallChild,
					Send_Node.Line[Send_Node.count - 1].LargeChild);
			temp.Line[temp.count - 1].data = Send_Node.Line[Send_Node.count - 1].data;
			Send_Node.Line[Send_Node.count - 1] = new Key();
			temp.Line[temp.count - 1].SmallChild = Send_Node;
			temp.Line[temp.count - 1].LargeChild = Receive_Node;
		} // Borrow key From left Node
		else if (check == 1) {
			for (int i = Receive_Node.count - 1; i >= 0; i--) {
				Receive_Node.Line[i + 1] = Receive_Node.Line[i];
			}
			Receive_Node.Line[0] = new Key(temp.Line[index - 1].data, Receive_Node.Line[1].SmallChild,
					Send_Node.Line[Send_Node.count - 1].LargeChild);
			temp.Line[index - 1].data = Send_Node.Line[Send_Node.count - 1].data;
			Send_Node.Line[Send_Node.count - 1] = new Key();
			temp.Line[index - 1].LargeChild = Receive_Node;
			temp.Line[index - 1].SmallChild = Send_Node;
		} // Borrow key from left Node - when right Node Size is less than 2 
		else {
			Receive_Node.Line[Receive_Node.count] = new Key(temp.Line[index].data, Send_Node.Line[0].SmallChild,
					Receive_Node.Line[Receive_Node.count - 1].LargeChild);
			temp.Line[index].data = Send_Node.Line[0].data;
			for (int i = 1; i < Send_Node.count; i++) {
				Send_Node.Line[i - 1] = Send_Node.Line[i];
			}
			Send_Node.Line[Send_Node.count - 1] = new Key();
			temp.Line[index].LargeChild = Send_Node;
			temp.Line[index].SmallChild = Receive_Node;
		} // Borrow key From right Node
		Receive_Node.EqualPointer();
		Send_Node.EqualPointer();
		Send_Node.count--;
		Receive_Node.count++;
		Send_Node.parent = temp;
		Receive_Node.parent = temp;
		return true;
	}

	private Node SwapKey(Node temp, int index) {
		Node RightNode = temp;
		Node LeftNode_of_RightNode = RightNode.Line[index].LargeChild;
		while (LeftNode_of_RightNode.Line[0].SmallChild != null) {
			RightNode = LeftNode_of_RightNode;
			LeftNode_of_RightNode = LeftNode_of_RightNode.Line[0].SmallChild;
		} // Find the smallest key in Right Node
		temp.Line[index].data = LeftNode_of_RightNode.Line[0].data;
		return LeftNode_of_RightNode;
	}

	public Node Search(int key, int mode) {
		// mode 0 -> no print search path , mode 1 -> print search path
		if (root == null) {
			return null;
		} else {
			int height = 1;
			Node temp = root;
			while (temp != null) {
				if (temp.Line[0].data > key) {
					if (mode == 1) {
						SearchPath(temp, height++, key);
					}
					temp = temp.Line[0].SmallChild;
				} else if (temp.Line[temp.count - 1].data != -1 && temp.Line[temp.count - 1].data < key) {
					if (mode == 1) {
						SearchPath(temp, height++, key);
					}
					temp = temp.Line[temp.count - 1].LargeChild;
				} else {
					int check = 1;
					for (int i = 0; i < temp.count; i++) {
						if (mode == 1 && check == 1) {
							check = 0;
							SearchPath(temp, height++, key);
						}
						if (temp.Line[i].data == key) {
							return temp;
						}
						if (temp.Line[i + 1].data > key && temp.Line[i].data < key) {
							temp = temp.Line[i].LargeChild;
							break;
						}
					}
				}
			}
		}
		return null;
	}

	public void SearchPath(Node temp, int height, int key) {
		for (int i = 1; i < height; i++) {
			System.out.printf("%5c", ' ');
		}
		System.out.printf("[%d]", height);
		for (int i = 0; i < SIZE; i++) {
			if (temp.Line[i].data == key) {
				System.out.printf("%3c%d", '*', temp.Line[i].data);
				// add '*' string to key that be wanted to find
			} else if (temp.Line[i].data != -1) {
				System.out.printf("%5d", temp.Line[i].data);
			}
		}
		System.out.println();
	}

	public void Show() {
		System.out.println("< B-Tree >");
		show_B_Tree(root, 1);
	}

	private void show_B_Tree(Node temp, int height) {
		if (temp != null) {
			for (int i = 1; i < height; i++) {
				System.out.printf("%5c", ' ');
			}
			System.out.printf("[%d]", height);
			for (int i = 0; i < temp.count; i++) {
				System.out.printf("%5d", temp.Line[i].data);
			}
			System.out.println();
			for (int i = 0; i < temp.count; i++) {
				show_B_Tree(temp.Line[i].SmallChild, height + 1);
			}
			show_B_Tree(temp.Line[temp.count - 1].LargeChild, height + 1);
			// recursive function call to print Child_Node with hegiht parameter
			// to plus 1
		}
	}

}

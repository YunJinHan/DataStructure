//자료구조 실습 과제 
//AVL Search Tree
//학번 : 2012036901
//이름 : 윤진한

public class AVLTree {
	private boolean search = false;
	private Node root = null;

	private class Node {
		private int key;
		private int balance;
		private int lchildheight;
		private int rchildheight;
		private Node parent;
		private Node lchild;
		private Node rchild;

		Node(int key, Node parent) {
			this.key = key;
			this.parent = parent;
			lchild = null;
			rchild = null;
		}
	}

	private boolean empty() {
		return root == null;
	}

	public void insert(int key) {
		search = false;
		if (!search(key)) {
			if (empty()) {
				root = new Node(key, null);
			} else {
				Node temp = root;
				while (key > temp.key && temp.rchild != null || key < temp.key && temp.lchild != null) {
					if (key > temp.key) {
						temp = temp.rchild;
					} else {
						temp = temp.lchild;
					}
				}
				if (key > temp.key) {
					temp.rchild = new Node(key, temp);
				} else {
					temp.lchild = new Node(key, temp);
				}
				rebalance(temp); // Insert 시 균형을 매번 다시 맞추어 재배열 해 준다.
			}
		} else {
			System.out.println("동일한 key 값이 있어 insert 할 수가 없습니다.");
		}
		search = true;
	}

	public void delete(int key) {
		search = false;
		if (!search(key)) {
			System.out.println("현재 BinaryTree 에 key 값이 없어 delete 할 수가 없습니다.");
		} else {
			Node parent = root;
			Node temp = root;
			boolean lchild = false;
			boolean rchild = false;
			while (key != temp.key) {
				parent = temp;
				if (key < temp.key) {
					lchild = true;
					rchild = false;
					temp = temp.lchild;
				} else if (key > temp.key) {
					rchild = true;
					lchild = false;
					temp = temp.rchild;
				}
			}
			// 삭제 할 Node와 그 부모노드를 찾는다.
			if (temp.rchild == null && temp.lchild == null) {
				if (temp == root) {
					root = null;
				} else if (lchild) {
					parent.lchild = null;
				} else if (rchild) {
					parent.rchild = null;
				}
			} // 삭제할 노드의 자식노드가 없을 경우.
			else if (temp.rchild == null) {
				if (temp == root) {
					root = temp.lchild;
				} else if (lchild) {
					parent.lchild = temp.lchild;
				} else if (rchild) {
					parent.rchild = temp.lchild;
				}
			} // 삭제할 노드의 자식노드가 한개만 있을 경우.
			else if (temp.lchild == null) {
				if (temp == root) {
					root = temp.rchild;
				} else if (lchild) {
					parent.lchild = temp.rchild;
				} else if (rchild) {
					parent.rchild = temp.rchild;
				}

			} // 삭제할 노드의 자식노드가 한개만 있을 경우.
			else if (temp.rchild != null && temp.lchild != null) {
				if ((temp.key - temp.rchild.key) > (temp.key - temp.lchild.key)) {
					Node Minimum = getMinimum(temp);
					if (temp == root) {
						root = Minimum;
					} else if (lchild) {
						parent.lchild = Minimum;
					} else if (rchild) {
						parent.rchild = Minimum;
					}
					Minimum.lchild = temp.lchild;
				} // subtree 의 오른쪽에서 가장 작은값을 가져오기.
				else if ((temp.key - temp.rchild.key) <= (temp.key - temp.lchild.key)) {
					Node Maximum = getMaximum(temp);
					if (temp == root) {
						root = Maximum;
					} else if (lchild) {
						parent.lchild = Maximum;
					} else if (rchild) {
						parent.rchild = Maximum;
					}
					Maximum.rchild = temp.rchild;
				} // subtree 의 왼쪽에서 가장 큰값을 가져오기.
			} // 삭제할 노드의 자식노드가 두개 다 있을 경우.
			rebalance(parent); // Delete 시 균형을 매번 다시 맞추어 재배열 해 준다.
		}
		search = true;
	}

	private Node getMinimum(Node temp) {
		Node Minimum = null;
		Node MinimumParent = null;
		Node current = temp.rchild;
		while (current != null) {
			MinimumParent = Minimum;
			Minimum = current;
			current = current.lchild;
		}
		if (Minimum != temp.rchild) {
			MinimumParent.lchild = Minimum.rchild;
			Minimum.rchild = temp.rchild;
		}
		return Minimum;
	}// subtree 에서 최소값 찾기.

	private Node getMaximum(Node temp) {
		Node Maximum = null;
		Node MaximumParent = null;
		Node current = temp.lchild;
		while (current != null) {
			MaximumParent = Maximum;
			Maximum = current;
			current = current.rchild;
		}
		if (Maximum != temp.lchild) {
			MaximumParent.rchild = Maximum.lchild;
			Maximum.lchild = temp.lchild;
		}
		return Maximum;
	}// subtree 에서 최댓값 찾기.

	public boolean search(int key) {
		if (empty()) {
			if (search != false) {
				System.out.println(key + "없음");
			}
			return false;
		} else {
			Node temp = root;
			while (key != temp.key) {
				if (temp.rchild == null && key > temp.key) {
					if (search != false) {
						System.out.println(key + "없음");
					}
					return false;
				} else if (temp.lchild == null && key < temp.key) {
					if (search != false) {
						System.out.println(key + "없음");
					}
					return false;
				}
				if (key > temp.key) {
					temp = temp.rchild;
				} else if (key < temp.key) {
					temp = temp.lchild;
				}
			}
			if (search != false) {
				System.out.println(key + "있음");
			}
			return true;
		}
	}
	
	private void setheight(Node temp){
		if (temp != null){
			temp.lchildheight = height(temp.lchild);
			temp.rchildheight = height(temp.rchild);
			setheight(temp.lchild);
			setheight(temp.rchild);
		}
	} // 각각 노드의 rchild 와 lchild 의 subtree height 를 설정해 준다.

	private int height(Node temp) {
		return (temp != null) ? 1+Math.max(height(temp.lchild),height(temp.rchild)) : 0;
	} // temp 노드의 height 를 리턴해 준다.

	private void setBalance(Node ... nodes) {
		for (Node n : nodes) {
			n.balance = height(n.rchild) - height(n.lchild);
		} // 파라미터로 들어오는 노드들의 각각 subtree 의 height 차이를 구해준다.
	}

	private void rebalance(Node temp) {
		setBalance(temp);
		if (temp.balance == -2) {
			if (height(temp.lchild.lchild) >= height(temp.lchild.rchild)) {
				temp = rotateLL(temp);
			} // LL Case 일때. 
			else {
				temp = rotateLR(temp);
			} // LR Case 일때.
		} // LL - LR Case 서브트리의 왼쪽 높이가 오른쪽 높이 보다 2 클때.  
		else if (temp.balance == 2) {
			if (height(temp.rchild.rchild) >= height(temp.rchild.lchild)) {
				temp = rotateRR(temp);
			} // RR Case 일때.
			else {
				temp = rotateRL(temp);
			} // RL Case 일때.
		} // RR - RL Case 서브트리의 오른쪽 높이가 왼쪽 높이 보다 2 클때.
		// 재배열할 노드를 찾은 후 다시 배열을 한다.
		if (temp.parent != null) {
			rebalance(temp.parent);
		}
		else {
			root = temp;
		}
		setheight(root); // 각각 Node의 subtree height 높이를 지정해준다.
	}
	
	private Node rotateLL(Node temp) {
		Node temp2 = temp.lchild;
		temp2.parent = temp.parent;
		temp.lchild = temp2.rchild;
		if (temp.lchild != null) {
			temp.lchild.parent = temp;
		}
		temp2.rchild = temp;
		temp.parent = temp2;
		if (temp2.parent != null){
			if (temp2.parent.rchild == temp) {
				temp2.parent.rchild = temp2;
			} else {
				temp2.parent.lchild = temp2;
			}
		}
		setBalance(temp,temp2); // 재배열 후 다시 balance 를 구해준다.
		return temp2;
	}

	private Node rotateRR(Node temp) {
		Node temp2 = temp.rchild;
		temp2.parent = temp.parent;
		temp.rchild = temp2.lchild;
		if (temp.rchild != null) {
			temp.rchild.parent = temp;
		}
		temp2.lchild = temp;
		temp.parent = temp2;
		if (temp2.parent != null) {
			if (temp2.parent.rchild == temp) {
				temp2.parent.rchild = temp2;
			} else {
				temp2.parent.lchild = temp2;
			}
		}
		setBalance(temp,temp2); // 재배열 후 다시 balance 를 구해준다.
		return temp2;
	}

	private Node rotateLR(Node temp) {
		temp.lchild = rotateRR(temp.lchild);
		return rotateLL(temp);
	}

	private Node rotateRL(Node temp) {
		temp.rchild = rotateLL(temp.rchild);
		return rotateRR(temp);
	}

	void print_preorder() {
		Node temp = root;
		System.out.print("Preorder : ");
		print_preorder(temp);
		System.out.println();
	}

	void print_preorder(Node temp) {
		if (temp != null) {
			System.out.print(temp.key + " ");
			print_preorder(temp.lchild);
			print_preorder(temp.rchild);
		}
	}

	void print_inorder() {
		Node temp = root;
		System.out.print("Inorder : ");
		print_inorder(temp);
		System.out.println();
	}

	void print_inorder(Node temp) {
		if (temp != null) {
			print_inorder(temp.lchild);
			System.out.print(temp.key + " ");
			print_inorder(temp.rchild);
		}
	}

	void print_postorder() {
		Node temp = root;
		System.out.print("Postorder : ");
		print_postorder(temp);
		System.out.println();
	}

	void print_postorder(Node temp) {
		if (temp != null) {
			print_postorder(temp.lchild);
			print_preorder(temp.rchild);
			System.out.print(temp.key + " ");
		}
	}

	void print_preorder_diff() {
		System.out.println();
		Node temp = root;
		System.out.print("Diff Preorder : ");
		print_preorder_diff(temp);
		System.out.println("\n");
	}

	void print_preorder_diff(Node temp) {
		if (temp != null) {
			System.out.print(temp.key + "(" + temp.lchildheight + "," + temp.rchildheight + ") ");
			print_preorder_diff(temp.lchild);
			print_preorder_diff(temp.rchild);
		}
	}
}

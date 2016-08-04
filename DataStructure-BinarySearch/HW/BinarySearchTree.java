//자료구조 실습 과제 
//Binary Search Tree
//학번 : 2012036901
//이름 : 윤진한

public class BinarySearchTree {
	private boolean search = false;
	private Node root = null;

	private class Node {
		private int key;
		private Node lchild;
		private Node rchild;

		Node(int key) {
			this.key = key;
			lchild = null;
			rchild = null;
		}
	}
	
	public Node getRoot() {
		return root;
	}

	boolean empty() {
		return root == null;
	}
	
	void insert(int key) {
		search = false;
		if (search(key)==null){
			if (empty()) {
				root = new Node(key);} 
			else {
				Node temp = root;
				while (key > temp.key && temp.rchild != null || key < temp.key && temp.lchild != null) {
					if (key > temp.key) {
						temp = temp.rchild;
					} 
					else {
						temp = temp.lchild;
					}
				}
				if (key > temp.key) {
					temp.rchild = new Node(key);
				} 
				else {
					temp.lchild = new Node(key);
				}
			}
		}
		else {
			System.out.println("동일한 key 값이 있어 insert 할 수가 없습니다.");
		}
		search = true;
	}

	void delete(int key) {
		search = false;
		if (search(key)==null){
			System.out.println("현재 BinaryTree 에 key 값이 없어 delete 할 수가 없습니다.");
		}
		else {
			Node parent = root;
			Node temp = root;
			boolean lchild = false;
			boolean rchild = false;
			while(key != temp.key){
				parent = temp;
				if(key < temp.key){
					lchild = true;
					rchild = false;
					temp = temp.lchild;
				}else if (key > temp.key){
					rchild = true;
					lchild = false;
					temp = temp.rchild;
				}
			}
			// 삭제 할 Node와 그 부모노드를 찾는다. 
			if (temp.rchild == null && temp.lchild == null){
				if (temp == root){
					root = null;
				}
				else if (lchild){
					parent.lchild = null;
				}
				else if (rchild){
					parent.rchild = null;
				}
			}// 삭제할 노드의 자식노드가 없을 경우.
			else if (temp.rchild == null){
				if (temp == root){
					root = temp.lchild;
				}
				else if (lchild){
					parent.lchild = temp.lchild;
				}
				else if (rchild){
					parent.rchild = temp.lchild;
				}
			}// 삭제할 노드의 자식노드가 한개만 있을 경우.
			else if (temp.lchild == null){
				if (temp == root){
					root = temp.rchild;
				}
				else if (lchild){
					parent.lchild = temp.rchild;
				}
				else if (rchild){
					parent.rchild = temp.rchild;
				}
				
			}// 삭제할 노드의 자식노드가 한개만 있을 경우.
			else if (temp.rchild != null && temp.lchild != null){
				if ((temp.key - temp.rchild.key) > (temp.key - temp.lchild.key)){
					Node Minimum = getMinimum(temp);
					if(temp == root){
						root = Minimum;
					}
					else if (lchild){
						parent.lchild = Minimum;
					}
					else if (rchild){
						parent.rchild = Minimum;
					}			
					Minimum.lchild = temp.lchild;
				} // subtree 의 오른쪽에서 가장 작은값을 가져오기.
				else if ((temp.key - temp.rchild.key) <= (temp.key - temp.lchild.key)){
					Node Maximum = getMaximum(temp);
					if (temp == root){
						root = Maximum;
					}
					else if (lchild){
						parent.lchild = Maximum;
					}
					else if (rchild){
						parent.rchild = Maximum;
					}
					Maximum.rchild = temp.rchild;
				} // subtree 의 왼쪽에서 가장 큰값을 가져오기.
			}// 삭제할 노드의 자식노드가 두개 다 있을 경우.
			
		}
		search = true;
	}
	
	Node getMinimum(Node temp){
		Node Minimum = null;
		Node MinimumParent = null;
		Node current = temp.rchild;
		while (current != null){
			MinimumParent = Minimum;
			Minimum = current;
			current = current.lchild;
		}
		if(Minimum != temp.rchild){
			MinimumParent.lchild = Minimum.rchild;
			Minimum.rchild = temp.rchild;
		}
		return Minimum;
	}// subtree 에서 최소값 찾기.
	
	Node getMaximum(Node temp){
		Node Maximum = null;
		Node MaximumParent = null;
		Node current = temp.lchild;
		while (current != null){
			MaximumParent = Maximum;
			Maximum = current;
			current = current.rchild;
		}
		if (Maximum != temp.lchild){
			MaximumParent.rchild = Maximum.lchild;
			Maximum.lchild = temp.lchild;
		}
		return Maximum;
	}// subtree 에서 최댓값 찾기.

	Node search(int key) {
		if (empty()){
			if (search != false){
				System.out.println(key+"없음");
			}
			return null;
		}
		else{
			Node temp = root;
			while (key != temp.key){
				if (temp.rchild == null && key > temp.key){
					if (search != false){
						System.out.println(key+"없음");	
					}
					return null;
				}
				else if (temp.lchild == null && key < temp.key){
					if (search != false){
						System.out.println(key+"없음");
					}
					return null;
				}
				if (key > temp.key){
					temp = temp.rchild;
				}
				else if (key < temp.key){
					temp = temp.lchild;
				}
			}
			if (search != false){
				System.out.println(key+"있음");
			}
			return temp;
		}
	}

	void print_preorder(){
		Node temp = root;
		System.out.print("Preorder : ");
		print_preorder(temp);
		System.out.println();
	}
	
	void print_preorder(Node temp) {
		if (temp != null){
			System.out.print(temp.key+" ");
			print_preorder(temp.lchild);
			print_preorder(temp.rchild);	
		}
		else {
			System.out.print("");
		}
	}
	
	void print_inorder(){
		Node temp = root;
		System.out.print("Inorder : ");
		print_inorder(temp);
		System.out.println();
	}

	void print_inorder(Node temp) {
		if (temp != null){
			print_inorder(temp.lchild);
			System.out.print(temp.key+" ");
			print_inorder(temp.rchild);
		}
		else {
			System.out.print("");
		}
	}
	
	void print_postorder(){
		Node temp = root;
		System.out.print("Postorder : ");
		print_postorder(temp);
		System.out.println();
	}

	void print_postorder(Node temp) {
		if (temp != null){
			print_postorder(temp.lchild);
			print_preorder(temp.rchild);
			System.out.print(temp.key+" ");
		}
		else {
			System.out.print("");
		}
	}
}

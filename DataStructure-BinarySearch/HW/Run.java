//자료구조 실습 과제 
//Binary Search Tree
//학번 : 2012036901
//이름 : 윤진한

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree a = new BinarySearchTree();
		int num [] = {5,3,2,1,4,8,6,7,10,9};
		for (int i=0;i<num.length;i++){
			a.insert(num[i]);
		}
		a.search(7);
		a.print_preorder();
		a.print_postorder();
		a.print_inorder();
		a.delete(8);
		a.delete(5);
		a.delete(2);
		a.search(8);
		a.print_preorder();
	}

}

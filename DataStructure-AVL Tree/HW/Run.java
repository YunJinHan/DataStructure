//자료구조 실습 과제 
//AVL Search Tree
//학번 : 2012036901
//이름 : 윤진한

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree a = new AVLTree();
		int num[] = {10,15,20,25,23,5,1,18,19};
		//int num[] = {10,15,20,25};
		for (int i = 0; i < num.length; i++) {
			a.insert(num[i]);
		}
		a.print_preorder();
		
		a.print_preorder_diff(); 
		System.out.println("1,10 Delete");
		a.delete(1); a.delete(10);
		a.print_preorder_diff(); 
		System.out.println("5 Delete 30 Insert 18 Delete");
		a.delete(5); a.insert(30); a.delete(18);
		a.print_preorder_diff();
		

	}

}

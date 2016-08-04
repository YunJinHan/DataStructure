
public class Run_BST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST a = new BST();
		int num [] = {5,3,2,1,4,8,6,7,10,9};
		for (int i=0;i<num.length;i++){
			a.insert(num[i]);
		}
		a.preorder();
		a.postorder();
		a.inorder();
		System.out.println("8 delete");
		a.delete(8);
		a.preorder();
		System.out.println("5 delete");
		a.delete(5);
		a.preorder();
		System.out.println("2 delete");
		a.delete(2);
		a.preorder();
	}

}


public class Run_AVL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVL a = new AVL();
		int num[] = {10,15,20,25,23,5,1,18,19};
		//int num[] = {10,15,20,25};
		for (int i = 0; i < num.length; i++) {
			a.insert(num[i]);
		}
		a.preorder();
		
		a.preorder_diff(); 
		System.out.println("1,10 Delete");
		a.delete(1);
		a.delete(10);
		a.preorder_diff(); 
		System.out.println("5 Delete 30 Insert 18 Delete");
		a.delete(5); 
		a.insert(30); 
		a.delete(18);
		a.preorder_diff();
	}
}

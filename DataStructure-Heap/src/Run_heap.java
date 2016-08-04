
public class Run_heap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		heap a = new heap();
		
		int num [] = {1,2,3,4,5,6,7,8,9};
		
		for (int i=0;i<num.length;i++){
			a.insert(num[i]);
		}
		
		a.print_preorder();
	}

}

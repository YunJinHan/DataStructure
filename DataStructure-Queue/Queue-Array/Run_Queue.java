
public class Run_Queue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Queue_Array CQ = new Queue_Array(100);
		CQ.dequeue();
		CQ.print();
		System.out.println("size : " + CQ.length());
		for (int i=1; i<15; i++)
			CQ.enqueue(i);
		CQ.print();
		System.out.println("size : " + CQ.length());
		for (int i=0; i<5; i++){
			CQ.dequeue();
		}
		CQ.print();
		for (int i=10; i<16; i++){
			CQ.dequeue();
		}
		
		System.out.println('\n');
		CQ.print();
		System.out.println('\n');
		
		for (int j=0; j<15; j++){
			for (int i=j+1; i<j+17; i++)
				CQ.enqueue(i);
			CQ.print();
			for (int i=3; i<15; i++)
				CQ.dequeue();
			CQ.print();
		}
		
		System.out.println("???");
			
	}
}

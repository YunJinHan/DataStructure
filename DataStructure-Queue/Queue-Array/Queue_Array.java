
public class Queue_Array {
	
	int r = 0;
	int f = 0;
	int size;
	int array[];
	
	Queue_Array(int size){
		this.size = size;
		array = new int[size];
		for (int e : array){
			e = 0;
		}
		r=2;
		f=2;
	}
	
	
	boolean isEmpty(){
		return r==f;
	}
	
	boolean isFull(){
		return (r+1) % size == f;
	}
	
	int length(){
		return (size-f+r) % size ;
	}
	
	void dequeue(){
		if (isEmpty()){
			System.out.println("Queue is Empty");
		}
		else {
			array[f] = 0;
			f = (f+1) % size;			
		}
	}
	
	void enqueue(int data){
		if (isFull()){
			System.out.println("Queue is Full");
		}
		else {
			array[r] = data;
			r = (r+1) % size;
		}
	}
	
	public void prn_q(){
		int i;
		if (isEmpty())
			System.out.println("Queue is Empty");
		else{
			if (f>r){
				for (i=f; i<size-1; i++){
					System.out.print(array[i]+" ");
				}
				for (i=0; i<r; i++){
					System.out.print(array[i]+" ");
				}
			}
			else{
				for (i=f; i<r; i++){
					System.out.print(array[i]+" ");
				}				
			}
			System.out.println();
		}
	}
	
	
	void print(){
		if (isEmpty()){
			System.out.println("Emtpy");
		}
		else {
			int length = size;
			int p = f;
			while (length != 0){
				if (p==size){
					p=0;
				}
				if (array[p] == 0){
					break;
				}
				System.out.print(array[p]+" ");
				p++;
				length--;
			}
		}
		System.out.println();
	}

}

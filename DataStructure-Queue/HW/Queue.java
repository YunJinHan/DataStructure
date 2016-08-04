//자료구조 실습 과제 
//Stack & Queue
//학번 : 2012036901
//이름 : 윤진한 

public class Queue {
	
	private Node first; // dequeue 시 node 가 나오는 곳
	private Node rear; // enqueue 시 node 가 들어가는 곳
	
	public class Node{
		private char data;
		private Node next;
		
		Node(char data){
			this.data = data;
		}
	}

	boolean empty(){
		return first == null && rear == null;
	}
	
	void Enqueue(char data){
		if (empty()){
			first = rear = new Node(data);
			// 큐가 비어있을때
		}
		else {
			Node newNode = new Node(data);
			rear.next = newNode;
			rear = newNode;// 큐가 비어있지 않을때
		}
	}
	
	char Dequeue(){
		if (empty()){
			return '$'; // 큐가 비어있을때 '$' 표시를 리턴시켜주었습니다.
		}
		else if (first == rear){
			Node temp = first;
			first = rear = null;
			return temp.data;// 큐에 한개의 원소만 있을때
		}
		else{
			Node temp = first;
			first = first.next;
			return temp.data;// 그외의 경우일때.
		}
		
	}
	
	void printQueue(){
		if (empty()){
			System.out.println("Queue is Empty");
		}
		else {
			String show = " [ ";
			Node temp = first;
			while (temp != rear){
				show += temp.data + ", ";
				temp=temp.next;
			}
			show += rear.data + " ]";
			System.out.println(show);
		}
	}
	
	void ResetQueue(){
		first = rear = null;
	} // 큐 초기화
}

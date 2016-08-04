//자료구조 실습 과제 
//Stack & Queue
//학번 : 2012036901
//이름 : 윤진한 

public class Stack {

	private Node top;

	public class Node {
		private char data;
		private Node next;

		Node(char data) {
			next = null;
			this.data = data;
		}
		
		char getData(){
			return data;
		}// data 를 보기위한 getter 메소드.
	}
	
	public Node getTop(){
		return top;
	}// stack 의 top 를 보기위한 getter 메소드.

	void push(char data) {
		if (empty()) {
			top = new Node(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = top;
			top = newNode;
		}
	}

	char pop() {
		if (empty()) {
			return '$';
		} else {
			Node temp = top;
			top = top.next;
			return temp.data;
		}

	}

	boolean empty() {
		return top == null;
	}

	void printStack() {
		if (empty()) {
			System.out.println("비어있음");
		} else {
			Node temp = top;
			String Stack = "[ ";
			while (temp.next != null) {
				Stack += temp.data + " , ";
				temp = temp.next;
			}
			Stack += temp.data + " ]";
			System.out.println(Stack);
		}
	}
	
	void ResetStack(){
		top = null;
	}
}

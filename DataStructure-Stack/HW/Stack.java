//자료구조 실습 과제 
//Stack
//학번 : 2012036901
//이름 : 윤진한 

public class Stack {

	private Node top;

	private class Node {
		private String data;
		private Node next;

		Node(String data) {
			next = null;
			this.data = data;
		}
	}

	void push(String data) {
		if (empty()) {
			top = new Node(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = top;
			top = newNode;
		}
	}

	String pop() {
		if (empty()) {
			return "비어있음";
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
}

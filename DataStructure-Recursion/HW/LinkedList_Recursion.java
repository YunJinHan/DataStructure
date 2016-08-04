//자료구조 실습 과제 
//LinkedList_Recursion
//학번 : 2012036901
//이름 : 윤진한 

public class LinkedList_Recursion {

	private Node head;
	private Node tail; // 링크리스트의 마지막 노드. 
	private int count = 0;

	private class Node {
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}

	void recursive_insert(int size){
		if (size==1){
			insert(0,0);
		}
		else{
			insert(0,5*(size-1));
			recursive_insert(size-1);
		}
	}//Recursion - insert 메소드 
	
	void recursive_delete(int size){
		if (size==1){
			delete(count-1);
		}
		else {
			delete(count-1);
			recursive_delete(size-1);
		}
	}//Recursion - delete 메소드 
	
	void insert(int position, int input) {
		Node newNode = new Node(input);
		if (count == 0) {
			head = tail = newNode;
		}// 리스트가 비어있을때 노드가 추가될때.
		else if (position == 0) {
			newNode.next = head;
			head = newNode;
		}// 리스트가 비어있지 않고 노드가 맨앞에 추가될때.
		else if (position == count) {
			tail.next = newNode;
			tail = newNode;
		}// 노드가 맨 마지막에 추가 될떄.
		else {
			int i = position - 1;
			Node a = head;
			while (i-- != 0) {
				a = a.next;
			}// 추될 자리 이전의 노드를 찾음.
			Node temp = a.next;
			a.next = newNode;
			newNode.next = temp;
		}// position 에 있는 노드의 연결을 끊고 새로운 노드를 이전 노드와 position 노드에 연결한다.
		count++;
	}

	void delete(int position) {
		int i = position - 1;
		Node a = head;
		while (i-- != 0) {
			a = a.next;
		}// 삭제될 자리 이전의 노드를 찾음.
		Node temp = a.next;
		a.next = temp.next;
		count--;
	}// position 에 있는 노드의 연결들을 끊고 이전 노드와 다음 노드를 연결시킴.

	void print_list() {
		String str = "[ ";
		if (count == 0) {
			str += " ]";
		}// 링크리스트 길이가 0일떄.
		else {
			Node a = head;
			while (a.next != null) {
				str += String.valueOf(a.data) + ", ";
				a = a.next;
			}
			str += String.valueOf(a.data) + " ]";
		}// 링크리스트 길이가 0이 아닐때.
		System.out.println(str);
	}

	void get_data(int position) {
		int index = position;
		Node a = head;
		while (position-- != 0) {
			a = a.next;
		}// 해당 position 의 a 를 찾음.
		System.out.println(index + "번째의 data 값 : " + a.data);
	}
}
//자료구조 실습 과제 
//DCLinkedList
//학번 : 2012036901
//이름 : 윤진한

public class DCLinkedList {

	private Node head;
	private Node tail;// 링크리스트의 마지막 노드.
	public int count;

	private class Node {
		int data;
		Node next;
		Node prev;

		Node(int input) {
			data = input;
			next = null;
			prev = null;
		}
	}

	void insert(int position, int input) {
		Node newNode = new Node(input);
		if (count == 0) {
			head = newNode;
			tail = newNode;
			newNode.next = newNode;
			newNode.prev = newNode;
		} // 리스트에 아무것도 없을때.
		else if (position == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			tail.next = head;
			head.prev = tail;
		} // 리스트의 맨 처음에 노드가 추가 될때.
		else if (position == count) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			head.prev = tail;
			tail.next = head;
		} // 리스트의 맨 마지막에 노드가 추가 될떄.
		else {
			int index = position - 1;
			Node a = head;
			if (index >= count / 2) {
				for (int i = count ; i > index; i--) {
					a=a.prev;
				}
			} else {
				while (index-- != 0) {
					a = a.next;
				}
			}
			Node temp = a.next;
			temp.prev = newNode;
			newNode.next = temp;
			a.next = newNode;
			newNode.prev = a;
		}
		count++;
	}

	void delete(int position) {
		if (count == 1) {
			head = null;
			tail = null;
		} // 리스트에 한개의 노드만이 있을때.
		else if (position == 0) {
			tail.next = head.next;
			head = head.next;
			head.prev = tail;
		} // 맨 처음의 노드를 삭제 할떄.
		else if (position == count) {
			tail = tail.prev;
			tail.next = head;
			head.next = tail;
		} // 맨 마지막의 노드를 삭제 할떄.
		else {
			int index = position - 1;
			Node a = head;
			if (index >= count / 2) {
				for (int i = count ; i > index; i--) {
					a=a.prev;
				}
			} else {
				while (index-- != 0) {
					a = a.next;
				}
			}
			a.next = a.next.next;
			a.next.prev = a;
		}
		count--;
	}

	void print_list_forward(int position) {
		String str = "[ ";
		int index = position;
		int index2 = count;
		Node a = head;
		while (index-- != 0) {
			a = a.next;
		} // 출력을 시작할 첫번째 노드를 찾는다.
		while (true) {
			if (index2 == 1)
				break;
			str += String.valueOf(a.data) + ",";
			a = a.next;
			index2--;
		} // 첫번째 노드로 부터 순서대로 노드의 데이터 값을 String 의 Str 로 더한다.
		str += String.valueOf(a.data) + " ]";
		System.out.println(str);
	}

	void print_list_backward(int position) {
		String str = "[ ";
		int index = position;
		int index2 = count;
		Node a = head;
		while (index-- != 0) {
			a = a.next;
		} // 출력을 시작할 첫번째 노드를 찾는다.
		while (true) {
			if (index2 == 1)
				break;
			str += String.valueOf(a.data) + ",";
			a = a.prev;
			index2--;
		} // 첫번째 노드로 부터 역순으로 노드의 데이터 값을 String 의 Str 로 더한다.
		str += String.valueOf(a.data) + " ]";
		System.out.println(str);
	}

	void get_data(int position) {
		Node a = head;
		if (position >= count / 2) {
			for (int i = count ; i > position; i--) {
				a=a.prev;
			}
		} else {
			while (position-- != 0) {
				a = a.next;
			}
		}
		System.out.println(a.data);
	}
	
}

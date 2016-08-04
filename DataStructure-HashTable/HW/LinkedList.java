//자료구조 실습 과제 
//HashTable
//학번 : 2012036901
//이름 : 윤진한 

public class LinkedList {

	private Node head;
	private int count = 0;

	public class Node {
		String key;
		String value;
		Node next;

		Node(String key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}

	public Node getHead() {
		return head;
	}
	
	String search(String key,int index){
		Node temp = head;
		while (temp !=null){
			if (temp.key.equals(key)){
				return "["+index+","+temp.key+","+temp.value+ "] ";
			}
			temp = temp.next;
		}
		return null;
	}

	void insert(String key, String value) {
		Node newNode = new Node(key,value);
		if (count == 0) {
			head = newNode;
		} // 리스트가 비어있을때 노드가 추가될때.
		else {
			Node a = head;
			while (a.next != null) {
				a = a.next;
			} // 추될 자리 이전의 노드를 찾음.
			Node temp = a.next;
			a.next = newNode;
			newNode.next = temp;
		}
		count++;
	}

	void print_list(int index) {
		String str = "";
		if (count == 0) {
			str += "["+index+",null] ";
		} // 링크리스트 길이가 0일떄.
		else {
			Node a = head;
			while (a.next != null) {
				str += "["+index+","+a.key+","+a.value+ "] ";
				a = a.next;
			}
			str += "["+index+","+a.key+","+a.value+ "] ";
		} // 링크리스트 길이가 0이 아닐때.
		System.out.print(str);
	}
	
}

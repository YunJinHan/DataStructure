//자료구조 실습 과제 
//HashTable
//학번 : 2012036901
//이름 : 윤진한 

public class Hashtable {

	final int size = 6; // hashtable size
	LinkedList value[] = new LinkedList[size]; // size 만큼 hashtable 을 만들어줌.

	Hashtable() {
		for (int i = 0; i < size; i++) {
			value[i] = new LinkedList();
		} // 각각의 hashtable의 LinkdeList 초기화 시켜줌.
	}

	int hashFunction(String key) {
		return key.length() % size; 
		// key의 크기를 hashtable size로 나눠준 값을 리턴시켜줌.
	}

	String getValue(String key) {
		int index = hashFunction(key);
		if (value[index].getHead()==null){
			return "존재하지 않음!";
		} // 주어진 index 의 LinkedList 에 아무것도 없을 때.
		else {
			if(value[index].search(key,index)==null){
				return "존재하지 않음!";
			} // 주어진 index 의 LinkedList 에 key 값 없을 때.
			else {
				return value[index].search(key,index); 
			} // 주어진 index 의 LinkedList 에 key 값 을 때.
		}
		
	}

	void setValue(String key, String value) {
		this.value[hashFunction(key)].insert(key, value); 
		// hasFunction 으로부터 주어진 index값에 해당 key,value를 가지는 노드를 추가한다.
	}

	boolean hasValue(int index) {
		if (index>=size){
			return false;
		} // 주어진 index의 값이 hashtable의 크기보다 크면 false를 리턴한다.
		return value[index].getHead() != null; 
		// 해당 index의 링크드리스트가 비어있다면 어떠한 원소도 없는 것이므로 false를 리턴하고 그렇지 않으면 true 를 리턴한다.
	}

	void showAll() {
		for (int i = 0; i < size; i++) {
			value[i].print_list(i);
		} // LinkedList 의 print_list 메소드를 이용하여 index 값을 파라미터로 주며 출력해준다.
	}
}

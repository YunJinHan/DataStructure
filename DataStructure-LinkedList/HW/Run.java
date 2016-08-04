//자료구조 실습 과제 
//LinkedList - Run
//학번 : 2012036901
//이름 : 윤진한 

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list1 = new LinkedList();
		System.out.println("메소드에 삽입되는 노드의 순서 값은 0부터 시작한다고 정하였습니다.\n");
		list1.insert(0,23);
		System.out.println("0번째에 23 삽입 ");
		list1.print_list();
		list1.insert(0,77);
		System.out.println("0번째에 77 삽입 ");
		list1.print_list();
		list1.insert(1,44);
		System.out.println("1번째에 44 삽입 ");
		list1.print_list();
		list1.insert(2,55);
		System.out.println("2번째에 55 삽입 ");
		list1.print_list();
		list1.delete(1);
		System.out.println("1번째 값 삭제 ");
		list1.print_list();
		list1.get_data(1);
	}

}

//자료구조 실습 과제 
//LinkedList_Recursion - Run
//학번 : 2012036901
//이름 : 윤진한 

public class Run {

	public static void main(String[] args) {
		LinkedList_Recursion list1 = new LinkedList_Recursion();
		list1.recursive_insert(20);
		System.out.print("Recursive_insert Result : ");
		list1.print_list();
		list1.recursive_delete(15);
		System.out.print("Recursive_delete Result : ");
		list1.print_list();
	}
}

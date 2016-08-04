//자료구조 실습 과제 
//DCLinkedList - Run
//학번 : 2012036901
//이름 : 윤진한 

import java.math.*;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DCLinkedList list1 = new DCLinkedList();
		//TODO #3
		for (int i=0;i<50;i++){
			list1.insert(i,(int)(Math.random()*10)+1);
		}//Math.Random 메소드를 사용하여 50개의 값을 리스트에 넣음.
		System.out.println("메소드에 삽입되는 노드의 순서 값은 0 부터 시작한다고 하였습니다.\n");
		System.out.println("Head 부터 출력되는 리스트 ");
		list1.print_list_forward(0);
		System.out.println("30번쨰 요소부터 출력되는 리스트 ");
		list1.print_list_forward(30);
		System.out.println("30번째 요소부터 역순으로 출력되는 리스트 ");
		list1.print_list_backward(30);
		System.out.print("30번째 Data 값 : ");
		list1.get_data(30);
		list1.delete(30);
		System.out.println("30번째 요소가 삭제된 Head 부터 출력되는 리스트 ");
		list1.print_list_forward(0);
		list1.delete(2);
		System.out.println("2번째 요소가 삭제된 Head 부터 출력되는 리스트 ");
		list1.print_list_forward(0);
		System.out.println("15번째 요소부터 출력되는 리스트 ");
		list1.print_list_forward(15);
		System.out.println("15번째 요소부터 역순으로 출력되는 리스트 ");
		list1.print_list_backward(15);
		System.out.print("2번째 Data의 값 : ");
		list1.get_data(2);

	}
}

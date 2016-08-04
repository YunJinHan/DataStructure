//자료구조 실습 과제 
//Stack
//학번 : 2012036901
//이름 : 윤진한 

public class Run {
	public static void main(String[] args){
		Stack st1 = new Stack();
		System.out.println(st1.pop());
		st1.push("2");
		st1.push("4");
		st1.push("6");
		st1.push("8");
		st1.push("10");
		st1.printStack();
		System.out.println("첫번째 pop 된 값 : "+st1.pop());
		st1.printStack();
		System.out.println("두번째 pop 된 값 : "+st1.pop());
		st1.printStack();
		System.out.println("세번째 pop 된 값 : "+st1.pop());
		st1.printStack();
		st1.push("1");
		System.out.println("1 push");
		st1.printStack();
	}

}

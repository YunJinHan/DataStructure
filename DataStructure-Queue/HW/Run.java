//자료구조 실습 과제 
//Stack & Queue
//학번 : 2012036901
//이름 : 윤진한 

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Palindrome p1 = new Palindrome();
		String a1 = "((142+(2+(3-24)))+23)+(1414+(14-11))";
		String a2 = "(142+2(32)4+14((1))4+(1(4-11)";
		String a3 = "(142+2324)+1414)+14";
		String a4 = "(((((())))()()))((((((()))(())))(((())((((()))))))))";
		p1.bracketCheck(a1);
		p1.bracketCheck(a2);
		p1.bracketCheck(a3);
		p1.bracketCheck(a4);
		
	}

}
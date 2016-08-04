//자료구조 실습 과제 
//Stack & Queue
//학번 : 2012036901
//이름 : 윤진한 

public class Palindrome {

	Queue Que = new Queue();
	Stack Stk = new Stack();

	void bracketCheck(String words) {
		for (char e : words.toCharArray()) {
			Que.Enqueue(e);
		}// words를 char 타입으로 한개씩 끊어서 큐에 저장합니다.
		while (Que.empty() != true){ // 큐가 엠티가 될때까지 큐에 있는 모든 원소들을 Dequeue 합니다. 
			char a = Que.Dequeue();
			if (Stk.empty() == false && Stk.getTop().getData() == '(' && a == ')') {
				Stk.pop(); // 스택에 top 에 있는 원소와 현재 푸시하려는 원소가 서로 맞물린 상태 ('()') 라면 스택에 있는 원소를 팝 합니다.
			} 
			else if (a == '(' || a == ')') {
				Stk.push(a); // 그 외의 경우 괄호들만 스택에 푸시를 해줍니다.
			}
		}
		if (Stk.empty()){
			System.out.println(words +" => OK"); // 모든 실행이 완료된후 스택이 비어있다면 괄호의 상태가 다 맞는 경우이므로 ok를 출력합니다.
		}
		else {
			System.out.println(words +" => Error"); // 스택에 괄호가 남아있다면 상태가 맞지않는 경우이므로 error를 출력합니다.
		}
		Que.ResetQueue();
		Stk.ResetStack();
		// 모든 작업이 끝난후 스택과 큐를 초기화 시켜 다음 작업을 처음 상태에서 할수있도록 하였습니다.
	}
}

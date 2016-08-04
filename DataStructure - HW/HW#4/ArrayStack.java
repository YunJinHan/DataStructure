/*
 * CSE2010 Homework #4: ArrayStack.java
 * 
 * Modify this file as needed!
 * 
 * 학번 : 2012036901
 * 이름 : 윤진한
 * 컴퓨터공학과
 * 
 */
public class ArrayStack<T> implements Stack<T> {

	private int top = -1;
	private int size;
	private T[] stack;

	public ArrayStack(int size) {
		this.size = size;
		stack = (T[]) new Object[size];
	}

	public void push(T item) throws FullStackException {
		if (isEmpty()){
			stack[0]=item;
			top++;
		}
		else if (isFull()){
			System.out.println("stack is full");
			throw new FullStackException();
		}
		else {
			stack[top+1]=item;
			top++;
		}
	}

	public T pop() throws EmptyStackException {
		if (isEmpty()){
			System.out.println("stack is empty");
			throw new EmptyStackException();
		}
		else {
			T temp = stack[top];
			stack[top]=null;
			top--;
			return temp;
		}
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == size;
	}

	public T top() throws EmptyStackException {
		if (isEmpty()){
			System.out.println("stack is empty");
			throw new EmptyStackException();
		}
		else {
			return stack[top];
		}
	}

}
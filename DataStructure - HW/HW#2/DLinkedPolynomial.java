/*
 * (C) 2015 CSE2010 HW #2
 * 
 * Name: 윤진한
 * Student ID: 2012036901
 * 
 *
 * class DLinkedPolynomial
 */

public class DLinkedPolynomial implements Polynomial {

	private Node head;
	private Node tail;
	private int size;
	private boolean Add_Mult = false;

	private class Node {
		Node next;
		Node prev;
		Term data;

		Node(Term data) {
			this.data = data;
			next = null;
			prev = null;
		}

		Node(Term data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}// term 을 data로 가지는 Node Class.

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	public int getSize() {
		return size;
	}

	// Head,Tail,Size 의 getter

	public int getDegree() {
		// Complete here ...
		return head.data.expo;
	}

	public double getCoefficient(int exponent) {
		// Complete here ...
		Node a = head;
		int i = 0;
		while (i != size) {
			if (a.data.expo == exponent) {
				return a.data.coeff;
			}
			i++;
			a = a.next;
		}
		return 0;
	}

	public Polynomial padd(Polynomial p) {
		// Complete here ...
		int start = p.getDegree();
		while (start != -1) {
			if (p.getCoefficient(start) != 0) {
				Term newTerm = new Term(p.getCoefficient(start), start);
				Add_Mult = true;
				attach(newTerm);
				Add_Mult = false;
			} // expo 값이 같으면 Add값을 true 로 바꾼뒤 attach 를 통하여 더해주고 난 후 Add값을 다시
			// false로 바꾼다.
			start--;
		}
		return this;
	}

	public Polynomial pmult(Polynomial p) {
		// Complete here ...
		DLinkedPolynomial pmult = new DLinkedPolynomial();
		int start = p.getDegree();
		while (start != -1) {
			if (p.getCoefficient(start) != 0) {
				Node a = head;
				for (int i = 0; i < size; i++) {
					Term newTerm = new Term(a.data.coeff * p.getCoefficient(start), a.data.expo + start);
					pmult.Add_Mult = true;
					pmult.attach(newTerm);
					pmult.Add_Mult = false;
					a = a.next;
				}//  Poly p 와 기존의 Poly 원소들을 하나씩 곱하여 새로운 곱셈 Poly 에 원소를 추가시켜 만들었다.
			}
			start--;
		}
		return pmult;
	}

	public void attach(Term term) throws DuplicateException {
		// Complete here ...
		if (size == 0) {
			Node newNode = new Node(term);
			head = tail = newNode;
		}// 리스트에 아무것도 없을때
		else {
			Node newNode = new Node(term);
			Node a = head;
			if (a.data.expo < newNode.data.expo) {
				newNode = new Node(term, head, null);
				a.prev = newNode;
				head = newNode;
			}// head 의 expo 값이 newNode 의 expo 값 보다 클때.
			else {
				for (int i = 0; i < size; i++) {
					if (a.data.expo == newNode.data.expo) {
						if (Add_Mult == false) {
							System.out.println("DupicateException if a term with the same exponent as the given input term already exists.");
							throw new DuplicateException();
						}// 기존 노드의 expo 값과 newNode 의 expo 값이 같을때 예외처리.
						else if (Add_Mult == true) {
							a.data.coeff += newNode.data.coeff;
							size--;
						}// padd or pmult 시 boolean 변수를 이용하여서 expo 값이 같을때 coeff
						// 값을 더해주고 size는 늘이지 않기 위해 한 개를 줄여준다.
					}
					if (a.next == null) {
						if (a.data.expo > newNode.data.expo) {
							newNode = new Node(term, null, tail);
							a.next = newNode;
							tail = newNode;
						}
					}// tail 노드의 expo 값 보다 newNode 의 expo 값이 더 클때.
					if (a.data.expo > newNode.data.expo && a.next.data.expo < newNode.data.expo) {
						newNode = new Node(term, a.next, a);
						a.next.prev = newNode;
						a.next = newNode;
						break;
					}// newNode 의 expo 값이 기존 노드와 그 다음 사이의 노드의 expo 값 범위에 들어갈때.
					a = a.next;
				}
			}
		}
		size++;
	}

	public double evaluate(double val) {
		// Complete here ...
		double result = 0;
		int i = 0;
		Node a = head;
		while (i != size) {
			result += a.data.coeff * Math.pow(val, a.data.expo);
			a = a.next;
			i++;
		}
		return result;
	}

	public void printMe() {
		// Complete here ...
		String str = " ";
		if (size == 0) {
			System.out.println(str);
		} else {
			int i = 0;
			Node a = head;
			while (i != size) {
				str += "(" + a.data.coeff + "," + a.data.expo + ") ";
				a = a.next;
				i++;
			}
			System.out.println(str);
		}

	}
}
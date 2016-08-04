
/*
 * CSE2010 Homework #4: Maze.java
 * 
 * Fill your code here!
 * 
 * 학번 : 2012036901
 * 이름 : 윤진한
 * 컴퓨터공학과
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
	private int numRows;
	private int numCols;
	private int[][] myMaze = null;
	private boolean[][] marked = null;
	private int startI = 0;
	private int startJ = 0;
	private int finalI;
	private int finalJ;

	/* Create Stack of Location with a capacity of 100 */
	private ArrayStack<Location> _stack = new ArrayStack<>(100);

	/*
	 * DO NOT MODIFY THIS CONSTRUCTOR CODE!
	 */
	public Maze(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));

			numRows = sc.nextInt();
			numCols = sc.nextInt();

			finalI = numRows - 1;
			finalJ = numCols - 1;

			// Create Maze
			myMaze = new int[numRows][numCols];
			marked = new boolean[numRows][numCols];

			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numCols; j++) {
					myMaze[i][j] = sc.nextInt();
					marked[i][j] = false;
				}

			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/*
	 * DO NOT MODIFY THIS METHOD!
	 */
	public boolean findPath() {

		return moveTo(0, 0);
	}

	/*
	 * Complete the following recursive method.
	 */
	private boolean moveTo(int row, int col) {
		/* Your code */
		// 처음 길은 북 동 남 서 순으로 길을 찾음. 
		if (row == finalI && col == finalJ) {
			_stack.push(new Location(row, col));
			return true;
		} // 도착했을때
		if (row != 0 && myMaze[row - 1][col] == 0 && marked[row - 1][col] == false) {
			_stack.push(new Location(row, col));
			marked[row][col] = true;
			row--;
			return moveTo(row, col);
		} // 북
		else if (col != finalJ && myMaze[row][col + 1] == 0 && marked[row][col + 1] == false) {
			_stack.push(new Location(row, col));
			marked[row][col] = true;
			col++;
			return moveTo(row, col);
		} // 동
		else if (row != finalI && myMaze[row + 1][col] == 0 && marked[row + 1][col] == false) {
			_stack.push(new Location(row, col));
			marked[row][col] = true;
			row++;
			return moveTo(row, col);
		} // 남
		else if (col != 0 && myMaze[row][col - 1] == 0 && marked[row][col - 1] == false) {
			_stack.push(new Location(row, col));
			marked[row][col] = true;
			col--;
			return moveTo(row, col);
		} // 서
		else if (row != finalI && myMaze[row + 1][col] == 0 && marked[row + 1][col] == true) {
			_stack.pop();
			myMaze[row][col] = 1;
			row++;
			return moveTo(row, col);
		} // 남 - 갔던 곳
		else if (col != finalJ && myMaze[row][col + 1] == 0 && marked[row][col + 1] == true) {
			_stack.pop();
			myMaze[row][col] = 1;
			col++;
			return moveTo(row, col);
		} // 동 - 갔던 곳
		else if (row != 0 && myMaze[row - 1][col] == 0 && marked[row - 1][col] == true) {
			_stack.pop();
			myMaze[row][col] = 1;
			row--;
			return moveTo(row, col);
		} // 북 - 갔던 곳
		else if (col != 0 && myMaze[row][col - 1] == 0 && marked[row][col - 1] == true) {
			_stack.pop();
			myMaze[row][col] = 1;
			col--;
			return moveTo(row, col);
		} // 서 - 갔던 곳
		else {
			return false;
		} // 길이 없을때
	}

	/*
	 * DO NOT MODIFY THIS METHOD!
	 */
	public void printMaze() {

		System.out.println("Maze[" + numRows + "][" + numCols + "]");
		System.out.println("Entry index = (" + startI + ", " + startJ + ")");
		System.out.println("Exit index = (" + finalI + ", " + finalJ + ")" + "\n");

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++)
				System.out.print(myMaze[i][j] + " ");
			System.out.print("\n");
		}
		System.out.println();
	}

	/*
	 * DO NOT MODIFY THIS METHOD!
	 */
	public void showPath() {

		while (!_stack.isEmpty()) {
			System.out.print(_stack.pop() + " <-- ");
		}

		System.out.println("Start");
	}

}
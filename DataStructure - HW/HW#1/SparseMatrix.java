//�ڷᱸ�� ����
//SparseMatrix
//�й� : 2012036901
//�̸� : ������
//�а� : ��ǻ�Ͱ��а�


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Sparse Matrix ADT 
public class SparseMatrix {
	// nested class for each non-zero entry in Sparse Matrix
	class Entry {
		int row;
		int col;
		double value;

		Entry(int r, int c, double v) {
			row = r;
			col = c;
			value = v;
		}

		void printMe() {
			System.out.println(row + " " + col + " " + value);
		}
	}

	/*
	 * You can define additional private fields and/or methods here, if
	 * necessary.
	 */

	private int row;
	private int col;
	private int number; // 2���� matrix ���� 0�� �ƴ� ���� ������ ���ҵ��� ����.
	private double[][] matrix; // ����ͻ� ��� �� 2���� �迭.
	private ArrayList<Entry> array = new ArrayList<Entry>(); // 2���� matrix ���� 0�� �ƴ� ���� ���� ���ҵ��� ��Ƶ� �迭.

	/*
	 * Construct a sparse matrix by reading data from a specified file
	 */

	public SparseMatrix(String filename) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			boolean i = true;
			while ((line = br.readLine()) != null) {
				String[] Line = line.split(" ");
				if (i) {
					row = Integer.parseInt(Line[0]);
					col = Integer.parseInt(Line[1]);
					number = Integer.parseInt(Line[2]);
					matrix = new double[row][col];
					i = false;
				} else {
					Entry newEntry = new Entry(Integer.parseInt(Line[0]),
							Integer.parseInt(Line[1]),
							Double.parseDouble(Line[2]));
					array.add(newEntry);
				}
			} // filenmae �� ���� SparseMatrix.txt ���� ���پ� �о���� array�� matrix �� ����.
		} catch (Exception ex) {
			System.out.println("There is no File as " + filename + ".");
			// filename ������ ���� �� ���.
		}
	}

	/*
	 * Transpose this matrix
	 */
	public void transpose() {
		int temp = row;
		row = col;
		col = temp;
		matrix = new double[row][col];
		for (int i = 0; i < array.size(); i++) {
			temp = array.get(i).row;
			array.get(i).row = array.get(i).col;
			array.get(i).col = temp;
		}
		// row �� col �� transpose ��.
	}

	/*
	 * Add this matrix with another matrix m. Assume that the dimensions of two
	 * matrices are always compatible.
	 */
	public void addTo(SparseMatrix m) {
		int length = array.size();
		int mlength = m.array.size();
		boolean what = true;
		if (row == m.row || col == m.col) {
			if (length >= mlength) {
				for (int i = 0; i < mlength; i++) {
					for (int j = 0; j < length; j++) {
						if (m.array.get(i).row == array.get(j).row && m.array.get(i).col == array.get(j).col) {
							array.get(j).value += m.array.get(i).value; // row �� col �� ���ٸ� ���� ���� ���ο� ���� ������.
							what = false;
						} else if (what && j == length - 1) {
							what = false;
							array.add(m.array.get(i));
						}// m.array ���� �Ѱ��� array ���� �񱳸� �� �� row �� col �� ���� �ʴٸ� ���� �迭�� �߰�������.
					}
					what = true;
				}
			} // ���� �迭�� �ִ� number�� ���� �ҷ��� �迭�� number ���� Ŭ�� ����. 
			else {
				for (int i = 0; i < length; i++) {
					for (int j = 0; j < mlength; j++) {
						if (array.get(i).row == m.array.get(j).row &&  array.get(i).col == m.array.get(j).col ) {
							array.get(i).value += m.array.get(j).value; // row �� col �� ���ٸ� ���� ���� ���ο� ���� ������
						} else {
							array.add(m.array.get(j));
						} // m.array ���� �Ѱ��� array ���� �񱳸� �� �� row �� col �� ���� �ʴٸ� ���� �迭�� �߰�������.
					}
				}
			} // ���� �迭�� �ִ� number�� ���� �ҷ��� �迭�� number ���� ���� �� ����. 
		} else {
			System.out.println("Can't do aadTo because of length different.");
		} // �� ����� row �� col �� ���� �ʴٸ� ������ �Ҽ� ���� ������ ���� ������ �����.
	}

	/*
	 * Print contents of this matrix
	 */
	public void printMe() {
		System.out.println();
		for (int i = 0; i < array.size(); i++) {
			matrix[array.get(i).row][array.get(i).col] = array.get(i).value;
		} // 1���� ��ķ� ���� 2���� ����� ������.

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf("%8.2f", matrix[i][j]);
			}
			System.out.println("\n");
		} // ����ͻ� ������ 2���� ����� ����Ʈ��.
	}
}

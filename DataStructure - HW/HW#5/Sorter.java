/*
 * CSE2010 Homework #5: Sorter.java
 * 
 * Complete this file!
 * 
 */

public class Sorter {

	public static void selectionSort(int[] entry) {
		// Your code goes here!
		int k;
		for (int i = 0; i < entry.length - 1; i++) {
			k = i;
			for (int j = i + 1; j < entry.length; j++) {
				if (entry[k] > entry[j]) {
					k = j;
				}
			}
			int temp = entry[k];
			entry[k] = entry[i];
			entry[i] = temp;
		}
	}

	public static void insertionSort(int[] entry) {
		// Your code goes here!
		for (int i = 1; i < entry.length; i++) {
			int change = entry[i];
			int k = i - 1;
			while (k >= 0 && change < entry[k]) {
				entry[k + 1] = entry[k];
				entry[k] = change;
				k--;
			}
		}
	}

	public static void heapSort(int[] entry) {
		// Your code goes here!
		int size = entry.length - 1;
		for (int i = size / 2; i >= 0; i--) {
			MakeMaxHeap(entry, i, size);
		} // Max heap 상태로 만들어줌.
		for (int i = size; i > 0; i--) {
			int temp = entry[0];
			entry[0] = entry[i];
			entry[i] = temp;
			size--;
			MakeMaxHeap(entry, 0, size);
		} // Max 값을 뒤로 보내고 나머지 데이터들로 Max heap 상태를 만들어줌.
	}

	public static void MakeMaxHeap(int[] entry, int length, int size) {
		int left = length * 2;
		int right = length * 2 + 1;
		int parent = length;
		if (left <= size && entry[left] >= entry[parent]) {
			parent = left;
		}
		if (right <= size && entry[right] >= entry[parent]) {
			parent = right;
		}
		if (parent != length) {
			int temp = entry[length];
			entry[length] = entry[parent];
			entry[parent] = temp;
			MakeMaxHeap(entry, parent, size);
		}
	}
}
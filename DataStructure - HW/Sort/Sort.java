
public class Sort {

	public static void BubbleSort(int[] array) {
		
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
				Run.printer(array, 2);
			}
		}
	}

	public static void SelectionSort(int[] array) {
		int k;
		for (int i = 0; i < array.length - 1; i++) {
			k = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[k] > array[j]) {
					k = j;
				}
			}
			int temp = array[i];
			array[i] = array[k];
			array[k] = temp;
			Run.printer(array, 2);
		}
	}

	public static void InsertSort(int[] array) {
		int k;
		for (int i = 1; i < array.length; i++) {
			k = i;
			while (k != 0 && array[k] < array[k - 1]) {
				int temp = array[k];
				array[k] = array[k - 1];
				array[k - 1] = temp;
				Run.printer(array, 2);
				k--;
			}
		}
	}

	public static void HeapSort(int[] array) {
		// Your code goes here!
		int size = array.length - 1;
		for (int i = size / 2; i >= 0; i--) {
			MakeMaxHeap(array, i, size);
		} // Max heap 상태로 만들어줌.
		for (int i = size; i > 0; i--) {
			Run.printer(array, 2);
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			size--;
			Run.printer(array, 2);
			MakeMaxHeap(array, 0, size);
		} // Max 값을 뒤로 보내고 나머지 데이터들로 Max heap 상태를 만들어줌.
	}

	private static void MakeMaxHeap(int[] array, int length, int size) {
		int left = length * 2;
		int right = length * 2 + 1;
		int parent = length;
		if (left <= size && array[left] >= array[parent]) {
			parent = left;
		}
		if (right <= size && array[right] >= array[parent]) {
			parent = right;
		}
		if (parent != length) {
			int temp = array[length];
			array[length] = array[parent];
			array[parent] = temp;
			MakeMaxHeap(array, parent, size);
		}
	}

	public static void MergeSort(int[] array) {
		if (array.length > 1) {
			int temp1[] = new int[array.length / 2];
			int temp2[] = new int[array.length - array.length / 2];
			System.arraycopy(array, 0, temp1, 0, array.length / 2);
			System.arraycopy(array, array.length / 2, temp2, 0, array.length - array.length / 2);
			MergeSort(temp1);
			MergeSort(temp2);
			Merge(array, temp1, temp2);
		}
	}

	private static void Merge(int[] array, int[] temp1, int[] temp2) {
		int index = 0, temp1curser = 0, temp2curser = 0;

		while (temp1curser != temp1.length && temp2curser != temp2.length) {
			if (temp1[temp1curser] > temp2[temp2curser]) {
				array[index] = temp2[temp2curser];
				index++;
				temp2curser++;
			} else {
				array[index] = temp1[temp1curser];
				index++;
				temp1curser++;
			}
		}

		if (temp1.length == temp1curser) {
			for (int i = temp2curser; i < temp2.length; i++) {
				array[index] = temp2[i];
				index++;
			}
		} else if (temp2.length == temp2curser) {
			for (int i = temp1curser; i < temp1.length; i++) {
				array[index] = temp1[i];
				index++;
			}
		}
	}

	public static void QuickSort(int[] array) {
		QuickSort(array, 0, array.length - 1);
	}

	private static void QuickSort(int[] array, int a, int b) {
		if (b > a) {
			int p = array[b];
			int l = a;
			int r = b - 1;
			while (r >= l) {
				while (r >= l && array[l] <= p) {
					l++;
				}
				while (r >= l && array[r] > p) {
					r--;
				}
				if (r > l) {
					int temp = array[l];
					array[l] = array[r];
					array[r] = temp;
				}
			}
			int temp = array[l];
			array[l] = array[b];
			array[b] = temp;
			QuickSort(array, a, l - 1);
			QuickSort(array, l + 1, b);
		}
	}
}

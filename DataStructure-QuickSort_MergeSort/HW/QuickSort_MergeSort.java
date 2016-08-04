//자료구조 실습 과제 
//Quick & Merge
//학번 : 2012036901
//이름 : 윤진한 

public class QuickSort_MergeSort {

	private static int temp;

	public static void QuickSort(int[] array) {
		QuickSort(array, 0, array.length - 1);
	} // QuickSort 를 오버로딩하여 구현하였습니다.

	private static void QuickSort(int[] array, int a, int b) {
		if (b > a) { // 한 개 이상의 원소를 가진 배열일때.
			int p = array[b]; // 배열의 끝에 있는 원소를 pivot 으로 정하였습니다.
			int l = a;
			int r = b - 1;
			while (r >= l) { // l과 r이 만날 때까지.
				while (r >= l && array[l] <= p) {
					l++; }
				while (r >= l && array[r] > p) {
					r--; }
				// 전체 while문이 실행 되는 동안 두개의 while문이 실행도중 빠져나온다면 원소의 값이 제대로 위치하지 않은 것 이므로 아래의 실행을 해줍니다.
				if (l < r) {
					temp = array[l];
					array[l] = array[r];
					array[r] = temp;
				} // l 또는 r 의 위치에 있는 원소의 값이 pivot 보다 작으면 왼쪽으로 크면 오른쪽으로 r 또는 l 의 위치 원소와 swap 해줍니다.
			}
			// 배열의 처음 과 pivot의 전부터 원소의 크기를 pivot과 비교하며 왼쪽은 작은값이 오른쪽은 큰값이 들어가도록 해줍니다.
			temp = array[l];
			array[l] = array[b];
			array[b] = temp;
			QuickSort(array, a, l - 1);
			QuickSort(array, l + 1, b);
			// pivot 값을 중간 위치인 l에 있는 원소와 바꿔주어 pivot을 기준으로 왼쪽과 오른쪽을 각각 다시 quick 정렬 시킵니다.
		}
	}

	public static void MergeSort(int[] array) {
		if (array.length > 1) {
			int temp1[] = new int[array.length / 2];
			int temp2[] = new int[array.length - array.length / 2];
			System.arraycopy(array, 0, temp1, 0, array.length / 2);
			System.arraycopy(array, array.length / 2, temp2, 0, array.length - array.length / 2);
			// 기존 배열을 반으로 나눈뒤 새로운 배열에 각각 저장합니다.
			MergeSort(temp1);
			MergeSort(temp2);
			// 배열의 크기가 1이 될때 까지 나누어 저장해줍니다.
			Merge(array, temp1, temp2);
			// 나눈 두 배열을 정렬하면서 합병하여 나누기 전인 array 배열에 저장합니다.
		}
	}

	private static void Merge(int[] array, int[] temp1, int[] temp2) {
		int index = 0;
		int temp1curser = 0; // temp1 의 원소를 가르키는 커서.
		int temp2curser = 0; // temp2 의 원소를 가르키는 커서.

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
		} // 한쪽 배열이 전부 array에 저장 될 때까지 두 배열의 원소 크기를 비교합니다.

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
		} // 남은 배열의 나머지 부분을 array의 뒷부분에 추가시켜줍니다.
	}

	public static void printer(int[] array, int kind) {
		String what = (kind == 3) ? "" : ((kind == 0) ? " - Before Sorting " : " - After Sorting ") + "Array : ";
		System.out.print(what + "[ ");
		for (int e : array) {
			System.out.print(e + " ");
		}
		System.out.println("]\n");
	} // 현재 배열의 상태를 보여줍니다.

}

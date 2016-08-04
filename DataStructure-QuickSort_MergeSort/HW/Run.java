//자료구조 실습 과제 
//Quick & Merge
//학번 : 2012036901
//이름 : 윤진한 

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("< Quick Sort >\n");
		int data[] = { 45, 39, 98, 15, 52, 44, 33, 28, 40, 38, 77, 68, 11, 43 };
		QuickSort_MergeSort.printer(data, 0);
		QuickSort_MergeSort.QuickSort(data);
		QuickSort_MergeSort.printer(data, 1);
		int data2[] = { 101, 39, 98, 15, 52, 44, 11, 28, 40, 5, 77, 68, 11, 43 };
		QuickSort_MergeSort.printer(data2, 0);
		QuickSort_MergeSort.QuickSort(data2);
		QuickSort_MergeSort.printer(data2, 1);
		System.out.println("< Merge Sort >\n");
		int data3[] = { 54, 93, 89, 51, 25, 44, 33, 82, 4, 83, 77, 26, 11, 34, 15 };
		QuickSort_MergeSort.printer(data3, 0);
		QuickSort_MergeSort.MergeSort(data3);
		QuickSort_MergeSort.printer(data3, 1);
		int data4[] = { 11, 61, 93, 51, 25, 44, 11, 28, 2, 15, 47, 68, 31, 43, 17 };
		QuickSort_MergeSort.printer(data4, 0);
		QuickSort_MergeSort.MergeSort(data4);
		QuickSort_MergeSort.printer(data4, 1);
	}
}

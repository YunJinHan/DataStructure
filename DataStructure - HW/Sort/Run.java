import java.util.Scanner;

public class Run {
	
	public static void printer(int [] array,int i){
		if (i==1){
			System.out.print("Sorted ");
		}
		if (i==2){
			System.out.print("Sorting ");
		}
		System.out.print("Array : [ ");
		for (int e : array){
			System.out.print(e+" ");
		}
		System.out.print("]\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.print("Size (1~100) : ");
			int size = s.nextInt();
			int array[] = Array.generate(size);
			printer(array,0);
			System.out.print("1) Bubble sort 2) Selection sort 3) Insert sort 4) Heap sort 5) Merge sort 6) Quick sort 7) Quit ==> ");
			int kind = s.nextInt();
			if (kind==1){
				Sort.BubbleSort(array);
				printer(array,1);
			}
			else if (kind==2){
				Sort.SelectionSort(array);
				printer(array,1);
			}
			else if (kind==3){
				Sort.InsertSort(array);
				printer(array,1);
			}
			else if (kind==4){
				Sort.HeapSort(array);
				printer(array,1);
			}
			else if (kind==5){
				Sort.MergeSort(array);
				printer(array,1);
			}
			else if (kind==6){
				Sort.QuickSort(array);
				printer(array,1);
			}
			else if (kind==7){
				System.out.println("Thank you for using.");
				break;
			}
			else {
				System.out.println("Wrong answer, Try again.");
			}
		}
	}
}

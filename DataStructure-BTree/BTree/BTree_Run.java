
// DataBase - BTree
// BTree_Run
// Department : Computer Science and Engineering
// SID : 2012036901
// NAME : 윤진한
// 2016.05.14

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BTree_Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTree b1 = new BTree();
		Scanner s = new Scanner(System.in);
		while (true) {
			int Select, number;
			while (true) {
				try {
					System.out.println("============================================================");
					System.out.printf(
							"1) Insert\n2) Delete\n3) Search\n4) Print B-Tree\n9) Insert From File\n0) Exit\n Select : ");
					Select = s.nextInt();
					break;
				} catch (InputMismatchException ime) {
					System.out.println("Invalid input , Try again");
					String enter = s.next();
				}
			}
			System.out.println("============================================================");
			if (Select == 1) {
				// Insert
				while (true) {
					while (true) {
						try {
							System.out.print("Key to Insert (Exit : -1) : ");
							number = s.nextInt();
							break;
						} catch (InputMismatchException ime) {
							System.out.println("Invalid input , Try again");
							String enter = s.next();
						}
					}
					if (number == -1) {
						break;
					}
					b1.Insert(number);
				}
			} else if (Select == 2) {
				// Delete
				while (true) {
					while (true) {
						try {
							System.out.print("Key to Delete (Exit : -1) : ");
							number = s.nextInt();
							break;
						} catch (InputMismatchException ime) {
							System.out.println("Invalid input , Try again");
							String enter = s.next();
						}
					}
					if (number == -1) {
						break;
					}
					b1.Delete(number);
				}
			} else if (Select == 3) {
				// Search
				while (true) {
					while (true) {
						try {
							System.out.print("Key to Search (Exit : -1) : ");
							number = s.nextInt();
							break;
						} catch (InputMismatchException ime) {
							System.out.println("Invalid input , Try again");
							String enter = s.next();
						}
					}
					if (number == -1) {
						break;
					}
					if (b1.Search(number, 1) == null) {
						System.out.println(number + " is not in this B-Tree");
					}
				}
			} else if (Select == 4) {
				// Print B-Tree
				b1.Show();
			} else if (Select == 9) {
				// Insert PackageKey from Text file
				System.out.print("Data File Path : ");
				String path = s.next();
				try {
					File file = new File(path);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = null;
					while ((line = br.readLine()) != null) {
						b1.Insert(Integer.parseInt(line));
					}
					br.close();
					fr.close();
					System.out.println("Complete Insertion");
				} catch (FileNotFoundException e) {
					System.out.println("It is a wrong path");
				} catch (Exception e) {
					System.out.println("Error occur!");
				}
			} else if (Select == 0) {
				// exit
				System.out.println("Thank you for using.");
				break;
			} else {
				// input type is wrong
				System.out.println("Invalid input , Try again");
			}
		}
	}
}

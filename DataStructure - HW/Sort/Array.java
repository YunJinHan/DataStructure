import java.util.Date;
import java.util.Random;

public class Array {
	
	static int[] generate(int size) {
		
		Random r = new Random(new Date().getTime());
		
		int temp[] = new int[size];
		
		for (int i = 0; i < size; i++) {
			temp[i] = r.nextInt(100);
		}
		
		return temp;
	}
}

// Randy Wong
// Some of the code is modified so that it has different test cases
import java.util.Random;
import java.util.Scanner;
public class CS331Project1 {

	public static void main(String[] args)
	{
		Random random = new Random(10);
		for(int i = 8; i <= 16; i++) {
			int n = (int) Math.pow(2, i);
			int[] sortedArray = new int[n];
			for(int j = 0; j < sortedArray.length - 1; j++) {
				sortedArray[j] = j;
			}
			//long start =  System.nanoTime();
			//int temp1 = (SequentialSearch(sortedArray, sortedArray.length - 1));
			//long end = System.nanoTime();
			//System.out.println(end - start);

			//long start =  System.nanoTime();
			//int temp1 = (BinarySearch(sortedArray, random.nextInt(n), 0, sortedArray.length - 1));
			//long end = System.nanoTime();
			//System.out.println(end - start);

			//System.out.println("Sequential Array: " + n + ", time: " + (end - start));
			//start =  System.nanoTime();
			//int temp2 = (BinarySearch(sortedArray, 6, 0, sortedArray.length - 1));
			//end =  System.nanoTime();
			//System.out.println("Binary Array: " + n + ", time: " + (end - start));

		}
		long end = System.nanoTime();

		long time;
		Scanner kb= new Scanner(System.in);
		System.out.println("Selection Sort: 1, Insertion Sort: 2, Merge Sort: 3, Quick Sort: 4.");
		int method = kb.nextInt();

		SortAlgorithm array = null;
		SortTimer timer = new SortTimer();

		switch(method) {
		case 1:
			array = new SelectionSort();
			System.out.println("Selection Sort");
			break;
		case 2:
			array = new InsertionSort();
			System.out.println("Insertion Sort");
			break;
		case 3:
			array = new MergeSort();
			System.out.println("Merge Sort");
			break;
		case 4:
			array = new QuickSort();
			System.out.println("Quick Sort");
			break;

		default: System.out.println("Invalid  number.");
		System.exit(0);
		break;
		}

		double[] test;
		System.out.printf("%8s%12s\n", "n", "time");
		time = 0;
		for(int i = 1; i <= 15; i++) {
			int n = (int) Math.pow(2, i);
			for(int j = 0; j < 5; j++) {
				test = randomArray(n); 
				timer.reset();
				array.sort(test, timer);
				if(!verify(test)) {
					System.out.println("Not sorted properly.");
					System.exit(0);
				}
				time += timer.getElapsedTime() / 5000.0;
			}
			System.out.printf("%8d%12d\n", n, time);
		}
	}

	private static double[] randomArray(int n) {
		Random r = new Random();
		double[] a = new double[n];
		for (int i = 0; i < n-1; i++) {
			a[i] = r.nextInt(n);
			// a[i] = 1;
		}
		return a;
	}

	private static boolean verify(double[] a) {
		for (int i = 1; i < a.length - 1; i++)
			if(a[i-1] > a[i])
				return false;
		return true;
	}

	public static int SequentialSearch(int[] array, int value) { // 8c operations
		for(int index = 0; index < array.length; index++) { // 4n + 2c operations
			if (array[index] == value) { // 3n operations
				return index;
			}
		}
		return -1;
	}

	public static int BinarySearch(int[] array, int key, int min, int max) { // Return Type (1), Function Call Overhead (4), Pass-by-reference Parameter (1), Pass-by-value Parameter (6)
		if(max < min) { // Branching (1), Comparison (1)
			return -1;
		}
		else {
			int mid = (max + min + 1) / 2; // Variable Initialization (1), Mathematical Expression (3)
			if(array[mid] > key) { // Branching (1), Pass-by-reference (1), Comparison (1)
				return BinarySearch(array, key, min, mid - 1); // n or Function Call Overhead (4), Pass-by-reference Parameter (1), Pass-by-value Parameter (6)
			}
			else if(array[mid] < key) { // Array Indexing(1), Comparison (1), Pass-by-value (2)
				return BinarySearch(array, key, mid + 1, max); // n or Function Call Overhead (4), Pass-by-reference Parameter (1), Pass-by-value Parameter (6)
			}
			else {
				return mid;
			}
		}
	}

}

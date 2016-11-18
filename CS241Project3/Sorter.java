//import java.util.Scanner;
//
//public class Sorter
//{
//	public static long time;
//	public static long comp;
//	public static long moves;
//
//	public static void main(String[] args) {
//
//		Scanner kb= new Scanner(System.in);
//		System.out.println("Insert Sort: 1, Heap Sort: 2, Quick Sort: 3, Selection sort: 4.");
//		int method = kb.nextInt();
//		SortAlgorithm array = null;
//		SortTimer t = new SortTimer();
//
//		switch(method) {
//		case 1:
//			array = new InsertionSort();
//			System.out.println("Insertion Sort");
//			break;
//		case 2:
//			array = new HeapSort();
//			System.out.println("Heap Sort");
//			break;
//		case 3:
//			array = new QuickSort();
//			System.out.println("Quick Sort");
//			break;
//		case 4:
//			array = new SelectionSort();
//			System.out.println("Selection Sort");
//			break;
//		default: System.out.println("Invalid  number.");
//			System.exit(0);
//			break;
//		}
//
//		for(int i = 1; i < 5; i++)
//		{
//			t.reset();
//			double []a = randomArray(i);
//			array.sort(a, t);
//			System.out.println(verify(a));
////			System.out.println(System.nanoTime()/1000);
////			time += t.getElapsedTime()/5000;
//			time += t.getElapsedTime() / 1000;
//			comp += t.getComparisons();
//			moves += t.getMoves();
//			int n = (int) Math.pow(10, i);
//			System.out.printf("%8d%12d%12d%12d\n", n, time, comp, moves);
//		}
//	}
//
//	private static double[] randomArray(int n) {
//		double[] a = new double[(int) Math.pow(10, n)];
//		for (int i = 0; i < n; i++)
//			a[i] = Math.random();
//		return a;
//	}
//
//	private static boolean verify(double[] a) {
//		for (int i = 1; i < a.length - 1; i++)
//			if(a[i-1] > a[i])
//				return false;
//		return true;
//	}
//
//	private static void swap(double[] a, int i, int j) {
//		double temp1 = a[i];
//		double temp2 = a[j];
//		a[i] = temp2;
//		a[j] = temp1;
//	}
//}


/*
import java.util.*;

public class QuickSort implements SortAlgorithm {

	private static Random rand = new Random();
	SortTimer timer;

	// Input list of n comparable elements indexed 0 to n - 1.
	public void sort(double[] a, SortTimer timer) {
		// Call recursive quicksort procedure with left = 0 and right = n - 1.
		int left = 0;
		int right = a.length - 1;
		recursive(a, left, right);
		this.timer = timer;
	}

	private static void recursive(double[] a, int left, int right) {
		// If left = right then return.
		if(left > right) {
			return;
		}
		// Else select pivot element in the range left to right.
		else {
			int pivot = (int) (rand.nextInt(right + 1 - left)) + left;
			// Partition list in place so that all elements <= pivot precede those that are > pivot.
			// Let pos be the position of the pivot element after partitioning.
			int pos = partition(a, left, right, pivot);
			// Recursively quicksort elements left to pos - 1.
			partition(a, left, pos - 1, pivot);
			// Recursively quicksort elements pos + 1 to right.
			partition(a, pos + 1, right, pivot);
		}
	}

	// Input: list of elements from left to right and index of pivot element.
	private static int partition(double[] a,int left, int right, int pivot) {
		int pos;
		// Swap pivot with element at position right.
		double swap = a[right];
		a[right] = a[pivot];
		a[pivot] = swap;
		// Set pos to left; pos represents the boundary between small and large elements.
		pos = left;
		// for i = left to right - 1.
		for(int i = left; left < right - 1; i++) {
			// If element at i is <= pivot, swap it with element at pos and advance pos.
			if (a[i] <= a[pivot]) {
				swap = a[pos];
				pos++;
			}
		}
		// At this point, leftmost element > pivot is at pos; swap pivot with this element.
		swap = a[left];
		a[left] = a[pivot];
		a[pivot] = swap;
		// Return pos
		return pos;
		// Output: rearranged list: [pivot <=, pivot, > pivot]; final index of pivot element.
	}
}

 */
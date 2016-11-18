
public class QuickSort implements SortAlgorithm {
	double[] a;
	SortTimer timer;

	public void sort(double[] a, SortTimer timer) {
		this.timer = timer;
		this.a = a;
		// Call recursive quicksort procedure with left = 0 and right = n - 1
		quicksort(0, a.length - 1); // Pass-by-reference (1)
	}

	public void quicksort(int left, int right) { // Return Type (1), Function Call Overhead (4) 
		int pos = partition(left, right); // Variable Initialization (1)
		if (left < pos - 1) { // Comparison (1), Mathematical expressions (1)
			quicksort(left, pos - 1); // Mathematical Expression (1)
		}
		if (pos < right) { // Comparison (1)
			quicksort(pos, right);
		}
	}
		
	public int partition(int left, int right) { // Return Type (1), Function Call Overhead (4)
		int i = left;  // Variable Initialization (1)
		int j = right; // Variable Initialization (1)
		double pivot = a[(left + right) / 2]; // Variable Initialization (1), Mathematical expressions (2)
//		double pivot = a[left]; // Variable Initialization (1)
		while (i <= j) { //Branching (1)
			while (a[i] < pivot) { // Branching (1), Array Indexing (1), Comparison(1)
				i++; // Variable Initialization (1), Mathematical Expression (1)
			}
			while (a[j] > pivot) { // Branching (1), Array Indexing (1), Comparison(1)
				j--; // Variable Initialization (1), Mathematical Expression (1)
			}
			if (i <= j) { // Comparison (1)
				double swap = a[i]; // Variable Initialization (1)
				a[i] = a[j]; // Variable Initialization (1)
				a[j] = swap; // Variable Initialization (1)
				i++; // Variable Initialization (1), Mathematical Expression (1)
				j--; // Variable Initialization (1), Mathematical Expression (1)
			}
		}
		return i;
	}
}
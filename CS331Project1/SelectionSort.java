
public class SelectionSort implements SortAlgorithm {

	// Input: list of n comparable elements indexed 0 to n - 1.
	public void sort(double[] a, SortTimer timer) { // Function Nonvoid Return (1), Function call Overhead (4)
		// For i = n - 1 down to 1.
		for (int i = a.length - 1; i >= 0; i--) { // // Branching (1), Variable Initialization (2), Mathematical Expression (2), Comparison (1)
			int max = i; // Variable Initialization (1)
			// Set max to the index of largest element in the range 0 to i.
			for(int j = i - 1; j >= 0; j--) { // Branching (1), Variable Initialization (2), Mathematical Expression (2), Comparison (1)
				if (a[j] > a[max]) { // Comparison (1)
					max = j; // Variable Initialization (1)
				}
			}
			// Swap elements at positions i and max.
			double swap = a[max]; // Variable Initialization (1)
			a[max] = a[i]; // Variable Initialization (1)
			a[i] = swap; // Variable Initialization (1)
		}
	}
	// Output: list of elements sorted in ascending order.
}


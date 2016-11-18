
public class InsertionSort implements SortAlgorithm {
	SortTimer timer;

	// Input: list of n comparable elements indexed 0 to n - 1.
	public void sort(double[] a, SortTimer timer) { // Function Call (4), Pass-by-reference Parameter (1), Function Nonvoid Return (1)
		// For i = 1 to n - 1.
		for (int i = 1; i < a.length; i++) { // Pass-by-reference (1), Initialization of Variable (2), Mathematical Expression (1), Comparison (1)
			double shift = a[i]; // Initialization of Variable (1)
			int j;
			// j the current value with elements i - 1 down to 0.
			for (j = i - 1; j >= 0 && shift < a[j]; j--) { // Initialization of Variable (2), Mathematical Expression (1), Comparison (3)
				// Compare the current value with elements i - 1 down to 0.
				// Shift each element that is greater than the current value one position to the right.
				a[j + 1] = a[j]; // Initialization of Variable (1)
			}
			// Insert current value into its correct position relative to other elements in range.
			a[j + 1] = shift;  // // Initialization of Variable (1)
		}
	}
	// Output: list of elements sorted in ascending order.
}
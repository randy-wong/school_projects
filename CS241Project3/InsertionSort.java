
public class InsertionSort implements SortAlgorithm {
	SortTimer timer;

	// Input: list of n comparable elements indexed 0 to n - 1.
	public void sort(double[] a, SortTimer timer) {
		// For i = 1 to n - 1.
		for (int i = 1; i < a.length; i++) {
			double shift = a[i];
			int j;
			// j the current value with elements i - 1 down to 0.
			for (j = i - 1; j >= 0 && shift < a[j]; j--) {
				// Compare the current value with elements i - 1 down to 0.
				// Shift each element that is greater than the current value one position to the right.
				a[j + 1] = a[j];
				timer.addComparison();
				timer.addMove();
			}
			// Insert current value into its correct position relative to other elements in range.
			a[j + 1] = shift;
			timer.addMove();
		}
	}
	// Output: list of elements sorted in ascending order.
}
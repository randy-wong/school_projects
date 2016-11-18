
public class SelectionSort implements SortAlgorithm {

	// Input: list of n comparable elements indexed 0 to n - 1.
	public void sort(double[] a, SortTimer timer) {
		// For i = n - 1 down to 1.
		for (int i = a.length - 1; i >= 0; i--) {
			int max = i;
			// Set max to the index of largest element in the range 0 to i.
			for(int j = i - 1; j >= 0; j--) {
				timer.addComparison();
				if (a[j] > a[max]) {
					max = j;
				}
			}
			// Swap elements at positions i and max.
			double swap = a[max];
			a[max] = a[i];
			a[i] = swap;
			timer.addMoves(3);
		}
	}
	// Output: list of elements sorted in ascending order.
}


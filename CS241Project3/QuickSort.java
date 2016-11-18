
public class QuickSort implements SortAlgorithm {
	double[] a;
	SortTimer timer;

	public void sort(double[] a, SortTimer timer) {
		this.timer = timer;
		this.a = a;
		// Call recursive quicksort procedure with left = 0 and right = n - 1
		quicksort(0, a.length - 1);
	}

	public void quicksort(int left, int right) {
		int pos = partition(left, right);
		if (left < pos - 1) {
			quicksort(left, pos - 1);
		}
		if (pos < right) {
			quicksort(pos, right);
		}
	}
		
	public int partition(int left, int right) {
		int i = left; 
		int j = right;
		double pivot = a[(left + right) / 2];     
		while (i <= j) {
			timer.addComparison();
			while (a[i] < pivot) {
				timer.addComparison();
				i++;
			}
			timer.addComparison();
			while (a[j] > pivot) {	
				timer.addComparison();
				j--;
			}
			timer.addComparison();
			if (i <= j) {
				double swap = a[i];
				a[i] = a[j];
				a[j] = swap;
				timer.addMoves(3);
				i++;
				j--;
			}
		}
		return i;
	}
}
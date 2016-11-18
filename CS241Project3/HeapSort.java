
public class HeapSort implements SortAlgorithm {
	double[] a;
	SortTimer timer;
	int length;

	public void sort(double[] a, SortTimer timer) {
		this.a = a;
		this.timer = timer;
		length = a.length;
		heapify(a);
		heap(a);
	}
	
	// Input: list of n comparable elements.
	public void heap(double[] a) {
		// Convert list into heap.
		double[] b = new double[a.length];
		// For i = 1 to n.
		for(int i = 0; i < a.length; i++) {
			// Delete minimum element and append to new initially empty list.
			b[i] = poll();
		}
		for(int i = 0; i < a.length; i++) {
			// return new list.
			a[i] = b[i];
		}
	}

	// Sift down node at position a as needed to restore heap property.
	private void siftdown(int index) {
		// b  = a.
		int b = index;
		int c;
		// While node b has at least one child, and node b is bigger than its smaller child node c.
		while (haschild(b) && a[b] > a[smallerchild(b)]) {
			timer.addComparison();
			c = smallerchild(b);
			// Swap value of b with value of c.
			double swap = a[b];
			a[b] = a[c];
			a[c] = swap;
			timer.addMoves(3);
			// Set b = c.
			b = c;
		}
	}
	
	// Checks if node b has at least one child.
	private boolean haschild(int index) {
		timer.addComparison();
		return (index <= length / 2 - 1);
	}

	// Checks if node b is bigger than its smaller child node c.
	private int smallerchild(int index) {
		if(length - 1 >= 2 * index + 2) {
			if (a[2 * index + 1] > a[2 * index + 2]) {
				timer.addComparison();
				return 2 * index + 2;
			}
		}
		return 2 * index + 1;
	}

	// Convert input array into a heap.
	private void heapify(double []a) {
		// k = index of last node that has at least one child.
		int k = a.length / 2 - 1;
		// For a = k down to 0.
		for(int index = k; index >= 0; index--) {
			// Sift down(0).
			siftdown(index);
		}

	}

	// Delete and return minimum queue element and suitably reorganize queue.
	private double poll() {
		// Let e be the first element in array.
		double minvalue = a[0];
		// Exchange first and last elements in array
		//a[length - 1] = minvalue;
		length--;
		timer.addMoves(3);		
		// Sift down(0).
		siftdown(0);
		// Return e
		return minvalue;
	}
}

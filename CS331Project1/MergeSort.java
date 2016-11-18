
public class MergeSort implements SortAlgorithm {
	double[] a;
	SortTimer timer;
	int length;

	public void sort(double[] a, SortTimer timer) {
		this.a = a;
		this.timer = timer;
		length = a.length;
		mergeSort(a);
	}

	public double[] mergeSort(double array[]) { // Function Call Overhead (4), Return Type (1)
		// if the array has more than 1 element, we need to split it and merge the sorted halves
		if(array.length > 1) { // Pass-by-reference Parameter (1), Comparison (1)
			int length1 = array.length / 2; // Variable Initialization (1)
			int length2 = array.length - length1; // Variable Initialization (1)
			double split1[] = new double[length1]; // Variable Initialization (1)
			double split2[] = new double[length2]; // Variable Initialization (1)
			for(int i = 0; i < length1; i++) { // Branching (1), Variable Initialization (2), Mathematical Comparison (1)
				split1[i] = array[i]; // Variable Initialization (1)
			}
			for(int i = length1; i < length1 + length2; i++) { // Branching (1), Variable Initialization (2), Mathematical Comparison (2)
				split2[i - length1] = array[i];
			}
			split1 = mergeSort(split1); // Variable Initialization (1)
			split2 = mergeSort(split2); // Variable Initialization (1)
			int i = 0, j = 0, k = 0; // Variable Initialization (3)
			while(split1.length != j && split2.length != k) { // Comparison (3)
				if(split1[j] < split2[k]) { // Comparison (1), Array Indexing (2), Branching (1)
					array[i] = split1[j]; // Variable Initialization (1)
					i++; // Variable Initialization (1)
					j++; // Variable Initialization (1)
				}
				// if the current element of split2 is less than current element of split1
				else {
					array[i] = split2[k]; // Variable Initialization (1)
					i++; // Variable Initialization (1), Mathematical Comparison (1)
					k++; // Variable Initialization (1), Mathematical Comparison (1)
				}
			}
			while(split1.length != j) { // Comparison (1)
				array[i] = split1[j]; // Variable Initialization (1)
				i++; // Variable Initialization (1)
				j++; // Variable Initialization (1)
			}
			while(split2.length != k) { // Comparison (1)
				array[i] = split2[k]; // Variable Initialization (1)
				i++; // Variable Initialization (1)
				k++; // Variable Initialization (1)
			}
		}
		return array;
	}
}

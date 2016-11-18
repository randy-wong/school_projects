import java.util.Random;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {

		long time, comp, moves;
		Scanner kb= new Scanner(System.in);
		System.out.println("Selection Sort: 1, Insertion Sort: 2, Heap Sort: 3, Quick Sort: 4.");
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
			array = new HeapSort();
			System.out.println("Heap Sort");
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
        System.out.printf("%8s%12s%12s%12s\n", "n", "time", "comp", "moves");
        time = 0;
        for(int i = 1; i <= 7; i++) {
            int n = (int) Math.pow(10, i);
            comp = 0;
            moves = 0;
            for(int j = 0; j < 5; j++) {
                    test = randomArray(n); 
                    timer.reset();
                    array.sort(test, timer);
                    if(!verify(test)) {
                            System.out.println("Not sorted properly.");
                            System.exit(0);
                    }
                    time += timer.getElapsedTime() / 5000.0;
                    comp += timer.getComparisons() / 5.0;
                    moves += timer.getMoves() / 5.0;
            }
    		System.out.printf("%8d%12d%12d%12d\n", n, time, comp, moves);
        }
	}

	private static double[] randomArray(int n) {
		Random r = new Random();
		//int temp = (int) Math.pow(10, n);
		double[] a = new double[n];
		for (int i = 0; i < n-1; i++) {
			a[i] = r.nextInt(n);
		}
		return a;
	}

	private static boolean verify(double[] a) {
		for (int i = 1; i < a.length - 1; i++)
			if(a[i-1] > a[i])
				return false;
		return true;
	}
}

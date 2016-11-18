import java.util.*;

public class CS331Project3 {
	public static void main(String[] args) {

		// Matrix Multiplication
		Random random = new Random();
		int multiplicandrow = random.nextInt(9) + 1;
		int columnsandrows = random.nextInt(9) + 1 ;
		int multipliercolumns = random.nextInt(9) + 1;
		int [][] multiplicand = new int[multiplicandrow][columnsandrows];
		int [][] multiplier = new int[columnsandrows][multipliercolumns];
		int [][] product = new int[multiplicandrow][multipliercolumns];

		// Nanotime
		long time = System.nanoTime();

		for(int i = 0; i < multiplicandrow; i++) {
			for(int j = 0; j < multipliercolumns; j++) {
				for(int k = 0; k < columnsandrows; k++) {
					product[i][j] += multiplicand[i][k] * multiplier[k][j];
				}
			}
		}
		System.out.println(System.nanoTime() - time);
		
		// Created a class for testing the chained matrix multiplcation
		MatrixOrderOptimization test = new MatrixOrderOptimization();
		for(int i = 2; i <= 9; i++) {
			int n = (int) Math.pow(2, i) + 1;
			time = System.nanoTime();
			int[] matricies = new int[n];
			for(int j = 0; j < n; j++) {
				// filled matrix with randomized numbers to 999
				matricies[j] = random.nextInt(999)+1;
			}

			time = System.nanoTime();
			// Iterative method
			SequentialMultiplication(matricies);
			// Got time for testing
			System.out.println((System.nanoTime() - time));
			// Printed out array
			System.out.println(Arrays.toString(matricies));
			time = System.nanoTime();
			// Chained Matrix Operation
			test.matrixChainOrder(matricies);
			// Got time for testing
			System.out.println((System.nanoTime() - time));
			// Printed the shortest path
			test.printOptimalParenthesizations();
		}
	}
	
	public static class MatrixOrderOptimization {
		protected int[][]m;
		protected int[][]s;

		public void matrixChainOrder(int[] p) {
			int n = p.length - 1;
			m = new int[n][n];
			s = new int[n][n];
			for (int i = 0; i < n; i++) {
				m[i] = new int[n];
				// Initally start at zero
				m[i][i] = 0;
				s[i] = new int[n];
			}
			// Loops to keep track 
			for (int ii = 1; ii < n; ii++) {
				for (int i = 0; i < n - ii; i++) {
					int j = i + ii;
					// Keep value at infinity for comparison
					m[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						int q = m[i][k] + m[k+1][j] + p[i]*p[k+1]*p[j+1];
						if (q < m[i][j]) {
							m[i][j] = q;
							s[i][j] = k;
						}
					}
				}
			}
		}
		public void printOptimalParenthesizations() {
			boolean[] inAResult = new boolean[s.length];
			printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
		}
		void printOptimalParenthesizations(int[][]s, int i, int j,  /* for pretty printing: */ boolean[] inAResult) {
			if (i != j) {
				printOptimalParenthesizations(s, i, s[i][j], inAResult);
				printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
//				System.out.println(i + " " + s[i][j]);
//				System.out.println(s[i][j] + 1 + " " + j);				
				String istr = inAResult[i] ? "_result " : " ";
				String jstr = inAResult[j] ? "_result " : " ";
				System.out.println(" A_" + i + istr + "* A_" + j + jstr);
				inAResult[i] = true;
				inAResult[j] = true;
			}
		}
	}

	// Matrix Multiplcation
	public static void SequentialMultiplication(int[] n) {
		for(int h = 0; h < n.length - 2; h++)
		{
			int multiplicandrow = h;
			int columnsandrows = h + 1;
			int multipliercolumns = h + 2;
			int [][] multiplicand = new int[multiplicandrow][columnsandrows];
			int [][] multiplier = new int[columnsandrows][multipliercolumns];
			int [][] product = new int[multiplicandrow][multipliercolumns];
			for(int i = 0; i < multiplicandrow; i++) {
				for(int j = 0; j < multipliercolumns; j++) {
					for(int k = 0; k < columnsandrows; k++) {
						product[i][j] += multiplicand[i][k] * multiplier[k][j];
					}
				}
			}
		}
	}
}
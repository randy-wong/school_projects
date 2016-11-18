import java.util.*;

public class CS331Project2
{
	final static int empty = 999;


	public static void main(String[] args) {
		int size = 5;	// Size or Number of Nodes in Graph
		int[][] graph = new int[size][size];	// Adjacency Matrix
		Random rand = new Random();
		for (int i = 0; i < size; i++)			// Creates an adjacency matrix
			for (int j = 0 + i; j < size; j++) {	// Reiterate for only the upper triangle
				if (i == j) {
					graph[i][j] = empty;	// Zeros in the diagonal
				}								// An empty value denotes an edge that does not exist
				else
				{
					int RandomDenseGraph = rand.nextInt(69) + 1;	// 70 will be maximum allowed graph
					graph[i][j] = RandomDenseGraph;		// Keeps the matrix symmetric
					graph[j][i] = RandomDenseGraph;		// Keeps the matrix symmetric
				}


//				else if (j == i + 1)
//				{
//					int RandomSparseGraph = rand.nextInt(69) + 1;	// 70 will be maximum allowed weight
//					graph[i][j] = RandomSparseGraph;		// Keeps the matrix symmetric
//					graph[j][i] = RandomSparseGraph;		// Keeps the matrix symmetric
//				}
//				else
//				{
//					graph[i][j] = empty;
//					graph[j][i] = empty;
//			}

			}
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
				if (graph[i][j] < empty)
					System.out.print(" " + graph[i][j] + " ");
				else
					System.out.print(" 0 ");

			System.out.println();

		}
		long time = System.nanoTime();
//		Prim(graph, size);
		Dijkstra.dijkstra(graph, size);
		System.out.println(System.nanoTime() - time);

	}

	static int nodes;
	static int[] connected;
	static boolean[] reached;

	public static void Prim(int graph[][], int size) {
		nodes = size;
		connected = new int[nodes];		// connected[x] = y means that the connected of node x is node y
		reached = new boolean[nodes];		// Initially, all nodes' "reached" values are false
		Random rand = new Random();
		int start = rand.nextInt(nodes);	// The source node is selected at random
		reached[start] = true;
		connected[start] = start;
		printReached();
		for (int h = 0; h < nodes - 1; h++)
		{
			int x = 0, y = 0;
			for (int i = 0; i < nodes; i++)
				for (int j = 0; j < nodes; j++)
					// Find the smallest available weight from reached nodes to unreached nodes
					if (reached[i] && !reached[j] && graph[i][j] < graph[x][y])
					{
						x = i;
						y = j;
					}
			System.out.println("Edge: (" + x + "," + y + "), Weight: " + graph[x][y]);
			// Mark the new node as reached and register the previous node as its connected
			connected[y] = x;
			reached[y] = true;
			printReached();
		}
		printMST();
	}

	private static void printMST()
	{
		System.out.println("Minimum Spanning Tree: ");
		for (int i = 0; i < nodes; i++) {
			System.out.println(connected[i] + " - " + i);
		}
	}

	private static void printReached()
	{
		System.out.print("Nodes in Containing Set: ");
		for (int i = 0; i < reached.length; i++) {
			if (reached[i]) {
				System.out.print(i + " ");
			}
		}
	}

	public static class Floyd
	{
		private static int[][] weight;
		// From psuedocode from class
		public static void floyd(int graph[][], int size)
		{
			nodes = size;
			weight = new int[nodes][nodes];

			for (int i = 0; i < nodes; i++)
				for (int j = 0; j < nodes; j++)
					weight[i][j] = graph[i][j];

			for (int h = 0; h < nodes; h++)
				for (int i = 0; i < nodes; i++)
					for (int j = 0; j < nodes; j++)
						if (i != j)
							if (weight[i][h] + weight[h][j] < weight[i][j])
								weight[i][j] = weight[i][h] + weight[h][j];

			printSP();
		}

		private static void printSP()
		{
			System.out.println("Source Nodes (row) to Destination Nodes (column):");

			for (int i = 0; i < nodes; i++)
			{
				for (int j = 0; j < nodes; j++)
					if (weight[i][j] < empty)
						System.out.print(weight[i][j] + "\t");
					else
						System.out.print("*\t");

				System.out.println();
			}
		}
	}

	public static class Dijkstra
	{
		private static int[] connected;
		private static boolean[] reached;
		final static int empty = 999;	// Need a number to represent non-existent edges

		public static void dijkstra(int graph[][], int size)
		{
			nodes = size;
			connected = new int[nodes];
			reached = new boolean[nodes];
			int[] path = new int[nodes];
			Random rand = new Random();
			int sourcenode = rand.nextInt(nodes);	// The starting node is selected at random
			reached[sourcenode] = true;	// Starting node is reached
			connected[sourcenode] = sourcenode;	// The starting node is not connected, so connect
			printReached1();
			for (int i = 0; i < nodes; i++) {
				path[i] = graph[sourcenode][i];
				if (graph[sourcenode][i] < empty) {
					connected[i] = sourcenode;
				}
			}
			for (int i = 0; i < nodes - 1; i++) {
				int next;
				int compare;
				for (next = 0; next < nodes; next++)
					if (!reached[next])
						break;
				// If the weight of node B is less than the weight of node A, make B new node
				for (compare = next + 1; compare < nodes; compare++)
					if (reached[compare] == false && path[compare] < path[next])
						next = compare;
				// Do not consider it in future iterations
				reached[next] = true;

				// If the distance from our new node + the distance to a subsequent node is less than the distance
				// directly to said subsequent node, use the first path rather than the second
				for (compare = 0; compare < nodes; compare++)
					if (reached[compare] == false)
						if (path[next] + graph[next][compare] < path[compare])
						{
							path[compare] = path[next] + graph[next][compare];
							connected[compare] = next;
						}
				printReached1();
			}

			printSP();
		}
	}
	private static void printSP()
	{
		System.out.println("Shortest path edges to all nodes from source node");

		// Print the edges necessary for the shortest path
		for (int i = 0; i < nodes; i++)
			System.out.println(Dijkstra.connected[i] + " - " + i);
	}

	private static void printReached1()
	{
		System.out.print("Nodes in Containing Set: ");
		for (int i = 0; i < Dijkstra.reached.length; i++)
			if (Dijkstra.reached[i])
				System.out.print(i + " ");
		System.out.println("\n");
	}
}

public class Kruskal {
	// The program is for adjacency matrix representation of the graph
	// Number of vertices in the graph
	// A utility function to find the vertex with minimum key value, from
	// the set of vertices not yet included in MST
	public static int minKey(int key[], boolean mstSet[], int size) {
		// Initialize min value
		int min = 999;
		int min_index = 0;

		for (int v = 0; v < size; v++) {
			if (mstSet[v] == false && key[v] < min) {
				min = key[v]; 
				min_index = v;
			}
		}
		return min_index;
	}

	// A utility function to print the constructed MST stored in parent[]
	public static void printMST(int parent[], int graph[][], int size)
	{
		System.out.printf("Edge   Weight\n");
		for (int i = 1; i < size; i++)
			System.out.printf("%d - %d    %d \n", parent[i], i, graph[i][parent[i]]);
	}

	// Function to construct and print MST for a graph represented using adjacency
	// matrix representation
	public static void primMST(int graph[][], int size)
	{
		int parent[] = new int[size]; // Array to store constructed MST
		int key[] = new int[size];   // Key values used to pick minimum weight edge in cut
		boolean mstSet[] = new boolean[size];  // To represent set of vertices not yet included in MST

		// Initialize all keys as empty
		for (int i = 0; i < size; i++)
		{
			key[i] = 999;
			mstSet[i] = false;
		}

		// Always include first 1st vertex in MST.
		key[0] = 0;     // Make key 0 so that this vertex is picked as first vertex
		parent[0] = -1; // First node is always root of MST 

		// The MST will have V vertices
		for (int count = 0; count < size-1; count++)
		{
			// Pick the minimum key vertex from the set of vertices
			// not yet included in MST
			int u = minKey(key, mstSet, size);

			// Add the picked vertex to the MST Set
			mstSet[u] = true;

			// Update key value and parent index of the adjacent vertices of
			// the picked vertex. Consider only those vertices which are not yet
			// included in MST
			for (int v = 0; v < size; v++)

				// graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices not yet included in MST
				// Update the key only if graph[u][v] is smaller than key[v]
				if (graph[u][v] != 999 && mstSet[v] == false && graph[u][v] <  key[v]) 
				{
					parent[v]  = u; 
					key[v] = graph[u][v];
				}
		}
		// print the constructed MST
		printMST(parent, graph, size);
	}
	// driver program to test above function
}

import java.io.IOException;
import java.util.*;
import graph.*;

public class Classification
{
	static ArrayList<HashSet<Integer>> ancestors;

	public static void main(String[] args) throws IOException
	{
		Scanner kb = new Scanner(System.in);
//		DfsGraph graph = new DfsGraph(kb.nextLine());
		DfsGraph graph = new EdgeTypes(kb.nextLine());
//		MyDfsGraph graph = new MyDfsGraph(kb.nextLine());
		System.out.println(graph);
		graph.dfs(0);
	}

	private static class EdgeTypes extends DfsGraph
	{
		public EdgeTypes(String arg0) throws IOException
		{
			super(arg0);
			ancestors = new ArrayList<HashSet<Integer>>(order);
			for(int i = 0; i < order; i++)
			{
				ancestors.add(new HashSet<Integer>());
			}
		}

		public void doMarked(int v, int w)
		{
			// System.out.printf("[%d, %d] Not a Tree Edge.\n", v, w);
			if(ancestors.get(v).contains(w))
			{
				System.out.printf("(%d, %d) Back Edge\n", v, w);
			}
			else if (ancestors.get(w).contains(v))
			{
				System.out.printf("(%d, %d) Forward Edge\n", v, w);
			}
			else
			{
				System.out.printf("(%d, %d) Cross Edge\n", v, w);
			}
		}

		public void doUnmarked(int v, int w)
		{
			ancestors.get(w).add(v);
			ancestors.get(w).addAll(ancestors.get(v));
			System.out.printf("(%d, %d) Tree Edge\n", v, w);
		}
	}
}
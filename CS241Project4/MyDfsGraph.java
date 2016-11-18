import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import graph.*;

public class MyDfsGraph extends Graph
{
	static ArrayList<HashSet<Integer>> ancestors;
	public MyDfsGraph(String arg0) throws IOException
	{
		super(arg0);
		ancestors = new ArrayList<HashSet<Integer>>(order);
		for(int i = 0; i < order; i++)
		{
			ancestors.add(new HashSet<Integer>());
		}
		persistentDfs();
	}
	public void dfs(int v)
	{
		markVertex(v);
		for(int i = 0; i < getNeighbors(v).length; i++)
		{
			int w = getNeighbors(v)[i];
			if(vertexMarked(w))
			{
				doMarked(v, w);
			}
			else
			{
				doUnmarked(v, w);
				dfs(w);
			}
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
	public void persistentDfs()
	{
		for(int i = 0; i < vertices.length; i++)
		{
			if(!vertices[i].isMarked())
			{
				dfs(i);
			}
		}
	}
}
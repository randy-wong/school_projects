public class Set_WithoutADT
{
	public static void main(String[] args)
	{

		final int limit = 15;
		boolean change = true;

		/*******************************
		 * BLOCK B1 Initialize
		 *******************************/

		int[] inB1 = {};
		int[] genB1 = {1,2,3};
		int[] killB1 = {4,5,6,7};	
		int[] predB1 ={1};
		int[] outB1 = new int[limit];
		int[] new_genB1 = new int[limit];
		int[] new_killB1 = new int[limit];	

		new_genB1 = assignTo(genB1);
		new_killB1 = assignTo(killB1);
		outB1 = copy(new_genB1);


		/*******************************
		 * BLOCK B2: Initialize
		 *******************************/

		int[] inB2 = {};
		int[] genB2 = {4,5};
		int[] killB2 = {1,2,7};
		int[] outB2 = new int[limit];
		int[] predB2 ={1,3,4};
		int[] new_genB2 = new int[limit];
		int[] new_killB2 = new int[limit];

		new_genB2 = assignTo(genB2);
		new_killB2 = assignTo(killB2);
		outB2 = copy(new_genB2);


		/*******************************
		 * BLOCK B3 Initialize
		 *******************************/

		int[] inB3 = {};
		int[] genB3 = {6};
		int[] killB3 = {3};
		int[] outB3 = new int[limit];

		int[] predB3 ={2};
		int[] new_genB3 = new int[limit];
		int[] new_killB3 = new int[limit];

		new_genB3 = assignTo(genB3);
		new_killB3 = assignTo(killB3);
		outB3 = copy(new_genB3);	

		/*******************************
		 * BLOCK B4 Initialize
		 *******************************/

		int[] inB4 = {};
		int[] genB4 = {7};
		int[] killB4 = {1,4};
		int[] outB4 = new int[limit];
		int[] predB4 ={2};
		int[] new_genB4 = new int[limit];
		int[] new_killB4 = new int[limit];

		new_genB4 = assignTo(genB4);
		new_killB4 = assignTo(killB4);
		outB4 = copy(new_genB4);	

		System.out.print("\n\n");

		/******************************************************
		  Algorithm that returns in[B] and out[B] for each block
		 ******************************************************/

		while(change)
		{
			change = false;

			System.out.println( "\n\n\tBlock B1    ");

			System.out.print("gen:\t");
			printSet(new_genB1);		
			System.out.print("out:\t");		
			printSet(outB1);
			printPredecessor(predB1);
			System.out.print("kill:\t");
			printSet(new_killB1);
			inB1 = union(inB1,inB1);
			setResult(inB1,outB1, new_genB1, new_killB1,change);

			System.out.println( "\n\n\tBlock B2    ");

			System.out.print("gen:\t");
			printSet(new_genB2);
			System.out.print("out:\t");	
			printSet(outB2);
			printPredecessor(predB2);
			System.out.print("kill:\t");			
			printSet(new_killB2);

			inB2 = union(outB1,outB3);
			inB2 = union(inB2,outB4);
			outB2 =setResult(inB2,outB2, new_genB2, new_killB2,change);

			System.out.println( "\n\n\tBlock B3    ");

			System.out.print("gen:\t");
			printSet(new_genB3);		
			System.out.print("out:\t");
			printSet(outB3);
			printPredecessor(predB3);
			System.out.print("kill:\t");
			printSet(new_killB3);
			inB3 = union(outB2,outB2);
			setResult(inB3,outB3, new_genB3, new_killB3,change);

			System.out.println( "\n\n\tBlock B4    ");

			System.out.print("gen:\t");
			printSet(new_genB4);		
			System.out.print("out:\t");		
			printSet(outB4);
			printPredecessor(predB4);
			System.out.print("kill:\t");
			printSet(new_killB4);

			inB4 = union(outB2,outB2);
			setResult(inB4,outB4, new_genB4, new_killB4,change);

		}		
	}
	private static int[] setResult(int[] in, int[] out, int[] gen,
			int[] new_killB1,boolean change) 
	{

		int[] oldoutB1 = new int[15];
		int[] diff = new int[15];

		System.out.print("in:\t");
		printSet(in);

		oldoutB1 = copy(out);
		System.out.print("oldout:\t");
		printSet(oldoutB1);

		diff = difference(in,new_killB1);
		out = union(gen,diff );

		System.out.print("out:\t");
		printSet(out);

		if(!equals(out, oldoutB1))
			change = true;	
		return out;
	}
	/******************************************************
	compare two sets and return true if two sets are 
	equals.
	 ******************************************************/
	private static boolean equals(int[] out, int[] oldout)
	{
		boolean change=false;
		for(int i=0; i<out.length; i++)
		{
			if(out[i] == oldout[i])
				change= true;

		}
		return change;

	}
	/******************************************************
 take the difference of two sets. in[B] - kill[B]
	 ******************************************************/
	private static  int[] difference(int[] in, int[] kill)
	{
		int[] diffSet = new int[15];

		for(int i =0; i<in.length; i++)
		{
			if(in[i] == 1 && kill[i] != 1 )
			{
				diffSet[i]= 1;
			}
		}
		System.out.print("diffSet ");
		printSet(diffSet);

		return diffSet;
	}
	/******************************************************
 Take union of two sets and return a new set. 
 Union of predecessors.
	 ******************************************************/
	private static int[] union(int[] outB1, int[] outB2)
	{
		int outP[] = new int[15];

		for(int i =0; i<outB1.length; i++)
		{
			if(outB1[i] == 1)
				outP[i] = 1;			
		}
		for(int i =0; i<outB1.length; i++)
		{
			if(outB2[i] == 1)
				outP[i] = 1;			
		}

		return outP;
	}
	/******************************************************
	 * print predecessor for each block
	 ******************************************************/	
	private static void printPredecessor(int[] predB) 
	{
		System.out.print("pred\t");
		for(int i=0; i<predB.length; i++)
		{
			System.out.print("B");
			System.out.print(predB[i] + " ");
		}

		System.out.print("\n");

	}
	/*******************************************************
	 * make an exact copy of a set
	 *******************************************************/
	private static int[] copy(int[] gen) 
	{
		int[] out = new int[15];

		for(int i=0; i<gen.length; i++)
			out[i] = gen[i];
		return out;
	}

	/******************************************************
	Print sets
	 ******************************************************/
	private static void printSet(int[] b)
	{
		System.out.print("{ ");
		for(int i=0; i<b.length; i++)
		{
			if(b[i] ==1)
			{
				System.out.print("d");
				System.out.print(i + " ");

				if(b[i+1] == 1)
				{
					System.out.print(",");			
				}			
			}
		}
		System.out.print("}");
		System.out.println();
	}
	/*****************************************************
	assign 1 if array contains data
	 * @return 
	 *******************************************************/
	private static int[] assignTo(int[] B) 
	{
		int a[] = new int[15];
		for(int i =0; i< B.length; i++)
			a[B[i]]=1;
		return a;

	}

}


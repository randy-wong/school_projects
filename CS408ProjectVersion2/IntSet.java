public class IntSet
{
	private    int[] sets = new int[128];

	//****************************************************************************************
	//	Default Constructor initialize an empty set of an array
	//****************************************************************************************
	public IntSet() {}

	//****************************************************************************************
	//Constructor with one element
	//****************************************************************************************
	public IntSet(int e)
	{	
		sets[e] = 1; // store e element into index position as 1;
	}

	//****************************************************************************************
	//Constructor with an array as a parameter
	//****************************************************************************************
	public IntSet(int[] s1)
	{	
		for(int i=0; i<s1.length; i++)
		{
			int temp = s1[i];
			sets[temp] =1;
		}	
	}

	//***********************************************************************************
	//		UNION
	//Union of two IntSet objects.
	//***********************************************************************************

	public IntSet Union(IntSet c2)
	{
		IntSet unionSet = new IntSet(); // Store temporary set values.

		for(int i=0; i<sets.length; i++)
		{
			if((sets[i]==1)||(c2.sets[i]==1))

				// store new set in temporary object. the original array wont change.
				unionSet.sets[i] =1; 			
		}

		return unionSet;
	}
	//************************************************************************************
	//Copy one IntSet to another.
	//************************************************************************************
	public IntSet copy(IntSet s)

	{			
		IntSet copySet = new IntSet();

		for(int i=0; i<sets.length; i++)
		{
			if(s.sets[i] ==1)
				copySet.sets[i] =1;					
		}			
		return copySet;			
	} 

	public IntSet(IntSet s)
	{	
		for(int i=0; i<sets.length; i++)
		{
			if(s.sets[i] ==1)
				sets[i] =1;					
		}	
	}
	//***********************************************************************************
	//	SUB
	//Difference of two IntSet objects.
	//***********************************************************************************
	public IntSet difference(IntSet c4)
	{
		IntSet DiffSet = new IntSet();

		for(int i=0; i<sets.length; i++)
		{
			if(sets[i]==1)
			{
				if(c4.sets[i] == 0)
					DiffSet.sets[i] = 1;
				else 
					DiffSet.sets[i]=0;
			}
		}

		return DiffSet;
	}

	//***********************************************************************************
	//	Equals
	//Compare two IntSet Objects
	//***********************************************************************************
	public boolean equals(IntSet s)
	{
		IntSet equalSets = new IntSet();

		for(int i=0; i<sets.length; i++)
		{
			if(sets[i] ==1)
			{
				int temp = sets[i];
				if(s.sets[temp] == 1)
					return true;
				equalSets.sets[i]=1;
			}
		}				
		return false;		

	} 

	public String toString()
	{
		String setResult2 = "";
		String setD = "d";
		String setResult1 = " {";

		for(int i=0; i < sets.length; i++)
		{	
			if(sets[i]==1)
			{
				setResult2 += setD + (i);

				for(int j =i; j<sets.length-1; j++)
					if(sets[j+1]==1)
					{
						setResult2 += ",";
						break;											
					}
			}
		}
		String setResult3 = "}";

		return setResult1 + setResult2 + setResult3;
	}
}


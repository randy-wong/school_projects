/*
 * This program provides the skeleton to process multiple files from a directory
 * The directory name is provided in args[0] as I call "java Testdir hashingdata"
 */
import java.io.*;
import java.util.*;

class Project3{
	public static void main(String[] args) throws IOException
	{
		if (args.length < 1)
		{
			System.out.println("Error: Directory name is missing");
			System.out.println("Usage: java scoreProcess directory_name");
			return;
		}
		File directory = new File(args[0]); // args[0] contains the directory name
		if(!directory.exists())	// makes sure the directory name is there
		{
			System.out.println("Error: Directory name is missing, try again");
			return;
		}
		File[] files = directory.listFiles(); // get the list of files from that directory
		Scanner input;	// scanner for files
		Scanner kb = new Scanner(System.in);	// scanner for team input
		HashTable hashTable = new HashTable();	// hash table object
		String team = "0";	// initializing team 
		// process the arguments stores in args
		
		for (int i = 0; i < files.length; i++)
		{
			input = new Scanner(files[i]);

			// no error checking done here, add your own
			String key;
			Double value;

			while (input.hasNext())
			{
				key = "";
				while (!input.hasNextDouble())
				{
					key += input.next() + " ";
				}
				key = key.substring(0, key.length() - 1);
				value = new Double(input.next());
				System.out.println("Key: " + key + " Value: " + value);
				// #### insert the (key, value) pair into your hash table
				hashTable.put(key, value); // inputs names and values, creates table and entries
			}
		}

		System.out.println("# of collisions: " + hashTable.collision); // prints out collision
		System.out.println("Size of table: " + hashTable.size);	// prints out size of hash table
		System.out.printf ("\nLoad factor: %5.5f %n", (double)hashTable.number/hashTable.size); // print format load factor
		System.out.println("# of Names: " + hashTable.number);	// prints out number of names
		System.out.println("Minimum average: " + hashTable.minimumAverage());	// prints out the minimum average
		String[] min = hashTable.names(hashTable.minimumAverage());	// creates a string of values with minimum average
		for (int i1 = 0; i1 < min.length; i1++)	// searches and prints out value with the minimum average
			System.out.println(min[i1]);
		System.out.println("Maximum average: " + hashTable.maximumAverage());	// prints out the maximum average
		String[] max = hashTable.names(hashTable.maximumAverage());	// creates a string of values with maximum average
		for (int i1 = 0; i1 < max.length; i1++)	// searches and prints out value with the maximum average
			System.out.println(max[i1]);
		do
		{
			try{
				System.out.println("Enter name, EXIT to exit program: ");
				team = kb.nextLine();	//	input	
				System.out.println(hashTable.get(team).key + ": Average: " + 
						hashTable.get(team).value/hashTable.get(team).count + " # Scores: " 
						+ hashTable.get(team).count);	//	prints out key, average, count, scores
			}
			catch(NullPointerException e)	// exception
			{
				if(team.equals("EXIT"))
					return;
				System.out.println("Error, " + team + " team name not found, please input team name again.");
			}
		}
		while(!team.equals("EXIT"));	// exits program

	}

}
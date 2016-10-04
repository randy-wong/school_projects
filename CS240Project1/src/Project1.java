import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Project1	//this class is a random name selector, it reads a file and saves it in a data structure, it contains a user interface
{
	public static void main(String[] args) throws Exception
	{
		String name[] = new String[40];	//a string array to input the names into the array
		int counter[] = new int[40];	//an integer array to count how many times something has been called from the generator
		for(int k = 0; k < counter.length; k++)	//loop that sets all the indexes of counter to zero so it can be incremented for later use
		{
			counter[k] = 0;
		}
		int generated = 0;	//integer variable that is used to count how many names were called/generated
		int repetition = 0;	//integer variable that is used to count how many times a repetition was encountered
		int holder;	//used to track of what random number were called and used in multiple arrays
		int numNames = 0;	//integer variable that is used to count how many names were in a specified file
		int listNum = 0;	//integer variable that is used to list the order of what was called
		String input;	//variable that is used for the scanner for the user input
		Random rand = new Random();
		try	//try-catch block to ensure that the file works correctly
		{
			Scanner inFile = new Scanner(new FileReader("name.txt"));	//scanner that reads specified file
			do
			{
				name[numNames] = inFile.nextLine();	//sets the name from the file to the array
				++numNames;	//counts how many names there are(how many lines there are)
			}
			while (inFile.hasNextLine());	//condition to ensure that there are names to put into the array
			numNames -= 1;	//this variable is used to count an array so it naturally has to be one less so it can count as an index
			System.out.println("Number of students in class: " + numNames);
			holder = rand.nextInt(numNames-1);	//generates a random integer value
			counter[holder] += 1;	//sets the randomly selected index in the array for counting repetition
			System.out.println(name[holder]);
			Scanner kb = new Scanner(System.in);	//scanner for user interface
			do	//do-while loop for continued user interface
			{
				input = kb.nextLine();	//specific command that generates a random name
				if (input.equals("n"))
				{
					holder = rand.nextInt(numNames+1);	//generates a random value within the amount of names there are
					System.out.println(name[holder]);	//prints out the array index of that value
					counter[holder] +=1;	//increments the amount of times that name has been called
				}
			if (input.equals("list"))	//specific command that lists the names previously called and the amount of times it has been called
			{
				listNum = 0;	//lists starts are zero
				for (int j = 0; j < numNames; j++)	//for loop that goes through the amount of names that could have been called
				{
					if (counter[j] > 0)	//if the name has been called once then it will show the specific message of how many times it was called and in the order of when it was called
						System.out.println(++listNum + ". " + name[j] + " (" + counter[j] +")");
				}
			}
				if (input.equals("help"))	//specific command that shows other commands to help user
				{
					System.out.println(    "\nn       Next random name\nexit    Exit the program\nlist     List all the unique names that have been called as well as the number of times\nhelp   Display this message");
				}
			}
			while (!(input.equals("exit")));	//specific command that exits the program and shows results of data
				if (input.equals("exit"))
				{
					for (int i = 0; i < numNames; i++)	//loop that goes through what could have been called
					{
						generated += counter[i];
						if (counter[i] > 1)	//it finds if it has been called more than once
						{
							repetition += counter[i] - 1;	//and counts how many more times it has been called to find how many repetition there are
						}
					}
					System.out.println("The program has generated " + generated + " name(s) with " + repetition + " repetition(s).");
				}
		}
			catch (FileNotFoundException e)
			{
			System.out.println("Restart program with proper file in folder.");	//shows error message if file is not there
			}
	}
}
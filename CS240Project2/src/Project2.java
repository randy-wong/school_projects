import java.util.*;

public class Project2 
{
	
	public static void main(String[] args) throws Exception
	{
	
		Scanner kb = new Scanner(System.in); // scanner for input from user for set A
		boolean duplicate; // used for a while condition for input of same object twice in a set
		System.out.println("Enter the size of set A."); // asks input from user
		try
		{ // try-catch block incase the user tries to input something that is not an integer
			int size = kb.nextInt(); // gets an integer from user for a size of the set
			Set a = new Set(); // creates the first set you are working with
			a.setSize(size); // sets the size for set A
			for (int i = 0; i < size; i++) // loops that goes thru for set A that gets all the information from the user
			{
				System.out.println("Enter an element for the set A");
				duplicate = a.addElement(kb.next()); // adds element into set A
				while (duplicate == false) // gives the user an error message if object is input twice in set A
				{
					System.out.println("Enter a different element for set A.");
					duplicate = a.addElement(kb.next()); // gets the object again from the user
				}
			}	
		
			System.out.println("Enter the size of the set B.");  // asks input from user for set B
			size = kb.nextInt(); // gets an integer from user for a size of the set
			Set b = new Set(); // creates the second set you are working with
			b.setSize(size); // sets the size for set B
			for (int i = 0; i < size; i++)
			{
				System.out.println("Enter a number for the set B");
				duplicate = b.addElement(kb.next()); // adds the element into set B
				while (duplicate == false) // gives the user an error message if object is input twice in set B
				{
					System.out.println("Enter a different element for set B.");
					duplicate = b.addElement(kb.next()); // gets the object again from the user
				}
			}
			System.out.println("Passing in A to compare to B");
			System.out.println("Set A: " + a.toString()); // prints out set A
			System.out.println("Set B: " + b.toString()); // prints out set B
			System.out.println("Set A is a subset of B: " + a.subSetOf(b)); // prints out true if set A is a subset of set B else false
			System.out.println("Both sets are equal: " + a.isEqual(b)); // prints out false if set A is equal set B
			System.out.println("Union set " + a.union(b)); // prints out the union set of set A and B
			System.out.println("Intersection set " + a.intersection(b)); // prints out the intersection set of A and B
			System.out.println("Complement set " + a.complement(b)); // prints out the complement set of A and B
			}
		catch (InputMismatchException e) // type of except if user inputs the wrong input for size
		{
			System.out.println("Restart program with size as an integer).");	//  shows error message if integer is not input
		} 
	}
}
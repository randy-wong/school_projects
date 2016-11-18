import java.io.*;
import java.util.*;

public class ShoppingCartManagement
{
	public static void main(String[] args) throws IOException
	{

		// Get the items and order file names.
		File items_file = new File(args[0]);
		File orders_file = new File(args[1]);

		// Open the files.
		Scanner input_items = new Scanner(items_file);
		Scanner input_orders = new Scanner(orders_file);

		// String that will be place holders for the string function .next().
		String name, item;

		// Create a MyTreeMap object to store file item tokens.
		MyTreeMap<String, Integer> items_map = new MyTreeMap<String, Integer>();

		// Create a MyTreeMap object to store file order tokens.
		MyTreeMap<String, ArrayList<String>> orders_map = new MyTreeMap<String, ArrayList<String>>();

		// Stores all the items into a MyTreeMap.
		while (input_items.hasNextLine())
			items_map.put(input_items.next(), Integer.parseInt(input_items.next().substring(1)));

		// Reads thru each line in each file.
		while (input_orders.hasNextLine())
		{
			// Implements the price map and each shopper's cart. Use and ArrayList to store the items in the cart.
			switch (input_orders.next())
			{
			case "add":
				name = input_orders.next();
				item = input_orders.next();
				System.out.println(name + " added a " + item + " to their cart.");
				// If the name exists in the MyTreeMap, and item does not exist in the ArrayList, then add the item to the ArrayList.
				if (orders_map.containsKey(name))
				{
					if(!orders_map.get(name).contains(item))
						orders_map.get(name).add(item);
				}
				else 
				{
					// Creates an ArrayList for the MyTreeMap if the name is not in the orders MyTreeMap.
					ArrayList<String> cart = new ArrayList<String>();
					cart.add(item);
					orders_map.put(name, cart);
				}
				break;

			case "delete":
                name = input_orders.next();
                item = input_orders.next();
                System.out.println(name + " deleted a " + item +" from their cart.");
                if (orders_map.containsKey(name))
                   orders_map.get(name).remove(item);
				else
					System.out.println("Customer " + name + " does not exist.");
				break;
				
			case "view":
				name = input_orders.next();
				System.out.println("Viewing " + name + "'s shopping cart.");
				// An loop that checks through a specific MyTreeMap value and prints out it from ArrayList indexes.
 				ArrayList<String> list = orders_map.get(name);
				
				for (int i=0; i < list.size(); i++)
					System.out.println("Item: " + orders_map.get(name).get(i) + " $" + items_map.get(orders_map.get(name).get(i)));
				break;

			case "checkout":
				name = input_orders.next();
				int cost = 0;
				System.out.println("Checking out " + name + ".");
				// An loop that checks through a specific MyTreeMap value and prints out it from ArrayList indexes.
				for (int i = 0; i < orders_map.get(name).size(); i++) 
				{
					System.out.println("Item: " + orders_map.get(name).get(i) + " $" + items_map.get(orders_map.get(name).get(i)));
					cost = items_map.get(orders_map.get(name).get(i)) + cost;
				}
				System.out.println("Checkout Total for " + name + ": $" + cost);
				break;

			default:
				break;
			}
		}
	}
}

public class HashTable 
{
	int collision = 0;	// collision
	int size = 37;	// size of the hash table
	Entry[] table = new Entry[size];	// 
	int number = 0;	// number of names

	/* Returns a hash value for the specified object.  */
	static int hash(String key)
	{

		int hash = 0;	// initializes hash
		int n = key.length();	// gets the length of the key
		for (int i = 0; i < n; i++)	// goes through each space in the string
		{
			hash = (hash << 6) | (hash >>> 26); // 5-bit cyclic shift of the running sum
			hash += (int) key.charAt(i); // add in next character
			hash = 31*hash + key.charAt(i); // hash function copied from lecture notes Ex2
		}
		hash = ((hash)%31*7)%37; // compression function similar to the MAD method
		return hash; // returns a hashed number
	}

	/* Returns index for hash code h. */
	static int indexFor(int h, int length)
	{
		return h & (length-1);	// returns the hash value and the length of the string
	}

	/* Returns the value to which the specified key is mapped in this identity
	hash map, or null if the map contains no mapping for this key. */
	public Entry get(String key)	// method that gets the key/returns the values inside the key, helper method
	{
		int hash = hash(key);	// gets the hash value
		int i = indexFor(hash, table.length);	// 
		Entry e = table[i];
		while (true)	// checks every entry within the separate chaining
		{
			if (e == null)	// if the entry is null, return null
				return null;
			if (e.hash == hash && key.equals(e.key))	// if the entry exists, check if it is the same for searched key
				return e;	// returns e if it is equal
			e = e.next;	// next entry
		}
	}

	/**
	 * Associates the specified value with the specified key in this hash table.
	 * If the hash table contained the key, the old value is added
	 * Checks if the key 
	 */
	public void put(String key, double value)
	{
		int hash = hash(key);
		int i = indexFor(hash, table.length);
		Entry curr = table[i];	// used to traverse through entries
		Entry prev = null;	// used to traverse through entries

		if (table[i] == null)
		{
			table[i] = new Entry(hash, key, value, null);	// creates a new entry
			number++;	//new name is added
			return;	// exits out of the method
		}
		while (curr != null)	// there exists a hash for the given entry
		{
			if (curr.hash == hash && key.equals(curr.key))	// if the key exists in the seperate chaining
			{
				curr.value += value;	// adds up the values
				curr.count++;	// used for averaging
				return;	// exits out of method
			}
			prev = curr;	// makes sure it is the second to last when it comes time to add new entry
			curr = curr.next;	// goes through until it is null
		}
		prev.next = new Entry(hash, key, value, null);	// new entry is added onto the last entry
		collision++;	// there is a collision
		number++;	// the number has increased
	}

	public double minimumAverage()
	{
		double minimum = Double.MAX_VALUE; // For a compared value

		for (int i = 0; i < table.length; i++)	// Goes through the hash table
			for (Entry e = table[i]; e != null; e = e.next)	// Checks if there were collisions and goes thru it
				if(minimum > e.value/e.count)	// compares values
					minimum = e.value/e.count;	// sets values
		return minimum;	// returns value
	}

	public double maximumAverage()	// Similar to minimum
	{
		double maximum= Double.MIN_VALUE;

		for (int i = 0; i < table.length; i++)
			for (Entry e = table[i]; e != null; e = e.next)
				if(maximum < e.value/e.count)
					maximum = e.value/e.count;
		return maximum;
	}

	public String[] names(double average)	// returns an array of names with the average
	{
		String[] list = new String[size];	// makes a string list
		int index = 0;	// indexing for the array

		for (int i = 0; i < table.length; i++)	// goes through the hash table
			for (Entry e = table[i]; e != null; e = e.next)	// goes through the entries
				if(average == e.value/e.count)	// compares entries
				{
					list[index] = e.key;	// sets key into the array
					index++;	// moves on the comparisons
				}
		String[] list2 = new String[index];	// string to make a smaller list
		for(int i = 0; i < index; i++)	// loop
			list2[i] = list[i];	// stores keys into the new array
		return list2;	// returns the list of keys with all the averages stored within
	}

	/* Each entry stores a (key, value) pair, it's hash value and
	 * a reference to the next entry with the same hash value */
	class Entry
	{
		String key;
		double value;
		final int hash;
		int count;
		Entry next;

		/**
		 * Create new entry.
		 */
		Entry(int h, String k, double v, Entry n)
		{
			value = v;
			next = n;
			key = k;
			hash = h;
			count = 1;
		}

	}
}
public class Set
{
	private Node head; // dummy head
	private int size; // node size

	public Set() // constructor for set
	{
		head = new Node(null, null); // creates the dummy head
		size = 0; // sets the size to zero
	}
	
	public Set(int x) // constructor for set
	{
		head = new Node(null, null); // creates the dummy head
		size = x; // sets the size to zero
	}

	public void setSize(int x) // size method that sets the size from the driver program
	{
		size = x;
	}
	
	public boolean isEmpty() // checks if the set is empty
	{
		return (size == 0); // if the size is 0 then return a true value
	}
	
	public boolean contain(Object a) // Returns true if the given object is contained in the set and false otherwise. 
	{
		if (isEmpty()) // checks if it is empty because you cannot contain an object from an empty list
		{
			return false;
		}
		Node temp = head.next; // temporary node that starts at the head
		while(temp != null) // end of the list condition
		{
			if(temp.object.equals(a)) // condition to check if the objects are equal
			{
				return true;
			}
			temp = temp.next; // used to go next in the list
		}
		return false;
	}
	
	public boolean remove(Object a) // Returns true if the node containing the object is removed from the set and false otherwise.
	{
		if (isEmpty()) // checks if it is empty because you cannot remove an object from an empty list
		{
			return false;
		}
		Node curr = head.next; // the second place holder
		Node prev = head; // the first place holder
		while(curr.next != null) // end of the list condition
		{
			if(curr.object.equals(a)) // condition to check if the objects are equal
			{
				prev.next = curr.next; // fills in the gap when removing 
				size--; // the size is less one after removing
				return true;
			}
			curr = curr.next; // the second place holder's next node
			prev = prev.next; // the first place holder's next node
		}
		return false;
	}

	public boolean addElement(Object a) // Returns false if element not added because it is already in the set and true if the element is added.
	{
		Node curr = head.next;// the second place holder
		Node prev = head;// the first place holder
		while(curr != null) // end of the list condition
		{
			if(curr.object.equals(a)) //condition to check if the objects are equal
			{
				return false;
			}
			curr = curr.next; // the second place holder's next node
			prev = prev.next; // the first place holder's next node
		}
		Node add = new Node(a, null); // new node if adding
		prev.next = add; // new node is added to the end
		size++; // the size is one more after adding
		return true;
	}
	
	public boolean subSetOf(Set b)
	{
		if(this.size > b.size) // the subset that is compared to cannot be smaller than the set passed in
		{
			return false;
		}
		Node acurr = this.head.next; // the first node of set A
		while (acurr != null) // end of the list condition
		{
			if(!b.contain(acurr.object)) // if the given object is not contained in the set by comparing the nodes in the set to the all of the first set object's value
			{
				return false;
			}
			acurr = acurr.next; // progresses the list
		}
		return true;
	}

	public boolean isEqual(Set b)
	{
		return (this.subSetOf(b) && b.subSetOf(this)); // if they are subsets of each other then they are equal
	}
	
	public Set union(Set b)
	{
		Set c = new Set(); // set that is being passed out
		Node acurr = this.head.next; // head of a
		Node bcurr = b.head.next; // head of b
		while (acurr != null) // end of the list condition
		{
			c.addElement(acurr.object); // adds element if it is already not added for set A
			acurr = acurr.next; // progresses the first list
		}
		while (bcurr != null) // end of the list condition
		{
			c.addElement(bcurr.object); // adds element if it not already added for set B
			bcurr = bcurr.next; // progresses the second list
		}
		return c;
	}

	public Set intersection(Set b)
	{
		Set c = new Set(); // set that is being passed out
		Node acurr = this.head.next; // head of a
		while (acurr != null) // end of the list condition
		{
			if(b.contain(acurr.object)) // if the given object is contained in the set by comparing the nodes in the set to the all of the first set object's value
			{
				c.addElement(acurr.object); // adds the element
			}
			acurr = acurr.next; // progresses the list
		}	
		return c;
	}

	public Set complement(Set b)
	{
		Set c = new Set(); // set that is being passed out
		Node acurr = this.head.next; // head of a
		while (acurr != null) // end of the list condition
		{
			if(!b.contain(acurr.object)) // if the given object is not contained in the set by comparing the nodes in the set to the all of the first set object's value
			{
				c.addElement(acurr.object); // adds the element
			}
			acurr = acurr.next; // progresses the list
		}	
		return c;
	}
		
	public int size() 
	{
		return size;
	}
	
	public String toString()
	{
		String str = "{";
		Node temp = this.head.next;
		while(temp != null) // end of the list condition
		{
			str += " " + temp.object; // adds the element in the set
			temp = temp.next; // progresses the list
		}
		str += " }";
		return str;
	}
	
	private static class Node 
	{ // the Node class is defined within the Sets class
		private Object object;
		private Node next;
		Node(Object object, Node next) 
		{
			this.object = object;
			this.next = next;
		}
	}
}
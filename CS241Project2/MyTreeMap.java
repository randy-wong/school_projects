import tree.BinaryTree;
//import tree.*;

public class MyTreeMap <E>
{

	//	public MyTreeMap(E root, BinaryTree<E> left, BinaryTree<E> right){}

	// Return element if found or else null.
	public E search(E element, BinaryTree tree)
	{
		// If tree is empty, return null.
		if(tree.getRoot() == null)
			return null;
		// Let r be the element at the root.
		E root = (E) tree.getRoot();
		// If e = r return r
		if(element.equals(root))
			return root;
		// Else return search(e, left)
		else if(element.compareTo(root))
			return (search(element, tree.getLeft()));
		// Else return search(e, right)
		else
			return (search(element, tree.getRight()));
	}
	public void insert(E element)
	{
		// If tree is empty, create new tree with e at root and return null
		if(tree.getRoot() == null)
			return null;
		{

		}
	}
	public void insert(E element, BinaryTree tree)
	{
		E root = TreeMap<E>;
		// Let r be element at root.
		tree.setRoot(<E>root);

	}


}

/*
 * Written by Okim Nsor 
 * This program replaces Java's Built in Tree Map
 */
import java.io.*;
import java.util.*;

import tree.BinaryTree;


public class Mytreemap <K extends Comparable<K>,V>
{
//-------------------------------------------------------------------------------------------
	private BinaryTree<Element> tree;
	TreeSet<K> keySet;	
//-------------------------------------------------------------------------------------------
	public V add(K key, V value)
	{
		if (put(key, value)==null)
		{
			System.out.println("Successfully added " + key+".");
		}
		 return put(key, value);	
	 }
//-------------------------------------------------------------------------------------------
	 public V find(K key)
	 {
		if (tree == null ||containsKey(key) == null) {return null;}
		return get(key);
	 }
//-------------------------------------------------------------------------------------------
	 public Element delete(Element oldElement, BinaryTree<Element> tree)
	 {
	 	if(tree == null){return null;}
	 	Element root = tree.getRoot();
	 	if (oldElement.key.compareTo(root.key) > 0) {return delete(oldElement, tree.getRight());}
	 	if (oldElement.key.compareTo(root.key) < 0) {return delete(oldElement, tree.getLeft());}
	 	if (oldElement.key.compareTo(root.key) == 0)
	 	{
	 		Element temp = tree.getRoot();
	 		if (tree.isLeaf())
	 		{ 
	 		 	tree = null;
	 		 	return temp;
	 		}
	 		if(tree.getLeft() == null)
	 		{ 
	 			tree = tree.getRight();
	 			return temp;
	 		}
	 		if(tree.getRight() == null)
	 		{ 
	 			tree = tree.getLeft();
	 			return temp;
	 		}
	 		else
	 		{
	 			BinaryTree<Element> ptr = tree.getRight();
	 			while(ptr.getLeft() != null)
	 			{
	 				ptr = ptr.getLeft();
	 			}
				tree.setRoot(ptr.getRoot());
				tree = tree.getRight();
	 			while(tree.getLeft() != null)
	 			{
	 				tree = tree.getLeft();
	 			}
				tree = tree.getRight();
	 			return temp;
	 		} 		
	 	}
		return null;
	 }
//-------------------------------------------------------------------------------------------
	 public void list()
	 {
		 System.out.println();
		  for (K key: keySet())
		   {
			   System.out.printf("%-25s\t\t%-12s\n", key, get(key));
		   }
	 }
//-------------------------------------------------------------------------------------------	
	public V containsKey(K key)
	{
		if(key == null){return null;}
		Element temp = search(key, tree);
		if(temp == null) {return null;}
		return temp.value;
	}
//-------------------------------------------------------------------------------------------	
	public V put(K key, V value)
	{
		Element temp = insert(new Element(key,value));
		if(temp != null) return temp.value;
		return null;
	}
//-------------------------------------------------------------------------------------------
	public V get(K key)
	{
		if(tree == null) return null;
		return containsKey(key);
	}
//-------------------------------------------------------------------------------------------	
	public V remove(K key)
	{
		if(tree == null) return null;
		if(containsKey(key) == null) {return null;}
		Element temp = delete(new Element(key, null), tree);
		return temp.value; //Returns value of removed element
	}
//-------------------------------------------------------------------------------------------
	public TreeSet<K> keySet() //Used to print list in order
	{
		if(keySet == null) {keySet = new TreeSet <K>();}
		inOrder(tree);
		return keySet;
	}
//-------------------------------------------------------------------------------------------
	private Element search(K key, BinaryTree<Element> tree)
	{//Recursively calls appropriate subtree until the tree is empty or matches with root 
		if(tree == null) return null; 
		if(key.compareTo(tree.getRoot().key) == 0) return tree.getRoot();
		else if (key.compareTo(tree.getRoot().key) < 0) return search(key,tree.getLeft());
		return search(key,tree.getRight());
	}
//-------------------------------------------------------------------------------------------
	private Element insert(Element newElement) //Insert for the first element creates a new tree
	{
		if(tree == null)
		{
			tree = new BinaryTree<Element> (newElement);
			return null;
		}
		return insert(newElement, tree); //If it isnt empty then go to the other insert function
	}
//-------------------------------------------------------------------------------------------
	private Element insert(Element newElement, BinaryTree<Element> tree) // Continuation from above insert method
	{
		Element root = tree.getRoot();
		if(newElement.key.compareTo(root.key) == 0)
		{
			tree.setRoot(newElement);
			return root;
		}
		if(newElement.key.compareTo(root.key) > 0)
		{
			if(tree.getRight() == null)
			{
				tree.setRight(new BinaryTree<Element>(newElement));
				return null;
			}
			return insert(newElement, tree.getRight());
		}
		else
		{
			if(tree.getLeft() == null)
			{
				tree.setLeft(new BinaryTree<Element>(newElement));
				return null;
			}
			return insert(newElement, tree.getLeft());
		}
	}
//-------------------------------------------------------------------------------------------
	private void inOrder(BinaryTree<Element> tree) // Organizes the keys
	{
		if(tree.getLeft() != null) {inOrder(tree.getLeft());}
		keySet.add(tree.getRoot().key);
		if(tree.getLeft() != null) {inOrder(tree.getRight());}	
	}
//-------------------------------------------------------------------------------------------
	private class Element //Element class that makes the nodes of the binary tree
	{
		K key;
		V value;
//-------------------------------------------------------------------------------------------
		Element(K key, V value)
		{
		this.key = key;
		this.value =value;
		}
//-------------------------------------------------------------------------------------------
	}//end of class Element
//-------------------------------------------------------------------------------------------
}// end of class Mytreemap
///////////////////////////////////////////////////////////////////////////////////////


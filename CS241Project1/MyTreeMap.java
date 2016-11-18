/**
 *	TreeMap implemented as Binary Search Tree
 */
import tree.BinaryTree;

public class MyTreeMap<K extends Comparable<K>,V> {
	private BinaryTree<Element> map;
	java.util.Set<K> keys;  // to return keys in order

	//	private int size;

	public boolean containsKey(K key)
	{
		Element r = search(new Element(key,null), map);
		if( r == null)
			return false;
		if(key.compareTo(key) == 0)
			return true;
		return false;
	}
	public V put(K key, V value)
	{
		Element element = new Element(key, value);
		insert(element);
		return value;
	}
	public V get(K key)
	{
		Element element = new Element(key, null);
		if(map == null)
			return null;
		return search(element, map).value;
	}
	public V remove(K key)
	{
		return get(delete(map, new Element(key, get(key)), null).key);
	}

	//	public int size() {}
	//	public int height() {}
	public String toString() 
	{
		return map.toString();
	}
	public java.util.Set<K> keySet()
	{
		keys = new java.util.TreeSet<>();
		if(map != null)
			inOrder(map);
		return keys;
	}
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	// Element class  C 
	private class Element
	{
		K key;
		V value;

		public Element(K key, V value) 
		{
			this.key = key;
			this.value = value;
		}
		public int compareTo(Element that)
		{
			return(key.compareTo(that.key));
		}
		public String toString()
		{
			return key + "=" + value;
		}	
	}
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	// private methods implementing BST operations search, insert, delete, inorder
	// reference: http://en.wikipedia.org/wiki/Binary_search_tree
	//
	private Element search(Element element, BinaryTree<Element> tree) 
	{
		// If Tree is empty, return null.
		if(tree == null)
			return null;
		// Let r be the element at the root.
		Element r = tree.getRoot();
		// If e = r return r.
		if(element.compareTo(r) == 0)
			return r;
		// Else if e < r return search(e, left).
		else if(element.compareTo(r) < 0)
			return search(element, tree.getLeft());
		// Else return search(e, right).
		else
			return search(element, tree.getRight());
	}
	private Element insert(Element element)
	{
		// If tree is empty, create new tree with e at root and return null.
		if(map == null)
		{
			map = new BinaryTree<Element>(element);
			return element;
		}
		// Else return insert(e, tree).
		else
			return insert(element, map);
	}
	private Element insert(Element element, BinaryTree<Element> tree)
	{
		// Let r be element at root.
		Element r = tree.getRoot();
		// e = r set root to e and return r.
		if(element.compareTo(r) == 0)
		{
			tree.setRoot(element);
			return r;
		}
		// Else if e < r
		else if(element.compareTo(r) < 0)
		{
			// If left == null attach e to the right and return null.
			if (tree.getLeft() == null)
			{
				tree.setLeft(new BinaryTree<Element>(element));
				return null;
			}
			// Else return insert(e, left).
			return insert(element, tree.getLeft());
		}
		// Else e > r
		else
		{
			// If right = null attach e to right and return null.
			if (tree.getRight() == null)
			{
				tree.setRight(new BinaryTree<Element>(element));
				return null;
			}
			// else return insert(e,left)
			return insert(element, tree.getRight());
		}
	}
	private Element delete(BinaryTree<Element> tree, Element element, BinaryTree<Element> parent)
	{
		// If tree is empty, return null.
		if(tree == null)
			return null;
		// Let r be the element at root x.
		Element r = tree.getRoot();
		// If e < r return delete(e, left).
		if(element.compareTo(r) < 0)
			return delete(tree.getLeft(), element, parent);
		// Else if e > r return delete(e, right).
		else if (element.compareTo(r) > 0)
			return delete(tree.getRight(), element, parent);
		// Else e = r.
		else if (element.compareTo(r) == 0)
		{
			// If node x is a leaf, delete.
			if (tree.isLeaf())
				promote(tree, parent, null);
			// else if x has only one child, delete x and promote child.
			if(tree.getLeft() == null)
				promote(tree, parent, tree.getRight());
			else if(tree.getRight() == null)
				promote(tree, parent, tree.getLeft());
			// Else x has two children.
			else
			{
				// Find inorder successor node s of x with element f.
				Element f = tree.setRoot(inOrderSuccessor(tree));
				// Delete s and promote its only child.
				delete(tree, inOrderSuccessor(tree), parent);
				// Set element at x to f.
				tree.setRoot(f);
			}
		}
		// Return r.
		return r;
	}
	// make newChild the appropriate (left or right) child of parent, if parent exists
	private void promote(BinaryTree<Element> tree, BinaryTree<Element> parent, BinaryTree<Element> newChild)
	{
		if(parent == null)
			map = newChild;
		else if (tree == parent.getLeft())
			parent.setLeft(newChild);
		else parent.setRight(newChild);
	}
	private void inOrder(BinaryTree<Element> tree)
	{
		if(tree.getLeft() != null)
			inOrder(tree.getLeft());
		keySet();
		if(tree.getLeft() != null)
			inOrder(tree.getRight());
	}
	private Element inOrderSuccessor(BinaryTree<Element> tree)
	{
		if(tree.getRight() == null)
			return null;
		else
			return findLeftMostChild(tree.getRight());
	}
	private Element findLeftMostChild(BinaryTree<Element> tree)
	{
		if(tree == null)
			return null;
		else if (tree.getLeft() == null)
			return tree.getRoot();
		else
			return findLeftMostChild(tree.getLeft());
	}
}
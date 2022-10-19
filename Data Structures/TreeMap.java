/*
 * TreeMap.java
 *
 * implements a map using a binary search tree
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V>
{
	private class Node
	{
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node (K key, V value)
		{
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}

		public String toString()
		{
			return "" + key + "=" + value;
		}
	}

	private Node root;
	private int size;

	public TreeMap()
	{
		root = null;
		size = 0;
	}

	public V add (K key, V value)
	{
		checkForNull(key);
		Node current = root;
		Node parent = null;
		boolean onLeft = true;
		if (root == null) {
			root = new Node(key, value);
			return null;
		}
		while (current != null)
		{
			int result = key.compareTo(current.key);
			parent = current;
			if (key.compareTo(current.key) == 0){
				V old = current.value;
				current.value = value;
				return old;
			}
			if (result < 0) {
				current = current.left;
				onLeft = true;
			} else {
				current = current.right;
				onLeft = false;
			}
			if (onLeft) {
				parent.left = new Node(key, value);
			} else {
				parent.right = new Node(key,value);
			}
		}
			size++;
			/**
			int result = key.compareTo(current.key);

			// smaller -> go left
			if (result < 0)
			{
				parent = current;
				current = current.left;
				onLeft = true;
				if (key == current){
					V save = current;
					current = key;
					return save;
				parent = parent.right;
			}
		}

			// larger -> go right
			else if (result > 0)
			{
				parent = current;
				current = current.right;
				onLeft = false;
				if (key == current){
					V save = current;
					current = key;
					return save;
				parent = parent.left;
			}
		}
		if(!contains(key)){
			Node n = new Node(key, value);
			if(size == 0){
				n = current;
			}else{
				int newresult = key.compareTo(current.key);
				if(newresult<0){
					parent = current;
					current= current.left
				}else{
					parent = current;
					current = current.right;
				}
			}
		}
			size++;
		**/
		return null;

	}

	public V set (K key, V value)
	{
			checkForNull(key);
			Node current = root;

			while (current != null)
			{
				int result = key.compareTo(current.key);

				if (result == 0)
				{
					current.value = value;
				}

				if (result < 0)
				{
					current = current.left;
				}

				else
				{
					current = current.right;
				}
			}
		return value;
	}

	public boolean contains (K key)
	{
		checkForNull(key);
		Node current = root;

		while (current != null)
		{
			int result = key.compareTo(current.key);

			if (result == 0)
			{
				return true;
			}

			if (result < 0)
			{
				current = current.left;
			}

			else
			{
				current = current.right;
			}
		}

		return false;
	}

	public V get(K key)
	{
		checkForNull(key);
		Node current = root;

		while (current != null)
		{
			int result = key.compareTo(current.key);

			if (result == 0)
			{
				return current.value;
			}

			if (result < 0)
			{
				current = current.left;
			}

			else
			{
				current = current.right;
			}
		}

		return null;
	}

	public V remove (K key)
	{
		Node current = root;
		Node parent = null;
		boolean onLeft = true;
		V removed = null;

		// traverse tree until find item
		while (current != null)
		{
			int result = key.compareTo(current.key);

			// smaller -> go left
			if (result < 0)
			{
				parent = current;
				current = current.left;
				onLeft = true;
			}

			// larger -> go right
			else if (result > 0)
			{
				parent = current;
				current = current.right;
				onLeft = false;
			}

			// item found
			else
			{
				// save value
				removed = current.value;

				// no children -> delete node
				if (current.left == null && current.right == null)
				{
					if (size == 1) root = null;
					else if (onLeft) parent.left = null;
					else parent.right = null;
				}

				// right child only -> replace parent with it
				else if (current.left == null)
				{
					if (current == root) root = current.right;
					else if (onLeft) parent.left = current.right;
					else parent.right = current.right;
				}

				// left child only -> replace parent with it
				else if (current.right == null)
				{
					if (current == root) root = current.left;
					else if (onLeft) parent.left = current.left;
					else parent.right = current.left;
				}

				// two children -> replace with largest predecessor
				// if node has child -> replace with child
				else
				{
					// save pointer to node to be replaced
					Node replace = current;

					// find largest predecessor
					parent = current;
					current = current.left;

					while (current.right != null)
					{
						parent = current;
						current = current.right;
					}

					// replace item to remove with largest predecessor
					replace.key = current.key;
					replace.value = current.value;

					// if it has child, replace with child
					if (current.left != null)
						parent.right = current.left;
					else
						parent.right = null;
				}

				size--;
				return removed;
			}
		}

		return null;
	}

	public int size()
	{
		return size;
	}

	public String toString()
	{
		if (root == null)
		{
			return "{}";
		}

		String s = "{" + inorder(root, new StringBuilder());

		return s.substring(0, s.length()-2) + "}";
	}

	private String inorder(Node n, StringBuilder s)
	{
		if (n.left != null)
			inorder (n.left, s);

		s.append(n + ", ");

		if (n.right != null)
			inorder(n.right, s);

		return s.toString();
	}

	public Iterator<K> iterator()
	{
		return new TreeIterator();
	}

	private void checkForNull (K key, V value)
	{
		if (key == null || value == null)
		{
			throw new IllegalArgumentException("Key/value can't be null");
		}
	}

	private void checkForNull (K key)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("Key can't be null");
		}
	}

	private class TreeIterator implements Iterator<K>
	{
		private Stack<Node> nodes;
		private Node currentNode;

		public TreeIterator()
		{
			nodes = new LinkedStack<>();
			currentNode = root;
		}

		public boolean hasNext()
		{
			return nodes.size() != 0 || currentNode != null;
		}

		public K next()
		{
			Node nextNode = null;

			while (currentNode != null)
			{
				nodes.add(currentNode);
				currentNode = currentNode.left;
			}

			if (nodes.size() != 0)
			{
				nextNode = nodes.remove();
				currentNode = nextNode.right;
			}

			else
			{
				throw new NoSuchElementException ("Off end of list");
			}

			return nextNode.key;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

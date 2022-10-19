/*
 * LinkedBag.java
 *
 * unordered collection with duplicates allowed
 *
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBag<T> implements Bag<T>
{
	/*
	 * inner class to represent node
	 */

	private class Node
	{
		/* instance variables */
		private T data;
		private Node next;

		/* constructor */
		public Node(T item)
		{
			data = item;
			next = null;
		}
	}

	/* instance variables */
	private Node head;
	private int size;

	/*
	 * constructor
	 */

	public LinkedBag()
	{
		head = null;
		size = 0;
	}

	/*
	 * adds item to collection
	 */

	public void add(T item)
	{
		// make sure null not being added
		checkForNull(item);

		// create new node for item to add
		Node newNode = new Node (item);

		// set its neighbor to be the current head of chain
		newNode.next = head;

		// make new node the new head of the chain
		head = newNode;

		// increment size of collection
		size++;
	}

	/*
	 * removes specified item
	 *
	 * returns true if removed, false if not found
	 */

	public boolean remove(T item)
	{
		// create travelling pointer and start it at head of chain
		Node current = head;

		// traverse collection
		for (int i = 0; i < size; i++)
		{
			// if item found
			if (item.equals(current.data))
			{
				// replace data at that node with data from first node
				current.data = head.data;

				// set start of chain to next node
				head = head.next;

				// decrement size of collection
				size--;

				// indicate item removed
				return true;
			}

			// advance to next node
			current = current.next;
		}

		// indicate item not found
		return false;
	}


	/*
	 * returns random item
	 */

	public T getRandom()
	{
		// return null if collection empty
		if (size == 0)
		{
			return null;
		}

		// choose random node number
		int random = (int)(Math.random()*size);

		// create travelling pointer
		Node current = head;

		// cycle through chain up to random node
		for (int i = 0; i < random; i++)
		{
			// advance pointer to next node
			current = current.next;
		}

		// return data at that node
		return current.data;
	}

	/*
	 * returns true if item is in collection, false otherwise
	 */

	public boolean contains(T item)
	{
		// create travelling pointer
		Node current = head;

		// cycle through collection
		while (current != null)
		{
			// if item found, indicate success
			if (item.equals(current.data))
			{
				return true;
			}

			// advance to next node
			current = current.next;
		}

		// indicate item not found
		return false;
	}

	/*
	 * returns size of collection
	 */

	public int size()
	{
		// return number of items in collection
		return size;
	}

	/*
	 * returns string representation of contents of collection
	 */

	public String toString()
	{
		// add opening bracket
		String s = "[";

		// create travelling pointer
		Node current = head;

		// cycle through collection
		while (current != null)
		{
			// append each item to string
			s += current.data;

			// add comma and space to all but last item
			if (current.next != null)
			{
				s += ", ";
			}

			// advance to next node
			current = current.next;
		}

		// add closing bracket and return string
		return s + "]";
	}

	public Iterator<T> iterator()
	{
		// return pointer to use for traversing
		return new LinkedIterator();
	}

	/* private helper method */

	/*
 	 * throws exception if item is null
	 */

	private void checkForNull(T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
	}

	/* iterator class */
	private class LinkedIterator implements Iterator<T>
	{
		/* instance variables */
		private Node on;

		/* constructor */
		public LinkedIterator()
		{
			on = head;
		}

		/*
		 * returns true if another item exists
		 */

		public boolean hasNext()
		{
			// if not at end of collection, return true
			return on!= null;
		}

		/*
		 * returns next item and moves iterator forward
		 */

		public T next()
		{
			// if another item exists
			if (hasNext())
			{
				// save item
				T item = on.data;

				// advance pointer
				on = on.next;

				// return saved item
				return item;
			}

			// if no additional item exists, indicate error
			else
			{
				throw new NoSuchElementException ("Off end of list");
			}
		}

		// indicate remove method not implemented
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

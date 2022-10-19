/*
 * LinkedList
 *
 * ordered collection with operations at any position
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T>
{
	private class Node
	{
		private T data;
		private Node next;

		public Node(T item)
		{
			data = item;
			next = null;
		}
	}

	private Node head;
	private int size;

	public LinkedList()
	{
		head = null;
		size = 0;
	}

	/*
	 * adds item to list
	 */

	public void add (T item)
	{
		checkForNull(item);
		Node newNode = new Node (item);

		if (size == 0)
		{
			head = newNode;
		}

		else
		{
			Node current = head;
			for (int i = 0; i < size-1; i++)
			{
				current = current.next;
			}
			current.next = newNode;
		}

		size++;
	}

	/*
	 * removes item from list
	 *
	 * returns true if item found in list
	 */

	public boolean remove (T item)
	{
		node current = head;
		if (size == 0){
			return null;
		}
		if (item.equals(head.data)){
			head = head.next;
		}else (int i = 0;i<size-1;i++){
			current = current.next
			if(current.next.data == item){
				current.next=current.next.next;
			}
		}size--;
		return false;
	}

	/*
	 * returns true if item is in list
	 */

	public boolean contains (T item)
	{
		Node current = head;

		while (current != null)
		{
			if (item.equals(current.data))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	/* list-specific methods */

	/*
	 * adds item at specified index
	 */

	public void add (int index, T item)
	{
		checkForNull(item);
		checkIndex(index);

		Node newNode = new Node (item);

		if (index == 0)
		{
			newNode.next = head;
			head = newNode;
		}

		else
		{
			Node current = head;

			for (int i = 0; i < index-1; i++)
			{
				current = current.next;
			}

			newNode.next = current.next;
			current.next = newNode;
		}

		size++;
	}

	/*
	 * replaces item at specified index with new value
	 *
	 * returns original value
	 */

	public T set (int index, T item)
	{
		checkForNull(item);
		checkIndex(index);

		Node current = head;

		for (int i = 0; i < index; i++)
		{
			current = current.next;
		}

		T removed = current.data;
		current.data = item;

		return removed;
	}

	/*
	 * removes item at specified index
	 *
	 * returns removed item
	 */

	public T remove (int index)
	{
		node current = head
		checkIndex(index);
		if (index == 0){
			T save = head.data;
			head = head.next;
		} for(int i = 0; i<index-1;i++){
			current = current.next;
		}T save = current.next.data;
		current.next = current.next.next;
		size--;
		return save;


	}

	/*
	 * returns item at specified index
	 */

	public T get (int index)
	{
		checkIndex(index);

		Node current = head;

		for (int i = 0; i < index; i++)
		{
			current = current.next;
		}

		return current.data;
	}

	/*
	 * returns index of specified item
	 *
	 * returns -1 if item not in list
	 */

	public int indexOf (T item)
	{
		Node current = head;

		for (int i = 0; i < size; i++)
		{
			if (item.equals(current.data))
			{
				return i;
			}

			current = current.next;
		}

		return -1;
	}

	/*
	 * returns size of list
	 */

	public int size()
	{
		return size;
	}

	/*
	 * returns string representation of list
	 */

	public String toString()
	{
		String s = "[";
		Node current = head;

		while (current != null)
		{
			s+= current.data;
			if (current.next!= null) s+= ", ";

			current = current.next;
		}

		return s + "]";
	}

	/*
	 * returns iterator for traversing collection
	 */

	public Iterator<T> iterator()
	{
		return new LinkedIterator();
	}

	/* helper methods */

	/*
	 * checks to make sure item isn't null
	 */

	private void checkForNull (T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException ("null not a possible value!");
		}
	}

	/*
	 * checks to make sure index falls within list
	 */

	private void checkIndex (int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("index out of range!");
		}
	}

	/* iterator class */
	private class LinkedIterator implements Iterator<T>
	{
		/* instance variables */
		private Node on;
		private int count;

		/* constructor */
		public LinkedIterator()
		{
			on = head;
			count = 0;
		}

		/*
		 * returns true if another item exists
		 */

		public boolean hasNext()
		{
			return on!= null;
		}

		/*
		 * returns next item and moves iterator forward
		 */

		public T next()
		{
			if (hasNext())
			{
				T item = on.data;
				on = on.next;
				count++;
				return item;
			}

			else
			{
				throw new NoSuchElementException ("Off end of list");
			}
		}

		/*
		 * removes next item
		 */

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

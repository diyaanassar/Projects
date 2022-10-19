/*
 * ArrayBag.java
 *
 * unordered collection with duplicates allowed
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBag<T> implements Bag<T>
{
	/* class variable */
	public static final int DEFAULT_CAPACITY = 10;

	/* instance variables */
	private T [] collection;
	private int size;

	/*
	 * default constructor
	 *
	 * initial capacity of ten
	 */

	public ArrayBag()
	{
		this(DEFAULT_CAPACITY);
	}

	/*
	 * argument constructor
	 *
	 * initial capacity specified by user
	 */

	public ArrayBag(int capacity)
	{
		// ensure size not negative
		if (capacity < 0)
		{
			throw new IllegalArgumentException("bag can't be negative size!");
		}

		// create generic array
		@SuppressWarnings("unchecked")
		T [] temp  = (T []) new Object[capacity];

		// set instance variable to point to it
		collection = temp;

		// delete temporary pointer
		temp = null;

		// set initial size of collection
		size = 0;
	}

	/*
	 * adds item to collection
	 *
	 * if at capacity, collection is resized
	 */

	public void add(T item)
	{
		// make sure null not being added
		checkForNull(item);

		// make sure have sufficient space
		ensureSpace();

		// add item to first free spot
		collection[size] = item;

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
		// cycle through collection
		for (int i = 0; i < size; i++)
		{
			// if item found
			if (item.equals(collection[i]))
			{
				// replace it with last item
				collection[i] = collection[size-1];

				// clear last cell
				collection[size-1] = null;

				// decrement size
				size--;

				// indicate item removed
				return true;
			}
		}

		// indicate item not found
		return false;
	}

	/* start of added code */

	/*
	 * removes and returns random item
	 */

	public T removeRandom()
	{
		return null;
	}


	public T getRandom()
	{
		// return null if collection empty
		if (size == 0)
		{
			return null;
		}

		// return random item from collection
		return collection[(int)(Math.random()*size)];
	}

	/*
	 * returns true if item is in collection
	 */

	public boolean contains(T item)
	{
		// cycle through collection
		for (int i = 0; i < size; i++)
		{
			// if item found, indicate success
			if (item.equals(collection[i]))
			{
				return true;
			}
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

		// cycle through collection
		for (int i = 0; i < size; i++)
		{
			// append each item to string
			s+= collection[i];

			// add comma and space to all but last item
			if (i < size -1)
			{
				s+= ", ";
			}
		}

		// add closing bracket and return string
		return s + "]";
	}

	/*
	 * returns iterator for traversing collection
	 */

	public Iterator<T> iterator()
	{
		// return pointer to use for traversing
		return new ArrayIterator();
	}

	/* private helper methods */

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

	/*
	 * doubles size of array if at capacity
	 */

	private void ensureSpace()
	{
		// if at capacity
		if (size == collection.length)
		{
			// create new array twice the size of old one
			@SuppressWarnings("unchecked")
			T [] larger  = (T []) new Object [size * 2];

			// copy each item over
			for (int i = 0; i < size; i++)
			{
				larger[i] = collection[i];
			}

			// set instance variable to point to new location
			collection = larger;

			// delete temporary pointer
			larger = null;
		}
	}

	/* iterator class */
	private class ArrayIterator implements Iterator<T>
	{
		/* instance variable */
		private int count; // number of items traversed

		/*
		 * constructor
		 */

		public ArrayIterator()
		{
			count = 0;
		}

		/*
		 * returns true if another item exists
		 */

		public boolean hasNext()
		{
			// if not at end of collection, return true
			return count < size;
		}

		/*
		 * returns next item and moves iterator forward
		 */

		public T next()
		{
			// if another item exists
			if (hasNext())
			{
				// return item at current location and increment item counter
				return collection[count++];
			}

			// if no additional item exists, indicate error
			else
			{
				throw new NoSuchElementException("off end of list");
			}
		}

		public void remove()
		{
			// indicate remove method not implemented
			throw new UnsupportedOperationException("remove not implemented");
		}
	}
}

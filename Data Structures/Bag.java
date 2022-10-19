/*
 * Bag.java
 *
 * interface for a bag
 *
 * unordered collection with duplicates allowed
 */

import java.util.Iterator;

public interface Bag<T>
{
	/*
	 * adds item to the collection
	 */

	void add (T item);

	/*
	 * removes specified item
	 *
	 * returns true if item removed, false if not found
	 */

	boolean remove (T item);

	/*
	 * removes and returns random item
	 */

	T removeRandom();

	/*
	 * removes random item
	 */

	T getRandom();

	/*
	 * returns true if item is in collection
	 */

	boolean contains (T item);

	/*
	 * returns size of collection
	 */

	int size();

	/*
	 * returns string representation of contents of collection
	 */

	String toString();

	/*
	 * returns iterator for traversing collection
	 */

	Iterator<T> iterator();
}

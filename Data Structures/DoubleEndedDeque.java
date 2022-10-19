/*
 * DoubleEndedDeque.java
 *
 * linked implementation of a queue
 *
 * uses tail pointer to access last node
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndedDeque<T> implements Deque<T>
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
	private Node tail;
	private int size;

	public DoubleEndedDeque()
	{
		head = null;
		tail = null;
		size = 0;
	}

	public void addFirst(T item)
	{
		checkForNull(item);
		Node newNode = new Node(item);
		newNode.next = head;
		head = newNode;
		if (size == 0) tail = head;
		size++;
	}



	public void addLast(T item)
	{
		Node newNode = new Node
		if (size ==0){
			head = newNode;
		}
		for (int i =0; i<size-1;i++){
			current= current.next;
		}
		current.next= newNode;
		tail=newNode;
		size++;
	}

	public T removeFirst()
	{
		if (size == 0)
		{
			return null;
		}

		T removed = head.data;
		head = head.next;

		if (size == 1)
		{
			tail = head;
		}

		size--;
		return removed;
	}



	public T removeLast()
	{
		if (size == 0){
			return null;
		}
		if (size == 1){
			T save = collection[0];
			head = null;
			tail = null;
		}
		Node pointer = head
		while	(pointer.next != null){
			pointer = pointer.next;
		}
		T save = pointer.next.data;
		pointer.next = null;
		pointer = tail;
		size--;
		return save;

	}

	public T getFirst()
	{
		if (size == 0)
		{
			return null;
		}

		return head.data;
	}

	public T getLast()
	{
		if (size == 0) return null;
		return tail.data;
	}

	public boolean contains (T item)
	{
		Node current = head;

		while (current!= null)
		{
			if (item.equals(current.data))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	public int size()
	{
		return size;
	}

	public String toString()
	{
		String s = "[";
		Node current = head;

		while (current != null)
		{
			s += current.data;

			if (current.next != null)
			{
				s += ", ";
			}

			current = current.next;
		}

		return s + "]";
	}

	public Iterator<T> iterator()
	{
		return new LinkedIterator();
	}

	private void checkForNull(T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
	}

	private class LinkedIterator implements Iterator<T>
	{
		private Node position;

		public LinkedIterator()
		{
			position = head;
		}

		public boolean hasNext()
		{
			return position!= null;
		}

		public T next()
		{
			if (hasNext())
			{
				T item = position.data;
				position = position.next;
				return item;
			}

			else
			{
				throw new NoSuchElementException ("Off end of list");
			}
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

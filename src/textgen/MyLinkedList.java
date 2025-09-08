package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (size == 0 || index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index size.");
		}

		int i=1;
		LLNode<E> value = head.next;

		if (index == 0)
			return value.data;

		while (i<=index) {
			value = value.next;
			i++;
		}
		
		return value.data;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null) {
			throw new NullPointerException("Null elements can not be inserted.");
		}

		LLNode<E> current = new LLNode<E>(element);
		if (size == 0) {
			current.prev = head;
			current.next = tail;
			head.next = current;
			tail.prev = current;
		} else {
			current.prev = tail.prev;
			current.next = tail;
			tail.prev.next = current;
			tail.prev = current;
		}
		size++;
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Null elements can not be inserted.");
		}
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index size.");
		}

		LLNode<E> current = new LLNode<E>(element);
		if (size == 0) {
			current.prev = head;
			current.next = tail;
			head.next = current;
			tail.prev = current;
		} else {
			int i=1;
			LLNode<E> value = head.next;
			while (i<=index) {
				value = value.next;
				i++;
			}

			current.prev = value.prev;
			current.next = value;
			value.prev.next = current;
			value.prev = current;
		}
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (size == 0 || index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index size.");
		}

		int i=1;
		LLNode<E> current = head.next;
		if (index != 0)
		{
			while (i<=index) {
				current = current.next;
				i++;
			}
		}
		E element = current.data;

//		if(index == 1) {
//			head.next = tail;
//			tail.prev = head;
//		} else {
//			
//		}
		
		current.next.prev = current.prev;
		current.prev.next = current.next;

		size--;
		return element;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Null elements can not be inserted.");
		}
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index size.");
		}
		
		int i=1;
		LLNode<E> current = head.next;
		if (index != 0)
		{
			while (i<=index) {
				current = current.next;
				i++;
			}
		}
		E value = current.data;
		current.data = element;
		return value;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

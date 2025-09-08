/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(3);
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.remove(-1);
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.add(53);
		list1.add(27);
		a = list1.remove(2);
		assertEquals("Remove: check a is correct ", 53, a);
		assertEquals("Remove: check element 1 is correct ", (Integer)42, list1.get(1));
		assertEquals("Remove: check element 2 is correct ", (Integer)27, list1.get(2));
		assertEquals("Remove: check size is correct ", 3, list1.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail("Check for null values.");
		}
		catch (NullPointerException e) {
		
		}
		shortList.add("X");
		shortList.add("T");
		assertEquals("AddEnd: check if size of shortList is correct", shortList.size(), 4);
		assertEquals("AddEnd: check if list updated correctly at pos 2", shortList.get(2), "X");
	}


	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check if size of long list is correct", longerList.size(), LONG_LIST_LENGTH);
		assertEquals("Size: check if size of short list is correct", shortList.size(), 2);
		assertEquals("Size: check if size of list1 is correct", list1.size(), 3);
		list1.add(82);
		assertEquals("Size: check if size of list1 is correct", list1.size(), 4);
	}


	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			shortList.add(3, "Z");
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.add(-1, "B");
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.add(0, null);
			fail("Check for null values.");
		}
		catch (NullPointerException e) {
		
		}
		list1.add(2, 72);
		list1.add(4, 67);
		assertEquals("AddAtIndex: check if size of list1 is correct", list1.size(), 5);
		assertEquals("AddAtIndex: check if list updated correctly at pos 2", list1.get(2), (Integer)72);
		assertEquals("AddAtIndex: check if list updated correctly at pos 3", list1.get(3), (Integer)42);
		assertEquals("AddAtIndex: check if list updated correctly at pos 4", list1.get(4), (Integer)67);
	}


	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			shortList.set(3, "Z");
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.set(-1, "B");
			fail("Check for index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.set(0, null);
			fail("Check for null values.");
		}
		catch (NullPointerException e) {
		
		}
		shortList.set(1, "F");
		assertEquals("Set: check if size of shortList is correct", shortList.size(), 2);
		assertEquals("Set: check if element updated correctly", shortList.get(1), "F");
	}
	
}

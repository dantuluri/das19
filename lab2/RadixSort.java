import java.io.*;
import java.util.*;

/**
 * RadixSort.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/22/2019
 */

public class RadixSort
{
	DoublyLinkedList list = new DoublyLinkedList();

	public static void main(String[]args)
	{
		RadixSort sort = new RadixSort();
		sort.run();
	}

	public void run()
	{
		DoublyLinkedList[] list = new DoublyLinkedList[]
		{
			new DoublyLinkedList(), // 0
			new DoublyLinkedList(), // 1
			new DoublyLinkedList(), // 2
			new DoublyLinkedList(), // 3
			new DoublyLinkedList(), // 4
			new DoublyLinkedList(), // 5
			new DoublyLinkedList(), // 6
			new DoublyLinkedList(), // 7
			new DoublyLinkedList(), // 8
			new DoublyLinkedList() // 9
		}

		ArrayList<Integer> input = new ArrayList<Integer>(); 
		Scanner reader = new Scanner(new File("numbers.txt"));

		while (reader.hasNext()){
			input.add(reader.nextInt());
		}
		
	}

	public void radixSort(ArrayList input, int size)
	{
		int bucketIndex, arrayIndex;
		int maxDigits = radixGetMaxLength(input, size);
		int pow10 = 1;
		for(int digitIndex = 0; digitIndex<maxDigits; digitIndex++)
		{
			for(int i = 0;i<size; i++)
			{
				bucketIndex = Math.abs(input.get(i)/pow10)%10;
				list[bucketIndex].append(new Node(input.at(i)))
			}

			arrayIndex = 0;
			for(int i=0;i<10;i++)
				for(int j=0;j<list[i].size();j++)
					input.set(arrayIndex++, )
		}
	} 

	RadixSort(array, arraySize) {
		buckets = create array of 10 buckets

   		// Find the max length, in number of digits
		maxDigits = RadixGetMaxLength(array, arraySize)

   		// Start with the least significant digit
		pow10 = 1
		for (digitIndex = 0; digitIndex < maxDigits; digitIndex++) {
			for (i = 0; i < arraySize; i++) {
				bucketIndex = abs(array[i] / pow10) % 10
				Append array[i] to buckets[bucketIndex]
			}

			arrayIndex = 0
			for (i = 0; i < 10; i++) {
				for (j = 0; j < buckets[i].size(); j++)
					array[arrayIndex++] = buckets[i][j]
			}
			pow10 = 10 * pow10
			Clear all buckets
		}
	}

	// Returns the maximum length, in number of digits, out of all elements in the array
	RadixGetMaxLength(array, arraySize) {
		maxDigits = 0
		for (i = 0; i < arraySize; i++) {
			digitCount = RadixGetLength(array[i])
			if (digitCount > maxDigits)
				maxDigits = digitCount
		}
		return maxDigits
	}

	// Returns the length, in number of digits, of value
	RadixGetLength(value) {
		if (value == 0)
			return 1

		digits = 0
		while (value != 0) {
			digits = digits + 1
			value = value / 10
		}
		return digits
	}

}

class DoublyLinkedList <T>
{
	public Node head;
	public Node tail;

	public DoublyLinkedList()
	{
		head = null;
		tail = null;
	}

	/** Inserts Node newNode after Node curNode
	*  @param curNode Node previous to inserted node
	*  @param newNode Node that is inserted after curNode
	*/
	public void insert(Node curNode, Node newNode)
	{
		//Temporary Node
		Node sucNode;

		// List empty
		if (head == null) 
		{ 
			head = newNode;
			tail = newNode;
		}
		// Insert after tail
		else if (curNode == tail) 
		{ 
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		else
		{
			sucNode = curNode.next;
			newNode.setNext(sucNode);
			newNode.setPrev(curNode);
			curNode.setNext(newNode);
			sucNode.setPrev(newNode);
		}
	}

	/** Deletes Node after Node curNode.
	*  @param curNode Node previous to deleted node
	*/
	public void delete(Node curNode)
	{
		//Temporary Node
		Node sucNode;
		Node predNode;
		sucNode = curNode.next;
		predNode = curNode.prev;

		if (sucNode!=null) 
		{
			sucNode.prev = predNode;
			sucNode.setPrev(predNode);
		}

		if (predNode!=null) 
		{
			predNode.setNext(sucNode);
		}

		// Removed head
		if (curNode == head) 
		{ 
			head = sucNode;
		}

		// Removed tail
		if (curNode == tail) 
		{ 
			tail = predNode;
		}
	}

	// Inserts node at the start of the list
	public void prepend(Node curNode)
	{
		curNode.setNext(head);
		curNode.setPrev(null);

		if(head != null)
			head.setPrev(curNode);

		head = curNode;

	}

	// Inserts node at the end of the list
	public void append(Node curNode)
	{

		Node traverseNode = head;

		curNode.setNext(null);

		if(head==null)
		{
			curNode.setPrev(null);
			head = curNode;
			return;
		}

		while(traverseNode.next!=null)
			traverseNode = traverseNode.next;

		traverseNode.setNext(curNode);
		curNode.setPrev(traverseNode);  
	}

	// Returns head of Doubly linked list
	public Node getHead()
	{
		return head;
	}

	// Returns tail of Doubly linked list
	public Node getTail()
	{
		return tail;
	}

	/** Prints Doubly Linked List
	*/
	public void printList()
	{
		Node temp = head; // start at the head node
		while (temp != null)
		{
			System.out.print(temp.get() + " ");
			temp = temp.next; // go to next node
		}
		System.out.println();
		System.out.println();
	}

	public int size()
	{
		int sizeCount = 0;
		Node temp = head; // start at the first node
		while (temp != null)
		{
			sizeCount++;
			temp = temp.next; // go to next node
		}
		return sizeCount;
	}

}


class Node <T>
{
	T data;
	Node next;
	Node prev;

	public Node(T t)
	{
		data = t;
		next = null;
		prev = null;
	}

	public T get()
	{
		return data;
	}
	public void set(T t)
	{
		data = t;
	}

	public void setNext(Node newNode)
	{
		next = newNode;
	}

	public void setPrev(Node newNode)
	{
		prev = newNode;
	}
}


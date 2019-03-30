import java.io.*;
import java.util.*;

/**
 * Extra Credit
 *
 * RadixSort.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 3/24/2019
 */

/*
Explanation:
Why do these execution results make sense? Since they take into factor of both length and
alphabetical order. 

Execution Results:
---------------------------------
Unsorted Array: 
---------------------------------
First 5 Elements: the and to of a 
Last 5 Elements: filled servants calling fallen supper 
---------------------------------

---------------------------------
Sorted Array: 
---------------------------------
First 5 Elements: a i o ah am 
Last 5 Elements: acquaintance conversation particularly satisfaction circumstances 

*/

public class RadixSort
{
	DoublyLinkedList[] buckets = new DoublyLinkedList[]
	{
			new DoublyLinkedList(), // a
			new DoublyLinkedList(), // b
			new DoublyLinkedList(), // c
			new DoublyLinkedList(), // d
			new DoublyLinkedList(), // e
			new DoublyLinkedList(), // f
			new DoublyLinkedList(), // g
			new DoublyLinkedList(), // h
			new DoublyLinkedList(), // i
			new DoublyLinkedList(), // j
			new DoublyLinkedList(), // k
			new DoublyLinkedList(), // l
			new DoublyLinkedList(), // m
			new DoublyLinkedList(), // n
			new DoublyLinkedList(), // o
			new DoublyLinkedList(), // p
			new DoublyLinkedList(), // q
			new DoublyLinkedList(), // r
			new DoublyLinkedList(), // s
			new DoublyLinkedList(), // t
			new DoublyLinkedList(), // u
			new DoublyLinkedList(), // v
			new DoublyLinkedList(), // w
			new DoublyLinkedList(), // x
			new DoublyLinkedList(), // y
			new DoublyLinkedList() // z

		};

		public static void main(String[]args)
		{
			RadixSort sort = new RadixSort();
			sort.run();
		}

		public void run()
		{
			ArrayList<String> input = new ArrayList<String>(); 

			try (Scanner reader = new Scanner(new FileReader("Words.txt")))
			{
				while (reader.hasNext()){
					input.add(reader.nextLine());
				}
			}
			catch (IOException | InputMismatchException ex)
			{
				ex.printStackTrace();
			}

			System.out.println("---------------------------------");
			System.out.println("Unsorted Array: ");
			System.out.println("---------------------------------");
			System.out.print("First 5 Elements: ");
			for(int i = 0;i<5;i++)
			{
				System.out.print(input.get(i)+" ");
			}
			System.out.println();
			System.out.print("Last 5 Elements: ");
			for(int i = input.size()-5;i<input.size();i++)
			{
				System.out.print(input.get(i)+" ");
			}

			System.out.println();
			System.out.println("---------------------------------");
			System.out.println();

			radixSort(input, input.size());

			try(PrintWriter writer = new PrintWriter("out.txt", "UTF-8"))
			{
				for(int i = 0;i<input.size();i++)
					writer.println(input.get(i));
				writer.close();
			}
			catch(UnsupportedEncodingException | FileNotFoundException ex)
			{
				ex.printStackTrace();
			}
			System.out.println("---------------------------------");
			System.out.println("Sorted Array: ");
			System.out.println("---------------------------------");
			System.out.print("First 5 Elements: ");
			for(int i = 0;i<5;i++)
			{
				System.out.print(input.get(i)+" ");
			}
			System.out.println();
			System.out.print("Last 5 Elements: ");
			for(int i = input.size()-5;i<input.size();i++)
			{
				System.out.print(input.get(i)+" ");
			}
			System.out.println();
			System.out.println("---------------------------------");
			
			

		}

		public void radixSort(ArrayList<String> array, int arraySize)
		{
			int bucketIndex, arrayIndex;
			arraySize = array.size();
			int maxDigits = radixGetMaxLength(array, arraySize);
			int pow10 = 0;
			for(int digitIndex = 0; digitIndex<maxDigits; digitIndex++)
			{
				// System.out.println(Arrays.toString(array.toArray()));
				// System.out.println("main loop "+digitIndex);
				for(int i = 0;i<arraySize; i++)
				{
					// bucketIndex = Math.abs(array.get(i)/pow10)%10;
					// System.out.println("in loop: "+i+" arr get: "+array.get(i)+" charat ");
					// System.out.print(getCharacterAt(array.get(i),pow10));
					bucketIndex = alphaMap(getCharacterAt(array.get(i),pow10));
					// System.out.println("ind "+bucketIndex);
					buckets[bucketIndex].append(new Node(array.get(i)));
				}

				arrayIndex = 0;
				for (int i = 0; i < 26; i++) 
				{
					int bucketSize = buckets[i].size();
					for (int j = 0; j < bucketSize; j++)
					{
						array.set(arrayIndex,pop(buckets[i]));
						arrayIndex++;
					}
				}
				// System.out.println(Arrays.toString(array.toArray()));
				pow10+=1;
				clearBucket();
			}
		}

	/**
	*	Extended from Queue class. Should use inheritance
	* 	but this temporary fix works.
	*/
	public String pop(DoublyLinkedList dll)
	{
		
		if(dll.head==null)
		{
			// System.out.println("this is empty");
			return "";
		}
		else
		{
			Node poppedItem = dll.head;
			dll.delete(dll.head);
			return ((String)poppedItem.get());
		}
		
	}

	public void clearBucket()
	{
		buckets = new DoublyLinkedList[]
		{
			new DoublyLinkedList(), // a
			new DoublyLinkedList(), // b
			new DoublyLinkedList(), // c
			new DoublyLinkedList(), // d
			new DoublyLinkedList(), // e
			new DoublyLinkedList(), // f
			new DoublyLinkedList(), // g
			new DoublyLinkedList(), // h
			new DoublyLinkedList(), // i
			new DoublyLinkedList(), // j
			new DoublyLinkedList(), // k
			new DoublyLinkedList(), // l
			new DoublyLinkedList(), // m
			new DoublyLinkedList(), // n
			new DoublyLinkedList(), // o
			new DoublyLinkedList(), // p
			new DoublyLinkedList(), // q
			new DoublyLinkedList(), // r
			new DoublyLinkedList(), // s
			new DoublyLinkedList(), // t
			new DoublyLinkedList(), // u
			new DoublyLinkedList(), // v
			new DoublyLinkedList(), // w
			new DoublyLinkedList(), // x
			new DoublyLinkedList(), // y
			new DoublyLinkedList() // z
		};
	}

	public String getCharacterAt(String str, int n)
	{
		if((str.length()-(n+1))<0)
			return "";
		else
			return "" + str.charAt(str.length()-(n+1));
	}

	public int alphaMap(String map)
	{
		// System.out.println("MAP: "+map);
		if(map.length()==0)
			return 0;
		return((int)(map.charAt(0)) - 96);
	}

	public int radixGetMaxLength(ArrayList<String> array, int arraySize)
	{
		int digitCount, maxDigits = 0;
		for (int i = 0; i < arraySize; i++) {
			digitCount = radixGetLength(array.get(i));
			if (digitCount > maxDigits)
				maxDigits = digitCount;
		}
		return maxDigits;
	} 

	public int radixGetLength(String value)
	{
		if (value.length()==0)
			return 1;

		int digits = 0;
		while (value.length()!=0) {
			digits++;
			value = value.substring(0, value.length() - 1);
		}
		return digits;
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


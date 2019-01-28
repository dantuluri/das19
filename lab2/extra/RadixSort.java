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
	DoublyLinkedList[] buckets = new DoublyLinkedList[]
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
			new DoublyLinkedList()  // 9
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
			int pow10 = 1;
			for(int digitIndex = 0; digitIndex<maxDigits; digitIndex++)
			{
				for(int i = 0;i<arraySize; i++)
				{
					if(isValid(array.get(i)))
					{
						bucketIndex = Math.abs(Integer.parseInt(array.get(i))/pow10)%10;
					}
					else
					{
						System.out.println("hello");
						bucketIndex = Math.abs(array.get(i).length()-(int)(Math.log10(pow10)));
					}
					buckets[bucketIndex].append(new Node(array.get(i)));
				}
				arrayIndex = 0;
				for (int i = 0; i < 10; i++) 
				{
					int bucketSize = buckets[i].size();
					for (int j = 0; j < bucketSize; j++)
					{
						array.set(arrayIndex,pop(buckets[i]));
						arrayIndex++;
					}
				}
				pow10*=10;
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
			System.out.println("this is empty");
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
			new DoublyLinkedList(), // 0
			new DoublyLinkedList(), // 1
			new DoublyLinkedList(), // 2
			new DoublyLinkedList(), // 3
			new DoublyLinkedList(), // 4
			new DoublyLinkedList(), // 5
			new DoublyLinkedList(), // 6
			new DoublyLinkedList(), // 7
			new DoublyLinkedList(), // 8
			new DoublyLinkedList()  // 9
		};
	}

	public static boolean isValid(String input) 
	{
		try 
		{ 
			Integer.parseInt(input); 
		} 
		catch(NumberFormatException ne) 
		{ 
			return false; 
		} 
		catch(NullPointerException ne) {
			return false;
		}
		return true;
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
			System.out.println("hello");
			digits++;
			value.substring(0, value.length() - 1);
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


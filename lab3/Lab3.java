import java.io.*;
import java.util.*;
/**
 * Lab3.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 2/7/2019
 */

public class Lab3
{
	Hashtable mod = new Hashtable(0);
	Hashtable mid = new Hashtable(1);
	Hashtable mul = new Hashtable(2);
	public static void main(String[]args)
	{
		Lab3 three = new Lab3();
		three.run();
	}
	public void run()
	{
		try (Scanner reader = new Scanner(new FileReader("Customer.csv")))
		{
			reader.useDelimiter(",");
			while (reader.hasNextLine()){
				//Input
				String inputInitial = reader.nextLine();
				String[] tempArray = inputInitial.split(",");
				String fname = tempArray[0];
				String lname = tempArray[1];
				String tempId = tempArray[2];
				tempId = tempId.substring(0,tempId.indexOf("-"))+tempId.substring(tempId.indexOf("-")+1,tempId.indexOf("-")+2);
				int id = Integer.parseInt(tempId);
				Customer visitor = new Customer(fname, lname, id);
			}
			reader.close();
		}
		catch (IOException | InputMismatchException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Collisions for Modulo Hashing: "+mod.getcollisions());
	}


	public void HashInsert(hashTable, item) {
		if (HashSearch(hashTable, item.keyId) == null) {
			bucketList = hashTable[Hash(item->key)]
			node = Allocate new linked list node
			node->next = null
			node->data = item
			ListAppend(bucketList, node)
		}
	}
}

class DynamicArray
{
	Object[] container;
	private int size;
	private int index;

	public DynamicArray(Object insert)
	{
		container = new Object[10];
		size = 10;
		for(int i = 0;i<size;i++)
			container[i] = insert;
		index = 0;

	}

	public void insert(Object item) 
	{ 
		if(index>size)
			resize();
		index++;
		container[index] = item;
	}

	public Object get(int i)
	{
		return container[i];		
	}

	public void resize()
	{
		size*=2;
		Object temp[] = new Object[size];

		for(int i = 0; i<container.length;i++)
			temp[i]=container[i];

		container=temp;
	}

	public void resize(int s)
	{
		if(s>size)
		{
			size=s;
			Object temp[] = new Object[size];

			for(int i = 0; i<container.length;i++)
				temp[i]=container[i];

			container=temp;
		}
	}

	public int size()
	{
		return size;
	}
}

class Hashtable <T>
{
	int collisions = 0;
	int s;
	int option;

	public Hashtable(int input)
	{
		s = 1;
		table.resize(s);
		option = input;
	}
	int size() { return table.size(); }
	int getcollisions() { return collisions; }

	public DoublyLinkedList get(int index)
	{
		return (DoublyLinkedList)table.get(index);
	}

	public void insert(Customer item) {
		DoublyLinkedList<Node> bucketList;
		if (HashSearch(item.id) == null) { 
			bucketList = (DoublyLinkedList)table.get(getHash(item.id, option));
			Node input = new Node(item);
			input.setNext(null);
			bucketList.append(input);
			table.insert();
			collisions++;
		}
	}

	public String HashSearch(int key) {
		DoublyLinkedList<Node> bucketList;
		bucketList = (DoublyLinkedList)table.get(getHash(key, option));
		Node itemNode = listSearch(bucketList, key);
		if (itemNode != null)
			return (String)itemNode.data;
		else
			return "";
	}

	public Node listSearch(DoublyLinkedList<Node> list, int data) 
	{ 
		try
		{
			Node current = list.peek();
			while (current != null) 
			{ 
				if (current.data.equals(data)) 
					return current;
				current = current.next; 
			} 
		}
		catch(NullPointerException e) 
		{ 
			
		} 
        return null;    //data not found 
    }  

    public int getHash(int data, int option)
    {
    	//mod
    	if(option==0)
    	{
    		System.out.println(data);
    		System.out.println(data%10);
    		System.out.println();
    		return (data%10);
    	}
    	//mid
    	if(option==1)
    	{
    		int R = 3;
    		int key = data;
    		int squaredKey = key * key;

    		int lowBitsToRemove = (32 - R) / 2;
    		int extractedBits = squaredKey >> lowBitsToRemove;
    		extractedBits = extractedBits & (0xFFFFFFFF >> (32 - R));

    		return extractedBits % 250;
    	}
    	//mul
    	if(option==2)
    	{
    		int stringHash = 5381; 

    		String key = Integer.toString(data);
    		for (int i = 0;i<key.length();i++) {
    			Character c = key.charAt(i);
    			stringHash = (stringHash * 33) + c;
    		}
    		return stringHash % 250;
    	}
    	return -1;
    }
}

class Customer
{
	public String firstname;
	public String lastname;
	public int keyId;
	public int hashId;

	public Customer(String fname, String lname, int lid)
	{
		this.firstname = fname;
		this.lastname = lname;
		this.keyId = lid;
	}

	public String getFirstName()
	{
		return firstname;
	}
	public String getLastName()
	{
		return lastname;
	}
	public int getId()
	{
		return keyId;
	}
}

class LinkedList <T>
{
	public Node head;
	public Node tail;

	public LinkedList()
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

	public Node peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("No element found to peek");
		return this.head;
	}

	public boolean isEmpty()
	{
		if(this.head!=null)
			return true;
		return false;
	}

	/** Prints Linked List
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
}


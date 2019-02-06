import java.io.*;

/**
 * Lab3.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/22/2019
 */

public class Lab
{
	Hashtable mod = new Hashtable(0);
	Hashtable mid = new Hashtable(1);
	Hashtable mul = new Hashtable(2);
	public static void main(String[]args)
	{
		Lab three = new Lab();
		three.run();
	}
	public void run()
	{
		try (Scanner reader = new Scanner(new FileReader("Customer.csv")))
		{
			reader.useDelimiter(",");
			while (reader.hasNextLine()){
				String inputInitial = reader.nextLine();
				String[] tempArray = inputInitial.split(",");
				String fname = tempArray[0];
				String lname = tempArray[1];
				String tempId = tempArray[2];
				tempId = tempId.substring(0,tempId.indexOf("-"))+tempId.substring(tempId.indexOf("-")+1,tempId.indexOf("-")+2);
				int id = Integer.parseInt(tempId);
			}
			reader.close();
		}
		catch (IOException | InputMismatchException ex)
		{
			ex.printStackTrace();
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

	}

	public void insert(Object item)
	{
		if(index==(size-1))
		{
			resize();
		}
		container[index]=item;
		index++;
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
		if(!(s<size))
		{
			size=s;
			Object temp[] = new Object[size];

			for(int i = 0; i<container.length;i++)
				temp[i]=container[i];

			container=temp;
		}
	}

}


class Hashtable <T>
{
	DynamicArray<DoublyLinkedList<Node>> table = new DynamicArray<DoublyLinkedList<Node>>();
	DynamicArray table = new DynamicArray();
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

	public LinkedList get(int index)
	{
		return table.get(index);
	}

	public void insert(Customer item) {
		LinkedList<Node> bucketList;
		if (HashSearch(item.id) == null) { 
			bucketList = table.get(getHash(item.id, option));
			Node input = new Node(item);
			input.setNext(null);
			bucketList.add(input);
		}
	}

	public String HashSearch(int key) {
		LinkedList<Node> bucketList;
		bucketList = table.get(getHash(key, option));
		Node itemNode = listSearch(bucketList, key);
		if (itemNode != null)
			return (String)itemNode.data;
		else
			return "";
	}

	public Node listSearch(LinkedList<Node> list, int data) 
	{ 
		Node current = list.peek();
		while (current != null) 
		{ 
			if (current.data.equals(data)) 
				return current;
			current = current.next; 
		} 
        return null;    //data not found 
    } 

    public int getHash(int data, int option)
    {
    	//mod
    	if(option==0)
    	{
    		System.out.println("DAT: "+data);
    		return (data%250);
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
	String firstname;
	String lastname;
	int id;

	public Customer(String fname, String lname, int lid)
	{
		firstname = fname;
		lastname = lname;
		id = lid;
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
		return id;
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


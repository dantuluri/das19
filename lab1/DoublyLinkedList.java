import java.io.*;
import java.util.*;

/**
 * DoublyLinkedList.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/12/2019
 */

public class DoublyLinkedList <T>
{
	private Node head;
	private Node tail;

	public DoublyLinkedList()
	{
		head = null;
		tail = null;
	}

	public static void main(String[]args)
	{
		DoublyLinkedList list = new DoublyLinkedList();
		list.run();
	}

	public void run()
	{
		//Making some nodes
		Node node1 = new Node(9);
		Node node2 = new Node(4);
		Node node3 = new Node(15);


		//Inserting the nodes into the Singly Linked List
		insert(head, node1);
		insert(tail, node3);
		insert(node1, node2);

		//Print initial list
		System.out.println();
		printList();
		//Should print out: apple peach carrot strawberry banana 

		//Removing "strawberry" by removing Node before (this is how the book implements it)
		System.out.println("Removing strawberry");
		delete(carrot);
		printList();
		//Should print out: apple peach carrot banana 

		//Same aforementioned logic is used for consequetive calls to delete nodes
		System.out.println("Removing banana");
		delete(carrot);
		printList();
		//Should print out: apple peach carrot 

		System.out.println("Removing apple");
		/** Deleting node null since it is before head and since this function 
		* deletes the next node the head is consequently deleted.
		*/
		delete(null);
		printList();
		//Should print out: peach carrot 

		System.out.println("Removing peach");
		delete(null);
		printList();
		//Should print out: carrot

		System.out.println("Removing carrot");
		delete(null);
		printList();
		//Should be empty list by now since all nodes are deleted
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

	/** Prints Singly Linked List
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

	public static void main(String[] args)
	{
		Integer inumber = new Integer(123);
		Node<Integer> inode = new Node<Integer>(inumber);
		System.out.println(inode.get());
		
		String astring = "abc";
		Node<String> snode = new Node<String>(astring);
		System.out.println(snode.get());
		
		ArrayList<String> alist = new ArrayList<String>();
		Node<ArrayList<String>> anode = new Node<ArrayList<String>>(alist);
		anode.get().add("ABC");
		anode.get().add("DEF");
		anode.get().add("GHI");
		for (int x=0; x<anode.get().size(); x++)
			System.out.println(anode.get().get(x));
	} 
}
import java.io.*;
import java.util.*;

/**
 * DoublyLinkedList.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/20/2019
 */

public class DoublyLinkedList <T>
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

	public void prepend(Node curNode)
	{
		curNode.setNext(head);
		curNode.setPrev(null);

		if(head != null)
			head.setPrev(curNode);

		head = curNode;

	}

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
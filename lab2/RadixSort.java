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
		Node list1 = new Node(new DoublyLinkedList());
		Node list2 = new Node(new DoublyLinkedList());
		Node list3 = new Node(new DoublyLinkedList());
		Node list4 = new Node(new DoublyLinkedList());
		Node list5 = new Node(new DoublyLinkedList());
		Node list6 = new Node(new DoublyLinkedList());
		Node list7 = new Node(new DoublyLinkedList());
		Node list8 = new Node(new DoublyLinkedList());
		Node list9 = new Node(new DoublyLinkedList());
		Node list10 = new Node(new DoublyLinkedList());
		list.insert(list.getHead(), list1);
		list.insert(list1, list2);
		list.insert(list2, list3);
		list.insert(list3, list4);
		list.insert(list4, list5);
		list.insert(list5, list6);
		list.insert(list6, list7);
		list.insert(list7, list8);
		list.insert(list8, list9);
		list.insert(list9, list10);

		
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


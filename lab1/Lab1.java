import java.io.*;
import java.util.*;

/**
 * Lab1.java
 * Runs Stack and Queue interface with some test cases
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/20/2019
 */

public class Lab1
{
	public static void main(String[]args)
	{
		Lab1 assignment = new Lab1();
		assignment.run();
	}

	// Runs Stack and Queue through their interface
	public void run()
	{
		System.out.println("Lab1");
		System.out.println("-----------------------------");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Stack");
		System.out.println("-----------------------------");
		Stack pancake = new Stack();
		pancake.run();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Queue");
		System.out.println("-----------------------------");
		Queue costco = new Queue();
		costco.run();
		System.out.println();
		System.out.println();
		System.out.println();
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

class Stack <T>
{
	private Node top;
	private Node bottom;
	private int length;
	DoublyLinkedList list = new DoublyLinkedList();

	public Stack()
	{
		top = null;
		bottom = null;
		length = 0;
	}

	public void run()
	{
		Node apple = new Node("apple");
		Node peach = new Node("peach");
		Node carrot = new Node("carrot");
		Node strawberry = new Node("strawberry");
		Node banana = new Node("banana");


		System.out.println("Pushing Apple");
		push(apple);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Pushing Peach");
		push(peach);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Pushing Carrot");
		push(carrot);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Popping");
		Node popped = pop();
		System.out.println(popped.get());
		System.out.println("Length of current Stack: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Popping again");
		popped = pop();
		System.out.println(popped.get());
		System.out.println("Length of current Stack: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Pushing Strawberry");
		push(strawberry);
		list.printList();
		System.out.println("Length of current Stack: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Peeking");
		Node peeked = peek();
		System.out.println(peeked.get());
		System.out.println("-----------------------------");
		System.out.println("Peeking again");
		peeked = peek();
		System.out.println(peeked.get());
		System.out.println("-----------------------------");
		System.out.println("Is Stack empty? Answer: "+isEmpty());
		System.out.println("-----------------------------");

	}

	// Pushes node into Stack (LIFO)
	public void push(Node curNode)
	{
		list.prepend(curNode);
		length++;
	}

	// Pops node into Stack (LIFO)
	public Node pop()
	{
		Node poppedItem = list.getHead();
		list.delete(list.getHead());
		length--;
		return poppedItem;
	}

	// Peeks top of Stack
	public Node peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("No element found to peek");
		return list.getHead();
	}

	// Checks if Stack is empty
	public boolean isEmpty()
	{
		if(list.getHead()==null)
			return true;
		return false;
	}

	// Gets length of Stack
	public int getLength()
	{
		return length;
	}
}

class Queue <T>
{
	private Node top;
	private Node bottom;
	private int length;
	DoublyLinkedList list = new DoublyLinkedList();

	public Queue()
	{
		top = null;
		bottom = null;
		length = 0;
	}

	public void run()
	{
		Node apple = new Node("apple");
		Node peach = new Node("peach");
		Node carrot = new Node("carrot");
		Node strawberry = new Node("strawberry");
		Node banana = new Node("banana");


		System.out.println("Pushing Apple");
		push(apple);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Pushing Peach");
		push(peach);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Pushing Carrot");
		push(carrot);
		list.printList();
		System.out.println("-----------------------------");
		System.out.println("Popping");
		Node popped = pop();
		System.out.println(popped.get());
		System.out.println("Length of current Queue: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Popping again");
		popped = pop();
		System.out.println(popped.get());
		System.out.println("Length of current Queue: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Pushing Strawberry");
		push(strawberry);
		list.printList();
		System.out.println("Length of current Queue: "+getLength());
		System.out.println("-----------------------------");
		System.out.println("Peeking");
		Node peeked = peek();
		System.out.println(peeked.get());
		System.out.println("-----------------------------");
		System.out.println("Peeking again");
		peeked = peek();
		System.out.println(peeked.get());
		System.out.println("-----------------------------");
		System.out.println("Is Queue empty? Answer: "+isEmpty());
		System.out.println("-----------------------------");
	}

	// Pushes node into Queue (FIFO)
	public void push(Node curNode)
	{
		list.append(curNode);
		length++;
	}

	// Pops node from Queue (FIFO)
	public Node pop()
	{
		Node poppedItem = list.head;
		list.delete(list.head);
		length--;
		return poppedItem;
	}

	// Peeks at end of Queue
	public Node peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("No element found to peek");
		return list.head;
	}

	// Checks if Queue is empty
	public boolean isEmpty()
	{
		if(list.head==null)
			return true;
		return false;
	}

	// Determines length of Queue
	public int getLength()
	{
		return length;
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
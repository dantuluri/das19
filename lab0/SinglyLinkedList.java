import java.io.*;
import java.util.*;

/**
 * SinglyLinkedList.java
 * Singly Linked List that links any type of data
 * @author Surya Dantuluri
 * @version 1.0
 * @since 8/17/2017
 */

public class SinglyLinkedList <T>
{
	private Node head;
	private Node tail;

	public SinglyLinkedList()
	{
		head = null;
		tail = null;
	}

	public static void main(String[]args)
	{
		SinglyLinkedList list = new SinglyLinkedList();
		list.run();
	}

	public void run()
	{
		Node apple = new Node("apple");
		Node peach = new Node("peach");
		Node carrot = new Node("carrot");
		Node strawberry = new Node("strawberry");
		Node banana = new Node("banana");
		insert(head, apple);
		insert(apple, peach);
		insert(peach, carrot);
		insert(carrot, strawberry);
		insert(strawberry, banana);
		printList();
		System.out.println("Removing strawberry");
		delete(carrot);
		printList();
		System.out.println("Removing banana");
		delete(carrot);
		printList();
		System.out.println("Removing apple");
		delete(null);
		printList();
		System.out.println("Removing peach");
		delete(null);
		printList();
		System.out.println("Removing carrot");
		delete(null);
		printList();
	}

	//insert after
	public void insert(Node curNode, Node newNode)
	{
		//Empty list
		if(head == null)
		{
			// System.out.println("first");
			head = newNode;
			tail = newNode;
		}
		//All else
		else if(curNode.get().equals(tail.get()))
		{
			// System.out.println("second");
			tail.setNext(newNode);
			tail = newNode;
		}
		else
		{
			// System.out.println("third");
			newNode.next = curNode.next;
			curNode.next = newNode;
		}
	}

	//delete after
	public void delete(Node curNode)
	{
		Node sucNode;
		// Node temp = head;
		// temp.setNext(head.next);
		
		// Special case, remove head
		if (curNode==null&&head!=null)
		{
			// System.out.println("First");
			sucNode = head.next;
			head = sucNode;

			if(sucNode==null)
			{
				// System.out.println("Inside First");
				tail = null;
			}
		}
		else if(curNode.next!=null)
		{ 
			sucNode = curNode.next.next;
			// System.out.println();
			// System.out.println();
			// System.out.println();
			// System.out.println();
			// System.out.println("Sucnode");
			// System.out.println(sucNode.get());
			// System.out.println("Sucnode end");

			curNode.next = sucNode;
			// System.out.println("Second");
			if(sucNode==null)
			{
				// System.out.println("Inside Second");
				tail = curNode;
			}
		}
	}

	public void printList()
	{
		Node temp = head; // start at the head node
		while (temp != null)
		{
			System.out.print(temp.get() + " ");
			temp = temp.next; // go to next node
		}
		System.out.println();
	}
}
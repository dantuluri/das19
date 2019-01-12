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

	/** Inserts Node newNode after Node curNode
	*  @param curNode Node previous to inserted node
	*  @param newNode Node that is inserted after curNode
	*/
	public void insert(Node curNode, Node newNode)
	{
		//Empty list
		if(head == null)
		{
			head = newNode;
			tail = newNode;
		}
		//All else
		else if(curNode.get().equals(tail.get()))
		{
			tail.setNext(newNode);
			tail = newNode;
		}
		else
		{
			newNode.next = curNode.next;
			curNode.next = newNode;
		}
	}

	/** Deletes Node after Node curNode.
	*  @param curNode Node previous to deleted node
	*/
	public void delete(Node curNode)
	{
		Node sucNode;

		if (curNode==null&&head!=null)
		{
			sucNode = head.next;
			head = sucNode;

			if(sucNode==null)
			{
				tail = null;
			}
		}
		else if(curNode.next!=null)
		{ 
			sucNode = curNode.next.next;
			curNode.setNext(sucNode);
			if(sucNode==null)
			{
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
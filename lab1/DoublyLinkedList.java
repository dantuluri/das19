import java.io.*;
import java.util.*;

/**
 * DoublyLinkedList.java
 * Doubly Linked List that links any type of data
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/14/2019
 */

public class DoublyLinkedList <T>
{
	private Node head;
	private Node tail;
	private Node prev;

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
		//Making some nodes
		Node apple = new Node("apple");
		Node peach = new Node("peach");
		Node carrot = new Node("carrot");
		Node strawberry = new Node("strawberry");
		Node banana = new Node("banana");

		//Inserting the nodes into the Singly Linked List
		insert(head, apple);
		insert(apple, peach);
		insert(peach, carrot);
		insert(carrot, strawberry);
		insert(strawberry, banana);

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
		// List empty
		if (head == null)
		{ 
			head = newNode
			tail = newNode
		}
		// Insert after tail
		else if (curNode.get().equals(tail.get()))
		{ 
			tail.setNext(newNode);
			newNode.prev
			newNode->prev = list->tail
			list->tail = newNode
		}
		// Insert in middle
		else
		{
			sucNode = curNode->next
			newNode->next = sucNode
			newNode->prev = curNode
			curNode->next = newNode
			sucNode->prev = newNode
		}
	}

	/** Deletes Node after Node curNode.
	*  @param curNode Node previous to deleted node
	*/
	public void delete(Node curNode)
	{
		//Temporary Node
		Node sucNode;

		//Remove head
		if (curNode==null&&head!=null)
		{
			sucNode = head.next;
			head = sucNode;

			//Removed last item
			if(sucNode==null)
			{
				tail = null;
			}
		}

		//Remove any node
		else if(curNode.next!=null)
		{ 
			sucNode = curNode.next.next;
			curNode.setNext(sucNode);

			//Remove tail
			if(sucNode==null)
			{
				tail = curNode;
			}
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
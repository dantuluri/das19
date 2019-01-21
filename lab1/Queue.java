import java.io.*;
import java.util.*;

/**
 * Queue.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/20/2019
 */

public class Queue <T>
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

	public static void main(String[]args)
	{
		Queue pancake = new Queue();
		pancake.run();
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
		push(peach);
		list.printList();
		push(carrot);
		list.printList();
		System.out.println("Popping");
		Node popped = pop();
		System.out.println(popped.get());
		System.out.println(getLength());
	}

	public void push(Node curNode)
	{
		list.append(curNode);
		length++;
	}

	public Node pop()
	{
		Node poppedItem = list.head;
		list.delete(list.head);
		length--;
		return poppedItem;
	}

	public Node peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("No element found to peek");
		return list.head;
	}

	public boolean isEmpty()
	{
		if(list.head!=null)
			return true;
		return false;
	}

	public int getLength()
	{
		return length;
	}

}

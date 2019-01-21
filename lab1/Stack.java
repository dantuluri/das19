import java.io.*;
import java.util.*;

/**
 * Stack.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/20/2019
 */

public class Stack <T>
{
	private Node top;
	private Node bottom;
	private int length;

	public Stack()
	{
		DoublyLinkedList list = new DoublyLinkedList();
		top = null;
		bottom = null;
		length = 0;
	}

	public static void main(String[]args)
	{

	}

	public void push(Node curNode)
	{
	}

	public Node pop(Node curNode)
	{
		if(head==null)
			return;
		Node traverseNode = top;
		top = top.prev;
		top.setNext(null);
		length--;
		return traverseNode;
	}

	public Node peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("No element found to peek");
		return top;
	}

	public boolean isEmpty()
	{
		if(top!=null)
			return true;
		return false;
	}

	public int getLength()
	{
		return length;
	}

}

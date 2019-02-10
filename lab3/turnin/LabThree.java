import java.io.*;
import java.util.*;

/**
 * Lab3.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 2/7/2019
 */

public class LabThree {
    public static void main(String[] args) {
        LabThree three = new LabThree();
        three.run();
    }

    public void run() {
        Hashtable mod = new Hashtable(0);
        Hashtable mid = new Hashtable(1);
        Hashtable mul = new Hashtable(2);
    }
}

class Hashtable
{
    private DynamicArray<HashEntry> table = new DynamicArray<HashEntry>();
    private int collisions = 0;
    private int hashOption;

    public Hashtable(int s, int option)
    {
        this.table.resize(s);
        this.hashOption = option;
    }

    public int size()
    {
        return size;
    }

    public int getCollisions()
    {
        return collisions;
    }

    public String get(String key)
    {
        int hash = getHash(key);
        return table.get(hash).getValue();
    }

    public HashEntry get(int index)
    {
        return table.index();
    }

    public void put(String value)
    {
        int hash = getHash(
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
		index = 0;

	}

	public void insert(Object item) 
	{ 
		if(index>size)
			resize();
		index++;
		container[index] = item;
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
		if(s>size)
		{
			size=s;
			Object temp[] = new Object[size];

			for(int i = 0; i<container.length;i++)
				temp[i]=container[i];

			container=temp;
		}
	}

	public int size()
	{
		return size;
	}
}

class HashEntry {
    private int key = 0;
    private String value = "";

    public HashEntry(int k, String v) {
        key = k;
        value = v;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

class Customer {
    public String firstname;
    public String lastname;
    public int keyId;

    public Customer(String fname, String lname, int lid) {
        this.firstname = fname;
        this.lastname = lname;
        this.keyId = lid;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public int getId() {
        return keyId;
    }
}

class DoublyLinkedList<T> {
    public Node head;
    public Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Inserts Node newNode after Node curNode
     * 
     * @param curNode Node previous to inserted node
     * @param newNode Node that is inserted after curNode
     */
    public void insert(Node curNode, Node newNode) {
        // Temporary Node
        Node sucNode;

        // List empty
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        // Insert after tail
        else if (curNode == tail) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            sucNode = curNode.next;
            newNode.setNext(sucNode);
            newNode.setPrev(curNode);
            curNode.setNext(newNode);
            sucNode.setPrev(newNode);
        }
    }

    /**
     * Deletes Node after Node curNode.
     * 
     * @param curNode Node previous to deleted node
     */
    public void delete(Node curNode) {
        // Temporary Node
        Node sucNode;
        Node predNode;
        sucNode = curNode.next;
        predNode = curNode.prev;

        if (sucNode != null) {
            sucNode.prev = predNode;
            sucNode.setPrev(predNode);
        }

        if (predNode != null) {
            predNode.setNext(sucNode);
        }

        // Removed head
        if (curNode == head) {
            head = sucNode;
        }

        // Removed tail
        if (curNode == tail) {
            tail = predNode;
        }
    }

    // Inserts node at the start of the list
    public void prepend(Node curNode) {
        curNode.setNext(head);
        curNode.setPrev(null);

        if (head != null)
            head.setPrev(curNode);

        head = curNode;

    }

    // Inserts node at the end of the list
    public void append(Node curNode) {

        Node traverseNode = head;

        curNode.setNext(null);

        if (head == null) {
            curNode.setPrev(null);
            head = curNode;
            return;
        }

        while (traverseNode.next != null)
            traverseNode = traverseNode.next;

        traverseNode.setNext(curNode);
        curNode.setPrev(traverseNode);
    }

    // Returns head of Doubly linked list
    public Node getHead() {
        return head;
    }

    // Returns tail of Doubly linked list
    public Node getTail() {
        return tail;
    }

    /**
     * Prints Doubly Linked List
     */
    public void printList() {
        Node temp = head; // start at the head node
        while (temp != null) {
            System.out.print(temp.get() + " ");
            temp = temp.next; // go to next node
        }
        System.out.println();
        System.out.println();
    }

    public int size() {
        int sizeCount = 0;
        Node temp = head; // start at the first node
        while (temp != null) {
            sizeCount++;
            temp = temp.next; // go to next node
        }
        return sizeCount;
    }

}

class Node<T> {
    T data;
    Node next;
    Node prev;

    public Node(T t) {
        data = t;
        next = null;
        prev = null;
    }

    public T get() {
        return data;
    }

    public void set(T t) {
        data = t;
    }

    public void setNext(Node newNode) {
        next = newNode;
    }

    public void setPrev(Node newNode) {
        prev = newNode;
    }
}
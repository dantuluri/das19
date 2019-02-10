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
        Hashtable mod = new Hashtable(10, 0);
        Hashtable mid = new Hashtable(10, 1);
        Hashtable mul = new Hashtable(10, 2);
    }
}

class Hashtable {
    private DynamicArray<DoublyLinkedList<Node>> table = new DynamicArray<DoublyLinkedList<Node>>();
    private int collisions = 0;
    private int hashOption;

    public Hashtable(int s, int option) {
        this.table.resize(s);
        this.hashOption = option;
    }

    public int size() {
        return size;
    }

    public int getCollisions() {
        return collisions;
    }

    public void HashInsert (int item) {
        if (HashSearch(hashTable, item->key) == null) {
           bucketList = hashTable[Hash(item->key)]
           node = Allocate new linked list node
           node->next = null
           node->data = item
           ListAppend(bucketList, node)
        }
     }

    public HashSearch(int key) {
        DoublyLinkedList bucketList = table.get(getHash(key));
        itemNode = ListSearch(bucketList, key)
        if (itemNode is not null)
           return itemNode->data
        else
           return null
     }

     public Node listSearch(DoublyLinkedList dll, int key)
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


    public int getHash(int key) {
        // mod
        if (hashOption == 0) {
            System.out.println("OPTION 0: "+key);
            System.out.println(key % 10);
            System.out.println();
            return (key % 10);
        }
        // mid
        if (hashOption == 1) {
            int R = 3;
            int squaredKey = key * key;

            int lowBitsToRemove = (32 - R) / 2;
            int extractedBits = squaredKey >> lowBitsToRemove;
            extractedBits = extractedBits & (0xFFFFFFFF >> (32 - R));

            return extractedBits % 250;
        }
        // mul
        if (hashOption == 2) {
            int stringHash = 5381;

            String key = Integer.toString(data);
            for (int i = 0; i < key.length(); i++) {
                Character c = key.charAt(i);
                stringHash = (stringHash * 33) + c;
            }
            return stringHash % 250;
        }
        return -1;
    }
}

class DynamicArray<T> {
    T[] container;
    private int size;
    private int index;

    public DynamicArray(T insert) {
        container = new T[10];
        size = 10;
        for (int i = 0; i < size; i++)
            container[i] = insert;
        index = 0;

    }

    public void insert(T item) {
        if (index > size)
            resize();
        index++;
        container[index] = item;
    }

    public T get(int i) {
        return container[i];
    }

    public void resize() {
        size *= 2;
        T temp[] = new T[size];

        for (int i = 0; i < container.length; i++)
            temp[i] = container[i];

        container = temp;
    }

    public void resize(int s) {
        if (s > size) {
            size = s;
            T temp[] = new T[size];

            for (int i = 0; i < container.length; i++)
                temp[i] = container[i];

            container = temp;
        }
    }

    public int size() {
        return size;
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
import java.io.*;
import java.util.*;

/**
 * Lab3.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 2/7/2019
 */

public class Lab3 {

    Hashtable mod = new Hashtable(10, 0);
    Hashtable mid = new Hashtable(10, 1);
    Hashtable mul = new Hashtable(10, 2);

    public static void main(String[] args) {
        Lab3 three = new Lab3();
        three.run();
    }

    public void run() {
        try (Scanner reader = new Scanner(new FileReader("Customer.csv"))) {
            reader.useDelimiter(",");
            while (reader.hasNextLine()) {
                // Input
                String inputInitial = reader.nextLine();
                String[] tempArray = inputInitial.split(",");
                String fname = tempArray[0];
                String lname = tempArray[1];
                String tempId = tempArray[2];
                tempId = tempId.substring(0, tempId.indexOf("-") - 2);
                int id = Integer.parseInt(tempId);

                Customer visitor = new Customer(fname, lname, id);

                mod.HashInsert(visitor);
                mid.HashInsert(visitor);
                mul.HashInsert(visitor);
            }
            reader.close();
        } catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Collisions for Modulo Hashing:                " + mod.getCollisions());
        System.out.println("--------------------------------------------------");
        System.out.println("Collisions for Mid-square Hashing:            " + mid.getCollisions());
        System.out.println("--------------------------------------------------");
        System.out.println("Collisions for Multiplicative string Hashing: " + mul.getCollisions());
        System.out.println("--------------------------------------------------");

    }
}

class Hashtable {
    DynamicArray<DoublyLinkedList> table = new DynamicArray<DoublyLinkedList>();
    private int collisions = 0;
    private int hashOption;

    public Hashtable(int s, int option) {
        tableFill();
        this.hashOption = option;
    }

    public void tableFill() {
        for (int i = 0; i < 10; i++) {
            table.insert(new DoublyLinkedList());
        }

    }

    public void tableResize() {
        int len = table.container.length;
        table.size = len * 2;
        table.container = Arrays.copyOf(table.container, table.size);
        for (int i = len; i < (len * 2); i++) {
            table.insert(new DoublyLinkedList());
        }
    }

    public int getCollisions() {
        for (int i = 0; i < table.getSize(); i++) {
            collisions += (table.get(i).size() - 1);
        }
        return collisions;
    }

    public void HashInsert(Customer visitor) {
        int key = visitor.getId();
        if (HashSearch(key) == -1) {
            DoublyLinkedList bucketList = table.get((getHash(key)));
            Node item = new Node(key);
            item.setNext(null);
            bucketList.append(item);
        }
    }

    public int HashSearch(int key) {
        while (getHash(key) > table.getSize() || getHash(key) == table.getSize()) {
            tableResize();
        }
        DoublyLinkedList bucketList = table.get(getHash(key));
        Node itemNode = listSearch(bucketList, key);

        if (itemNode != null)
            return ((int) itemNode.get());
        else
            return -1;
    }

    public Node listSearch(DoublyLinkedList dll, int key) {
        Node temp = dll.getHead(); // start at the head node
        if (temp != null) {

            int iter = (int) temp.get();
            while (iter != key) {
                if (temp.next != null) {
                    temp = temp.next; // go to next node
                    iter = (int) temp.get();
                } else {
                    return null;
                }

            }
        }

        return temp;
    }

    public int getHash(int key) {
        // mod
        if (hashOption == 0) {
            return (key % 10);
        }
        // mid
        if (hashOption == 1) {
            int R = 3;
            int squaredKey = (int) Math.pow(key, 2);
            int lowBitsToRemove = (32 - R) / 2;
            int extractedBits = squaredKey >> lowBitsToRemove;
            extractedBits = extractedBits & (0xFFFFFFFF >> (32 - R));

            return extractedBits % 250;
        }
        // mul
        if (hashOption == 2) {
            int stringHash = 7;

            String data = Integer.toString(key);
            for (int i = 0; i < data.length(); i++) {
                Character c = data.charAt(i);
                stringHash = (stringHash * 3) + c;
            }
            return stringHash % 250;
        }

        return -1;
    }
}

class DynamicArray<T> {
    private int index = 0;
    public int size = 10;
    public Object container[];

    public DynamicArray() {
        container = new Object[10];
    }

    public void insert(T item) {
        if (index == size) {
        }
        container[index++] = item;
    }

    private void resize() {
        size = container.length * 2;
        container = Arrays.copyOf(container, size);
        Obkect[] new container = new Object[contianer.length*2];
        for(int i = 0;i<container.length;i++)
            newContainer[i] = container[i];
        container = newContainer;
        size = container.length
    }

    public T get(int i) {
        return (T) container[i];
    }

    public int getSize() {
        return size;
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
            temp = temp.next; // go to next node
        }
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

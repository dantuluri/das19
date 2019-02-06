import java.io.*;

/**
 * Lab3.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 1/22/2019
 */

public class Lab
{
	Hashtable mod = new Hashtable(0);
	Hashtable mid = new Hashtable(1);
	Hashtable mul = new Hashtable(2);
	public static void main(String[]args)
	{
		Lab three = new Lab();
		three.run();
	}
	public void run()
	{
		try (Scanner reader = new Scanner(new FileReader("Customer.csv")))
		{
			reader.useDelimiter(",");
			while (reader.hasNextLine()){
				String inputInitial = reader.nextLine();
				String[] tempArray = inputInitial.split(",");
				String fname = tempArray[0];
				String lname = tempArray[1];
				String tempId = tempArray[2];
				tempId = tempId.substring(0,tempId.indexOf("-"))+tempId.substring(tempId.indexOf("-")+1,tempId.indexOf("-")+2);
				System.out.println("TEMTID: "+tempId);
				int id = Integer.parseInt(tempId);
				// String sid = reader.next();
				// sid
				// System.out.println("sid: "+sid);
				// int id = Integer.parseInt(sid);
				// Customer visitor = new Customer(fname,lname,id);
				// mod.insert(visitor);
				// mid.insert(visitor);
				// mul.insert(visitor);
			}
			reader.close();
		}
		catch (IOException | InputMismatchException ex)
		{
			ex.printStackTrace();
		}
	}

}



class Hashtable <T>
{
	ArrayList<LinkedList<Node>> table = new ArrayList<LinkedList<Node>>();
	int collisions = 0;
	int s = 1;
	int option;

	public Hashtable(int input)
	{
		option = input;
	}
	int size() { return table.size(); }
	int getcollisions() { return collisions; }

	// public String get(String key)
	// {
	// 	int hash = getHash(key,option);
	// 	return table.get(hash);
	// }
	public LinkedList get(int index)
	{
		return table.get(index);
	}

	public void insert(Customer item) {
		LinkedList<Node> bucketList;
		if (HashSearch(item.id) == null) { 
			bucketList = table.get(getHash(item.id, option));
			Node input = new Node(item);
			input.setNext(null);
			bucketList.add(input);
		}
	}

	// public void hashRemove(item)
	// {	
	// 	LinkedList<Node> bucketList;
	// 	bucketList = table.get(getHash(item.id, option));
	// 	itemNode = listSearch(bucketList, item.id)
	// 	if (itemNode is not null) {
	// 		bucketList.remove(itemNode);
	// 	} 
	// }

	public String HashSearch(int key) {
		LinkedList<Node> bucketList;
		bucketList = table.get(getHash(key, option));
		Node itemNode = listSearch(bucketList, key);
		if (itemNode != null)
			return (String)itemNode.data;
		else
			return "";
	}

	public Node listSearch(LinkedList<Node> list, int data) 
	{ 
		Node current = list.peek();
		while (current != null) 
		{ 
			if (current.data.equals(data)) 
                return current;
            current = current.next; 
        } 
        return null;    //data not found 
    } 


	// public void put(String value) 
	// {
	// 	int hash = getHash(value, option);
	// 	HashEntry entry(hash,value);
	// 	int key = table.get(hash).getKey();
	// 	if (key > 0)
	// 		collisions++;
	// 	table.get(hash) = entry;
	// }

    public int getHash(int data, int option)
    {
    	//mod
    	if(option==0)
    	{
    		System.out.println("DAT: "+data);
    		return (data%250);
    	}
    	//mid
    	if(option==1)
    	{
    		int R = 3;
    		int key = data;
    		int squaredKey = key * key;

    		int lowBitsToRemove = (32 - R) / 2;
    		int extractedBits = squaredKey >> lowBitsToRemove;
    		extractedBits = extractedBits & (0xFFFFFFFF >> (32 - R));

    		return extractedBits % 250;
    	}
    	//mul
    	if(option==2)
    	{
    		int stringHash = 5381; 

			String key = Integer.toString(data);
    		for (int i = 0;i<key.length();i++) {
    			Character c = key.charAt(i);
    			stringHash = (stringHash * 33) + c;
    		}
    		return stringHash % 250;
    	}
    	return -1;
    }
}

class Customer
{
	String firstname;
	String lastname;
	int id;

	public Customer(String fname, String lname, int lid)
	{
		firstname = fname;
		lastname = lname;
		id = lid;
		System.out.println("IDST: "+ id);
		System.out.println("Name: "+firstname+" "+lastname+" bend");
	}

	public String getFirstName()
	{
		return firstname;
	}
	public String getLastName()
	{
		return lastname;
	}
	public int getId()
	{
		return id;
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

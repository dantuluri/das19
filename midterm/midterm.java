//Surya Dantuluri
//22C Winter 2019 Midterm

1.
public int pop() 
{
    if (q1.peek() == null) 
    {
        int i = 0;
        return i;
    } 
    else 
    {
        int pop = q1.remove();
        return pop;
    }
}

public void push(int data)
{
    if (q1.peek() == null)
    {
        q1.add(data);
    }
    else
    {
        for (int i = GetLength(q1); i > 0; i--) 
        {
            //Given that the Queue returns the removed value when removed
            q2.add(q1.remove());
        }
        q1.add(data);
        for (int j = GetLength(q2); j > 0; j--)
        {
            q1.add(q2.remove());
        }

    }
}



2. 
//bigList is the big 2D Linked List
//longList is the long 1D SLL
public SinglyLinkedList makeOneDimensionalLinkedList(LinkedList bigList, Node bigListHead)
{
    SinglyLinkedList longList = new SinglyLinkedList();

    Node temp = bigListHead; // start at the head node

    while (temp != null)
    {
        LinkedList row = temp.data;
        Node rowHead = row.head;
        while(rowHead != null)
        {
            longList.append(rowHead);
        }
        temp = temp.next; // go to next node
    }

    return longList;
}

This algorithm has a Big-O complexity of O(n^2) since it has two while loops that loop n times.



3.
Instead of simply swapping numbers mindlessly, this algorithm seeks to ensure the lowest number
is placed at the correct position. Imagine an instance where the array is filled with big numbers
and there's a small number at the end. Both Bubble Sort and this Bubble Sort variant work relatively the same 
until the loop reaches the small number. Bubble Sort would just move the small number down an index. This variant, 
however, would loop until this small number is the first element in the array. Although both the variant and Bubble Sort
itself have the same Big-O time compelxity, this variant will work better when there is a lot of variance in the 
data. 



4.
public int getCollisions() {
        for (int i = 0; i < table.getSize(); i++)
            collisions += (table.get(i).size() - 1);
        return collisions;
    }



5.
This is a Binary Search Tree search algorithm. Both this implementation and the regular looping implementation
have a Big-O time complexity of O(n) since they both could loop through every single element
before finding the data they're looking for. Both a Stack and a Queue could work. In this case,
the Stack is unique compared to a Queue implementation since this Stack implementation would search through the entire
left branch before searching through the entire right branch to find the data provided in the parameters.
Queue on the other hand would search by level. The Stack is needed for this implementation since it supports popping
, pushing, and the top functions. No other data structure (other than the Queue) can store a call stack for 
a recursive search function such as this.

















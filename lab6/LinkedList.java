


public class LinkedList<T> extends List<T> {
	
	private static int size = 0;
	
	@Override
	public void insert(LinkedList<T> list, ListNode<T> current, ListNode<T> toAdd)
	{
		if(getHead() == null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else if(current == getTail())
		{
			list.getTail().setNext(toAdd);
			toAdd.setPrevious(list.getTail());
			list.setTail(toAdd);
		}
		else
		{
			ListNode<T> successive = current.getNext();
			toAdd.setNext(successive);
			toAdd.setPrevious(current);
			current.setNext(toAdd);
			successive.setPrevious(toAdd);
		}
		size++;
	}

	@Override
	public void remove(LinkedList<T> list, ListNode<T> current)
	{
		ListNode<T> successive = current.getNext();
		ListNode<T> previous = current.getPrevious();
		if(successive!=null)
		{
			successive.setPrevious(previous);
		}
		if(previous!=null)
		{
			previous.setNext(successive);
		}
		if(current==list.getHead())
		{
			list.setHead(successive);
		}
		if(current==list.getTail())
		{
			list.setTail(previous);
		}
		size--;
	}
	
	public void append(LinkedList<T> list, ListNode<T> toAdd)
	{
		if(list.getHead()==null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else
		{
			list.getTail().setNext(toAdd);
			toAdd.setPrevious(list.getTail());
			list.setTail(toAdd);
		}
		size++;
	}
	
	public void prepend(LinkedList<T> list, ListNode<T> toAdd)
	{
		if(list.getHead()==null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else
		{
			toAdd.setNext(list.getHead());
			list.getHead().setPrevious(toAdd);
			list.setHead(toAdd);
		}
		size++;
	}
	public static int getSize()
	{
		return size;
	}
	public void printList()
	{
		System.out.println("Current list: ");
		for(ListNode<T> i = this.getHead(); i != null; i = i.getNext())
		{
			System.out.print(i.getValue() + " ");
		}
		System.out.println();
	}
}

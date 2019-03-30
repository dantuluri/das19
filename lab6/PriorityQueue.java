

public class PriorityQueue<T>
{
	private LinkedList<Prioritizable<T>> list;
	
	public PriorityQueue()
	{
		list = new LinkedList<Prioritizable<T>>();
	}
	
	public void add(T toAdd, int priority)
	{
		Prioritizable<T> p = new Prioritizable<T>(priority, toAdd);
		ListNode<Prioritizable<T>> node = new ListNode<Prioritizable<T>>(p, null, null);
		if(list.getHead() == null)
		{
			list.insert(list, null, node);
		}
		else
		{
			ListNode<Prioritizable<T>> previous = null;
			boolean inserted = false;
			for(ListNode<Prioritizable<T>> i = list.getHead(); i != null; i = i.getNext())
			{
				if(i.getValue().getPriority() >= priority && previous != null)
				{
					list.insert(list, previous, node);
					inserted = true;
					break;
				}
				else if(i.getValue().getPriority() >= priority)
				{
					list.prepend(list, node);
					inserted = true;
					break;
				}
				previous = i;
			}
			if(!inserted)
			{
				list.append(list, node);
			}
		}
	}
	
	public T remove()
	{
		ListNode<Prioritizable<T>> node = list.getHead();
		list.remove(list, list.getHead());
		return node.getValue().getValue();
	}
	
	public boolean isEmpty()
	{
		if(list.getHead() == null && list.getTail() == null)
		{
			return true;
		}
		return false;
	}
	
	public void printList()
	{
		System.out.println("Current list: ");
		for(ListNode<Prioritizable<T>> i = list.getHead(); i != null; i = i.getNext())
		{
			Prioritizable<T> value = i.getValue();
			TreeNode<T> v = (TreeNode<T>) value.getValue();
			System.out.print(value.getPriority() + "-" + ((DecimalNode)(v.getValue())).getValue() + " ");
		}
		System.out.println();
	}
	
	public int size()
	{
		int count = 0;
		for(ListNode<Prioritizable<T>> i = list.getHead(); i != null; i = i.getNext())
		{
			count++;
		}
		return count;
	}
}



public abstract class List<T> {

	private ListNode<T> head;
	private ListNode<T> tail;
	
	public List()
	{
		this.head = null;
		this.tail = null;
	}
	public ListNode<T> getHead()
	{
		return head;
	}
	public ListNode<T> getTail()
	{
		return tail;
	}
	protected void setHead(ListNode<T> toHead)
	{
		head = toHead;
	}
	protected void setTail(ListNode<T> toTail)
	{
		tail = toTail;
	}
	public abstract void insert(LinkedList<T> list, ListNode<T> current, ListNode<T> toAdd);
	public abstract void remove(LinkedList<T> list, ListNode<T> current);
}

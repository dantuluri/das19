

public class ListNode<E>
{
	
	private E value;
	private ListNode<E> next;
	private ListNode<E> previous;
	
	public ListNode(E item, ListNode<E> previous, ListNode<E> next)
	{
		value = item;
		this.next = next;
		this.previous = previous;
	}
	
	public void setNext(ListNode<E> next)
	{
		this.next = next;
	}
	public ListNode<E> getNext()
	{
		return next;
	}
	public void setPrevious(ListNode<E> previous)
	{
		this.previous = previous;
	}
	public ListNode<E> getPrevious()
	{
		return previous;
	}
	public E getValue()
	{
		return value;
	}
}

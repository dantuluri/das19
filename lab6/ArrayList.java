

public class ArrayList<E> {

	Object[] container;
	private int size;
	private int length;
	
	public ArrayList()
	{
		container = new Object[10];
		size = 10;
	}
	
	public void insert(E a, int i)
	{
		if(size == container.length-1)
		{
			resize();
		}
		while(container.length-1 < i)
		{
			resize();
		}
		container[i] = a;
	}
	
	private void resize()
	{
		Object[] newContainer = new Object[container.length*2];
		for(int i=0; i<container.length; i++)
		{
			newContainer[i] = container[i];
		}
		container = newContainer;
		size = container.length;
	}
	
	public E get(int i)
	{
		return (E) container[i];
	}
	
	public int getLength()
	{
		return size;
	}
}



public class Prioritizable<T> {

	private int priority;
	private T value;
	
	public Prioritizable(int priority, T value)
	{
		this.priority = priority;
		this.value = value;
	}
	
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public void setValue(T value)
	{
		this.value = value;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public T getValue()
	{
		return value;
	}
}

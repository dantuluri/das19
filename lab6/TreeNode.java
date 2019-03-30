

public class TreeNode<Comparable> {
	
	private Comparable value;
	private TreeNode<Comparable> left;
	private TreeNode<Comparable> right;
	
	public TreeNode(Comparable value)
	{
		this.value = value;
		left = null;
		right = null;
	}
	
	public TreeNode(Comparable value, TreeNode<Comparable> left, TreeNode<Comparable> right)
	{
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public void setLeft(TreeNode<Comparable> newLeft)
	{
		left = newLeft;
	}
	
	public void setRight(TreeNode<Comparable> newRight)
	{
		right = newRight;
	}
	
	public void setValue(Comparable value)
	{
		this.value = value;
	}
	
	public TreeNode<Comparable> getRight()
	{
		return right;
	}
	
	public TreeNode<Comparable> getLeft()
	{
		return left;
	}
	
	public Comparable getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return value + "";
	}
}

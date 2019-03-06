import java.io.*;
import java.util.*;

/**
 * Lab5.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 3/8/2019
 */

public class BalancingTree
{
	public static void main(String[]args)
	{
		BalancingTree tree = new BalancingTree();
		tree.run();
	}

	public void run()
	{

	}

	public booleanvhrvkm=Monogonic invrsdo=in
}

class BinarySearchTree
{
	public int size, count;
	private TreeNode root;

	public BinarySearchTree()
	{
		size = 0;
		count = 0;
		root = null;
	}
	public void insert(TreeNode leaf)
	{
		if(root==null)
		{
			root = leaf;
			root.setLeft(null);
			root.setRight(null);
		}
		else
		{
			TreeNode iterate = root;
			while(iterate!=null)
			{
				if((Integer)leaf.get()<(Integer)iterate.get())
				{
					if(iterate.left==null)
					{
						iterate.setLeft(leaf);
						iterate = null;
					}
					else
					{
						iterate = iterate.left;
					}
				}
				else
				{
					if(iterate.right==null)
					{
						iterate.setRight(leaf);
						iterate = null;
					}
					else
					{
						iterate = iterate.right;
					}
				}
			}
			leaf.setLeft(null);
			leaf.setRight(null);
		}
	}

	public TreeNode search(TreeNode leaf)
	{
		TreeNode iterate = root;
		while(iterate!=null)
		{
			if((Integer)leaf.get()==(Integer)iterate.get())
			{
				return iterate;
			}
			else if((Integer)leaf.get()<(Integer)iterate.get())
			{
				iterate = iterate.left;
			}
			else
			{
				iterate = iterate.right;
			}
		}
		return null;
	}

	public void inOrder(TreeNode leaf)
	{
		if(leaf==null)
			return;
		inOrder(leaf.left);
		System.out.println((Integer)leaf.get());
		inOrder(leaf.right);
	}

	public boolean isBalanced()
	{
		int left = countInOrder(root.left);
		int right = countInOrder(root.right);
		System.out.println("Left Branch: "+left+" Right Branch: "+right);
		if(left==right||Math.abs(left-right)==1)
			return true;
		else
			return false;
	}

	public int countInOrder(TreeNode leaf)
	{
		if(leaf==null)
			return count;
		countInOrder(leaf.left);
		count++;
		countInOrder(leaf.right);
		return count;
	}

	public int getHeight(TreeNode leaf)
	{
		if(leaf==null)
			return -1;
		
		int leftHeight = getHeight(leaf.left);
		int rightHeight = getHeight(leaf.right);
		return 1+Math.max(leftHeight,rightHeight);
	}
	
	public TreeNode getRoot()
	{
		return root;
	}
}

class TreeNode<T>
{
	T data;
	TreeNode<T> left;
	TreeNode<T> right;

	public TreeNode(T t)
	{
		data = t;
		left = null;
		right = null;
	}

	public T get()
	{
		return data;
	}

	public void set(T t)
	{
		data = t;
	}

	public void setLeft(TreeNode nextLeaf)
	{
		left = nextLeaf;
	}

	public void setRight(TreeNode prevLeaf)
	{
		right = prevLeaf;
	}
}
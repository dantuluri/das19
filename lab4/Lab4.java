import java.io.*;
import java.util.*;

/**
 * Lab4.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 2/12/2019
 */

public class Lab4 
{
	public static void main(String[] args) 
	{
		Lab4 four = new Lab4();
		four.run();
	}

	public void run() 
	{
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(new TreeNode(id));
		// try (Scanner reader = new Scanner(new FileReader("Customer.csv"))) 
		// {
		// 	reader.useDelimiter(",");
		// 	while (reader.hasNextLine()) 
		// 	{
        //         // Input
		// 		String inputInitial = reader.nextLine();
		// 		String[] tempArray = inputInitial.split(",");
		// 		String tempId = tempArray[2];
		// 		tempId = tempId.substring(0, tempId.indexOf("-") - 2);
		// 		int id = Integer.parseInt(tempId);
				
		// 		tree.insert(new TreeNode(id));

		// 	}
		// 	reader.close();
		// }
		// catch (IOException | InputMismatchException ex) 
		// {
		// 	ex.printStackTrace();
		// }

		tree.inOrder(tree.getRoot());
	}
}

class BinarySearchTree
{
	public int size;
	private TreeNode root;

	public BinarySearchTree()
	{
		size = 0;
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












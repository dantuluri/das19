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
		tree.insert(new TreeNode(0));
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


		// 	}
		// 	reader.close();
		// }
		// catch (IOException | InputMismatchException ex) 
		// {
		// 	ex.printStackTrace();
		// }
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
			root.left = null;
			root.right = null;
		}
		else
		{
			TreeNode iterate = root;
			while(iterate!=null)
			{
				if((Integer)leaf.data<(Integer)iterate.data)
				{
					if(iterate.left==null)
					{
						iterate.left = leaf;
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
						iterate.right = leaf;
						iterate = null;
					}
					else
					{
						iterate = iterate.right;
					}
				}
			}
			leaf.left = null;
			leaf.right = null;
		}
	}
}



class TreeNode<T>
{
	T data;
	TreeNode<T> left;
	TreeNode<T> right;
}












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
		try (Scanner reader = new Scanner(new FileReader("Customer.csv"))) 
		{
			reader.useDelimiter(",");
			while (reader.hasNextLine()) 
			{
                // Input
				String inputInitial = reader.nextLine();
				String[] tempArray = inputInitial.split(",");
				String tempId = tempArray[2];
				tempId = tempId.substring(0, tempId.indexOf("-") - 2);
				int id = Integer.parseInt(tempId);


			}
			reader.close();
		}
		catch (IOException | InputMismatchException ex) 
		{
			ex.printStackTrace();
		}
	}
}

class BinarySearchTree
{
	public int size;
	private Node root;
	public BinarySearchTree()
	{
		size = 0;
	}
	public void insert(Node leaf)
	BSTInsert(tree, node) {
		if (tree->root is null)
		  tree->root = node
		  node->left = null
		  node->right = null
		else
		  cur = tree->root
		  while (cur is not null) 
			if (node->key < cur->key)
			   if (cur->left is null)
				 cur->left = node
				 cur = null
			   else
				 cur = cur->left
			else 
			   if (cur->right is null)
				 cur->right = node
				 cur = null
			   else
				 cur = cur->right       
		  node->left = null
		  node->right = null
	   }


}



class TreeNode<T>
{
	T data;
	TreeNode<T> left;
	TreeNode<T> right;
}












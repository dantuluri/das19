import java.io.*;
import java.util.*;

/**
 * Lab5.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 3/10/2019
 */

public class Lab5 
{
	BinarySearchTree tree = new BinarySearchTree();

	public static void main(String[] args) 
	{
		Lab5 five = new Lab5();
		five.run();
	}

	public void run()
	{
		// EDIT NODE COUNT
		int nodes = 10;

		for(int i = 1;i<nodes+1;i++)
		{
			tree.insert(new TreeNode(i));
		}

		//Print Output
		String[] arr = tree.printer();

		System.out.println("Before: ");
		for (int i = 0; i < arr.length-1; i++)
			System.out.print(arr[i]+" - ");
		System.out.print(arr[arr.length-1]);

		System.out.println();
		System.out.println();

		// Pinpoint center
		tree.setRoot(center(tree.getRoot()));
		// System.out.println("Root value "+tree.getRoot().get());

		// Rotate tree
		BinarySearchTree goodTree = verify(tree, tree.getRoot());

		arr = goodTree.printer();
		System.out.println("After: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

	public TreeNode center(TreeNode root) 
	{
		boolean notNull;
		if(root == null)
		{
			// System.out.println("Root is null");
			return null;
		}
		if (root.getRight() != null || root.getLeft() != null) 
		{
			notNull = true;
			if(root.getRight() != null)
				notNull = false;

			Stack<TreeNode> treeStack = new Stack<TreeNode>();

			int height = tree.getHeight(root);
			int size = height/2;

			if(height%2 == 0)
				size -= 1;

			for (int i = 0; i < size; i++) 
			{
				treeStack.push(root);
				if(notNull)
					root = root.getLeft();
				else
					root = root.getRight();
			}

			TreeNode temp = root;
			while (!treeStack.isEmpty()) 
			{
				TreeNode node = treeStack.pop();
				if(notNull)
				{
					node.setLeft(null);
					node.setRight(null);
					root.setRight(node);
					root = root.getRight();
				}
				else
				{
					node.setLeft(null);
					node.setRight(null);
					root.setLeft(node);
					root = root.getLeft();
				}
			}
			return temp;
		} 
		return root;
	}

	public BinarySearchTree verify(BinarySearchTree incompleteTree, TreeNode root) 
	{
		if(root == null) 
			return incompleteTree;

		root.setLeft(center(root.getLeft()));
		root.setRight(center(root.getRight()));

		if(root.getLeft()!=null)
			verify(incompleteTree, root.getLeft());

		if(root.getRight()!=null)
			verify(incompleteTree, root.getRight());

		return tree;
	}
}


class BinarySearchTree
{
	public int size, count;
	private TreeNode root;
	private String[] printStack;

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

	public void setRoot(TreeNode node)
	{
		root = node;
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
			return 0;
		
		int leftHeight = getHeight(leaf.left);
		int rightHeight = getHeight(leaf.right);
		return 1+Math.max(leftHeight,rightHeight);
		// return Math.max(getHeight(inTree, leaf.getLeft()), getHeight(inTree, leaf.getRight())) + 1;
	}
	
	public TreeNode getRoot()
	{
		return root;
	}

	public String[] printer()
	{
		printStack = new String[this.getHeight(this.getRoot())];
		// System.out.println("r "+this.getRoot().get());
		// System.out.println("");
		// System.out.println("debug2 lea"+this.getHeight(this, this.getRoot()));
		for(int i=0; i<printStack.length; i++)
			printStack[i] = "";
		printRecurse(0, this.getRoot());
		return printStack;
	}
	
	private void printRecurse(int level, TreeNode current)
	{
		if(current == null)
			return;
		printStack[level] += current.get() + " ";
		printRecurse(level + 1, current.getLeft());
		printRecurse(level + 1, current.getRight());
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

	public TreeNode getLeft()
	{
		return left;
	}

	public TreeNode getRight()
	{
		return right;
	}
}


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
	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

	public static void main(String[] args) 
	{
		Lab5 five = new Lab5();
		five.run();
	}

	public void run()
	{
		// EDIT NODE COUNT
		int nodes = 20;

		for(int i = 1;i<nodes+1;i++)
		{
			tree.insert(new TreeNode<Integer>(i, null, null));
		}


		//Print Output
		String[] arr = tree.expressAsArray();

		System.out.println("Before: ");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i]+" - ");
		}
		System.out.print(arr[arr.length-1]);

		System.out.println();
		System.out.println();

		TreeNode
		// Pinpoint center
		tree.setRoot(center(tree.getRoot()));

		// Rotate tree
		BinarySearchTree<Integer> newTree = verify(tree.getRoot());

		arr = newTree.expressAsArray();
		System.out.println("After: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

	public TreeNode<Integer> center(TreeNode<Integer> root) 
	{
		boolean notNull;
		if(root == null)
		{
			return null;
		}
		if (root.getRight() != null || root.getLeft() != null) 
		{
			notNull = true;
			if(root.getRight() != null)
				notNull = false;

			Stack<TreeNode<Integer>> treeStack = new Stack<TreeNode<Integer>>();

			int height = tree.treeHeight(tree, root);
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

			TreeNode<Integer> findRoot = root;
			while (!treeStack.isEmpty()) 
			{
				TreeNode<Integer> node = treeStack.pop();
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
			return findRoot;
		} 
		return root;
	}

	public BinarySearchTree<Integer> verify(TreeNode<Integer> root) 

	{
		if(root == null) 
			return inputTree;

		root.setLeft(center(inputTree, root.getLeft()));
		root.setRight(center(inputTree, root.getRight()));

		if(root.getLeft()!=null)
			verify(inputTree, root.getLeft());
		if(root.getRight()!=null)
			verify(inputTree, root.getRight());

		return inputTree;
	}
}


class BinarySearchTree<E>
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

	public String[] expressAsArray()
	{
		arrayRep = new String[this.treeHeight(this, this.getRootNode())];
		for(int i=0; i<arrayRep.length; i++)
			arrayRep[i] = "";
		expressAsArrayHelper(0, this.getRootNode());
		return arrayRep;
	}
	
	private void expressAsArrayHelper(int level, TreeNode<E> current)
	{
		if(current == null)
		{
			return;
		}
		arrayRep[level] += current.getValue() + " ";
		expressAsArrayHelper(level + 1, current.getLeft());
		expressAsArrayHelper(level + 1, current.getRight());
	}

	public void setRoot(TreeNode<E> node)
	{
		root = node;
	}


}

class LinkedList<T> extends List<T> 
{
	private static int size = 0;
	
	@Override
	public void insert(LinkedList<T> list, ListNode<T> current, ListNode<T> toAdd)
	{
		if(getHead() == null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else if(current == getTail())
		{
			list.getTail().setNext(toAdd);
			toAdd.setPrevious(list.getTail());
			list.setTail(toAdd);
		}
		else
		{
			ListNode<T> successive = current.getNext();
			toAdd.setNext(successive);
			toAdd.setPrevious(current);
			current.setNext(toAdd);
			successive.setPrevious(toAdd);
		}
		size++;
	}

	@Override
	public void remove(LinkedList<T> list, ListNode<T> current)
	{
		ListNode<T> successive = current.getNext();
		ListNode<T> previous = current.getPrevious();
		if(successive!=null)
		{
			successive.setPrevious(previous);
		}
		if(previous!=null)
		{
			previous.setNext(successive);
		}
		if(current==list.getHead())
		{
			list.setHead(successive);
		}
		if(current==list.getTail())
		{
			list.setTail(previous);
		}
		size--;
	}
	
	public void append(LinkedList<T> list, ListNode<T> toAdd)
	{
		if(list.getHead()==null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else
		{
			list.getTail().setNext(toAdd);
			toAdd.setPrevious(list.getTail());
			list.setTail(toAdd);
		}
		size++;
	}
	
	public void prepend(LinkedList<T> list, ListNode<T> toAdd)
	{
		if(list.getHead()==null)
		{
			list.setHead(toAdd);
			list.setTail(toAdd);
		}
		else
		{
			toAdd.setNext(list.getHead());
			list.getHead().setPrevious(toAdd);
			list.setHead(toAdd);
		}
		size++;
	}
	public static int getSize()
	{
		return size;
	}
	public void printList()
	{
		System.out.println("Current list: ");
		for(ListNode<T> i = this.getHead(); i != null; i = i.getNext())
		{
			System.out.print(i.getValue() + " ");
		}
		System.out.println();
	}
}

abstract class List<T> 
{
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

class ListNode<E>
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

class TreeNode<Comparable> {
	
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



public class BinaryTree<E> {
	
	private TreeNode<E> rootNode;
	private String[] arrayRep;
	
	public BinaryTree()
	{
		rootNode = null;
	}
	
	public BinaryTree(TreeNode<E> node)
	{
		rootNode = node;
	}
	
	public void printInorder(BinaryTree<E> tree, TreeNode<E> root)
	{
		if(root == null)
		{
			return;
		}
		printInorder(tree, root.getLeft());
		System.out.println(root.getValue());
		printInorder(tree, root.getRight());
	}
	
	public boolean isBalanced(BinaryTree<E> tree, TreeNode<E> root)
	{
		int leftHeight;
		int rightHeight;
		if(root == null)
		{
			return true;
		}
		leftHeight = treeHeight(tree, root.getLeft());
		rightHeight = treeHeight(tree, root.getRight());
		
		if(Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(tree, root.getLeft()) && 
				isBalanced(tree, root.getRight()))
		{
			return true;
		}
		return false;
	}
	
	public void insert(BinaryTree<E> tree, TreeNode<E> toInsert)
	{	
		if(tree.getRootNode() == null)
		{
			tree.setRootNode(toInsert);
			toInsert.setLeft(null);
			toInsert.setRight(null);
		}
		else
		{
			TreeNode<E> current = tree.getRootNode();
			while(current != null)
			{
				if(((Comparable) current.getValue()).compareTo( (Comparable) toInsert.getValue()) > 0)
					
				{
					if(current.getLeft() == null)
					{
						current.setLeft(toInsert);
						current = null;
					}
					else
					{
						current = current.getLeft();
					}
				}
				else
				{
					if(current.getRight() == null)
					{
						current.setRight(toInsert);
						current = null;
					}
					else
					{
						current = current.getRight();
					}
				}
			}
			toInsert.setLeft(null);
			toInsert.setRight(null);
		}
	}
	
	public void remove(BinaryTree<E> tree, E toRemove)
	{
		TreeNode<E> parse = null;
		TreeNode<E> current = tree.getRootNode();
		while(current != null)
		{
			if(current.getValue().equals(toRemove))
			{
				if(current.getLeft() == null && current.getRight() == null)
				{
					if(parse == null)
					{
						tree.setRootNode(null);
					}
					else if(parse.getLeft() == current)
					{
						parse.setLeft(null);
					}
					else
					{
						parse.setRight(null);
					}
				}
				else if(current.getLeft() != null && current.getRight() == null)
				{
					if(parse == null)
					{
						tree.setRootNode(current.getLeft());
					}
					else if(parse.getLeft() == current)
					{
						parse.setLeft(current.getLeft());
					}
					else
					{
						parse.setRight(current.getLeft());
					}
				}
				else if(current.getLeft() == null && current.getRight() != null)
				{
					if(parse == null)
					{
						tree.setRootNode(current.getRight());
					}
					else if(parse.getLeft() == current)
					{
						parse.setLeft(current.getRight());
					}
					else
					{
						parse.setRight(current.getRight());
					}
				}
				else
				{
					TreeNode<E> successive = current.getRight();
					while(current.getLeft() != null)
					{
						successive = successive.getLeft();
					}
					E successorData = successive.getValue();
					remove(tree, successive.getValue());
					current.setValue(successorData);
				}
				return;
			}
			else if(((Comparable) current.getValue()).compareTo((Comparable)toRemove) < 0)
			{
				parse = current;
				current = current.getRight();
			}
			else
			{
				parse = current;
				current = current.getLeft();
			}
		}
	}
	
	public TreeNode<E> getRootNode()
	{
		return rootNode;
	}
	
	public void setRootNode(TreeNode<E> node)
	{
		rootNode = node;
	}
	
	public int treeHeight(BinaryTree<E> tree, TreeNode<E> root)
	{
		if(root == null)
		{
			return 0;
		}
		return Math.max(treeHeight(tree, root.getLeft()), treeHeight(tree, root.getRight())) + 1;
	}
	
	public String[] expressAsArray()
	{
		arrayRep = new String[this.treeHeight(this, this.getRootNode())];
		for(int i=0; i<arrayRep.length; i++)
		{
			arrayRep[i] = i + ": ";
		}
		expressAsArrayHelper(0, this.getRootNode());
		return arrayRep;
	}
	
	private void expressAsArrayHelper(int level, TreeNode<E> current)
	{
		if(current == null)
		{
			return;
		}
		arrayRep[level] += ((DecimalNode)(current.getValue())).getValue() + " ";
		expressAsArrayHelper(level + 1, current.getLeft());
		expressAsArrayHelper(level + 1, current.getRight());
	}
}

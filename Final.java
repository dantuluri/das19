--------------------------------------------------------------------------------------------------------
Question #1

//Call getLeaves() to find out how many leaves there are in a BST.
int getLeaves()  
{ 
    return getLeafCount(root); 
} 

int getLeafCount(Node leaf)  
{ 
    if (leaf == null) 
        return 0; 
    if (leaf.left == null && leaf.right == null) 
        return 1; 
    else
        return getLeafCount(leaf.left) + getLeafCount(leaf.right); 
} 
--------------------------------------------------------------------------------------------------------
Question #2

Left-Right rotation (on node 9)
9 would be the root after these AVL rotations
Left rotation at 9 then Right rotation at 9 again
--------------------------------------------------------------------------------------------------------
Question #3
// This algorithms inserts a node into a complete tree and ensures that it stays as
// a complete tree. It traverses through the tree using Breadth First Search.
// By using BFS, this algorithm traverses through each node on every level
// in a balanced way, ensuring that it finds all nodes in the most optimal way (advantage of BFS).
// The queue is a FIFO data structure, so it pops the first inserted node and inserts
// the node in the left child, given there's no left child already in place and then
// the right child, given there's no right child already.
--------------------------------------------------------------------------------------------------------
Question #4
// A balanced tree wouldn't even work. Suppose there are two children for a parent and both children had
// children of their own. Also suppose that we're encoding alphabetical values to binary (for a visual
// representation of what I'm explaining see QueueTreeEncryption.pdf Pg.22). If we were to have the
// aforementioned structure (a parent, two children then two children of those two children), then 
// a certain embedding would have two possible values when decrypted. As a result a strictly unbalanced
// tree would only work.
--------------------------------------------------------------------------------------------------------
Question #5
// There are 18 lines in the AVLTreeRebalance. Here's the analysis of each one:
// The code below has a Big-O of O(log(n)). Given a number of nodes, rebalancing a tree
// would initially have a maximum of 2 possibilities. Either you rotate right, and if not, rotate left, then
// the second possibility would be to return the node. Since each time you rebalance the tree
// you half out the possibilities, you can at the worst case scenario get a time complexity of
// O(log(n)).

AVLTreeRebalance(tree, node) {
   AVLTreeUpdateHeight(node)        
   if (AVLTreeGetBalance(node) == -2) {
      if (AVLTreeGetBalance(node->right) == 1) {
         // Double rotation case.
         AVLTreeRotateRight(tree, node->right)
      }
      return AVLTreeRotateLeft(tree, node)
   }
   else if (AVLTreeGetBalance(node) == 2) {
      if (AVLTreeGetBalance(node->left) == -1) {
         // Double rotation case.
         AVLTreeRotateLeft(tree, node->left)
      }
      return AVLTreeRotateRight(tree, node)
   }        
   return node
}


// For the Red Black tree balancing method, the time complexity is O(nlog(n))
// since you can call the method back (recursion) while also reducing the 
// space in which you can balance the tree. Since you can both rotate left
// and rotate right, you get a complexity of O(log(n)). As explained before
// the reason it's O(nlog(n)) is because there's the possibility of calling
// itself, making it O(nlog(n))

RBTreeBalance(tree, node) {
  if (node->parent == null) {
     node->color = black
     return
  }
  if (node->parent->color == black)
     return
  parent = node->parent
  grandparent = RBTreeGetGrandparent(node)
  uncle = RBTreeGetUncle(node)
  if (uncle != null && uncle->color == red) {
     parent->color = uncle->color = black
     grandparent->color = red
     RBTreeBalance(tree, grandparent)
     return
  }
  if (node == parent->right &&
      parent == grandparent->left) {
     RBTreeRotateLeft(tree, parent)
     node = parent
     parent = node->parent
  }
  else if (node == parent->left &&
           parent == grandparent->right) {
     RBTreeRotateRight(tree, parent)
     node = parent
     parent = node->parent
  }
  parent->color = black
  grandparent->color = red
  if (node == parent->left)
     RBTreeRotateRight(tree, grandparent)
  else
     RBTreeRotateLeft(tree, grandparent)
}
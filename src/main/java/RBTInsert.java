/*
  class TreeNode {
    
    int value;
    TreeNode left, right, parent;
    boolean isRed = true;
    
    public TreeNode(int value) {
      this.value = value;
    }
  }
*/
public class RBTInsert {
  public TreeNode add(TreeNode tree, int el) {
    TreeNode root = addRecursion(tree, el);

    if (root.isRed) {
      root.isRed = false;
    }

    return root;
  }
  public TreeNode addRecursion(TreeNode tree, int el) {
    if(tree == null) {
      return new TreeNode(el);
    }
    if (tree.value > el) {
      tree.left = addRecursion(tree.left, el);
      tree.left.parent = tree;
    } else {
      tree.right = addRecursion(tree.right, el);
      tree.right.parent = tree;
      if(tree.left != null && tree.left.isRed) {
        tree.isRed = true;
        tree.left.isRed = false;
        tree.right.isRed = false;
      } else {
        tree.right.left = tree;
        tree = tree.right;
        tree.right = null;
        TreeNode oldParent = tree.parent;
        tree.parent = tree.left.parent;
        tree.left.parent = oldParent;
      }
    }



    return tree;
  }
}
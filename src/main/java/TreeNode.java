import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {

  int value;
  TreeNode left, right, parent;
  boolean isRed = true;

  public TreeNode(int value) {
    this.value = value;
  }

  public static TreeNode construct(Integer[][] data) {
    TreeNode root = null;
    List<TreeNode> level = new ArrayList<>();

    for (int i = 0; i < data.length; i++) {
      List<TreeNode> nextLevel = new ArrayList<>();

      for (int j = 0; j < data[i].length; j++) {
        Integer value = data[i][j];

        if (value != null) {
          TreeNode node = new TreeNode(value);
          nextLevel.add(node);

          if (root == null) {
            root = node;
          } else {
            TreeNode parent = level.get(j / 2);
            node.parent = parent;
            if (j % 2 == 0) {
              parent.left = node;
            } else {
              parent.right = node;
            }
          }
        }
      }
      level = nextLevel;
    }
    return root;
  }

  public static String printRBTree(TreeNode root) {
    StringBuilder res = new StringBuilder();

    List<TreeNode> level = new ArrayList<>();
    level.add(root);

    while (!level.isEmpty()) {
      List<TreeNode> nextLevel = new ArrayList<>();
      res.append("[ ");

      for (TreeNode node : level) {
        if (node != null) {
          if (node.isRed) {
            res.append('{')
                .append(node.value)
                .append('}');
          } else {
            res.append(node.value);
          }
          res.append(' ');
          nextLevel.add(node.left);
          nextLevel.add(node.right);
        } else {
          res.append("# ");
        }
      }
      level = nextLevel;
      res.append(']');
    }

    return res.toString();
  }

  public static String print(TreeNode root) {
    StringBuilder res = new StringBuilder();

    List<TreeNode> level = new ArrayList<>();
    level.add(root);

    while (!level.isEmpty()) {
      List<TreeNode> nextLevel = new ArrayList<>();
      res.append("[ ");

      for (TreeNode node : level) {
        if (node != null) {
          res.append(node.value).append(' ');
          nextLevel.add(node.left);
          nextLevel.add(node.right);
        } else {
          res.append("# ");
        }
      }
      level = nextLevel;
      res.append(']');
    }

    return res.toString();
  }

  public static boolean isRBTree(TreeNode root) throws Exception {
    // 1. root property
    if (root != null && root.isRed) {
      throw new Exception("Root should be black.");
    }
    Integer blackCount = null;
    List<TreeNode> level = new ArrayList<>();
    level.add(root);
    Map<TreeNode, Integer> nodes = new HashMap<>();

    while (!level.isEmpty()) {
      List<TreeNode> nextLevel = new ArrayList<>();

      for (TreeNode node : level) {
        // 3. children of red should be black
        if (node.isRed) {

          if (node.left != null && node.left.isRed) {
            String message = String.format(
                "Two consecutive nodes colored red: %d and %d",
                node.value, node.left.value);
            throw new Exception(message);
          }
          if (node.right != null && node.right.isRed) {
            String message = String.format(
                "Two consecutive nodes colored red: %d and %d",
                node.value, node.right.value);
            throw new Exception(message);
          }
        }

        if (node.left != null) {
          nextLevel.add(node.left);
        }

        if (node.right != null) {
          nextLevel.add(node.right);
        }

        if (node.parent != null) {
          Integer count = nodes.get(node.parent);
          if (count == null) {
            count = 0;
          }
          if (!node.isRed) {
            count++;
          }
          nodes.put(node, count);
        }

        if (node.left == null && node.right == null) {

          if (blackCount == null) {
            blackCount = nodes.get(node);
          } else {
            Integer count = nodes.get(node);
            if (count != blackCount) {
              throw new Exception(
                  "Two paths with different number of black nodes.");
            }
          }
        }
      }
      level = nextLevel;
    }

    return true;
  }

  public static boolean contains(TreeNode tree, int[] input) {
    for (int num : input) {
      if (!contains(tree, num)) return false;
    }
    return true;
  }

  public static boolean contains(TreeNode tree, int val) {
    while (tree != null) {
      if (tree.value == val) {
        return true;
      } else if (val < tree.value) {
        tree = tree.left;
      } else {
        tree = tree.right;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("{%d}", value);
  }
}
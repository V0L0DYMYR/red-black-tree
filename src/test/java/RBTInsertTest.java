import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RBTInsertTest extends AbstractTest {

  int[] input;
  Runnable task = new Runnable() {
    @Override
    public void run() {
      RBTInsert rbt = new RBTInsert();
      TreeNode tree = null;
      for (int i = 0; i < input.length; i++) {
        tree = rbt.add(tree, input[i]);
      }

      if (TreeNode.contains(tree, input)) {
        try {
          tree.isRBTree(tree);
        } catch (Exception e) {
          Common.assertEquals(error(e.getMessage()), true, false);
        }
      } else {
        String error = error(String.format("Tree %s does not contains all %s",
            TreeNode.printRBTree(tree), Common.printArray(input)));
        Common.assertEquals(error, true, false);
      }
    }
  };

  public RBTInsertTest(int[] input) {
    super("RBTInsert");
    this.input = input;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {new int[]{1}},
        {new int[]{1, 2}},
        {new int[]{3, 2, 1}},
        {new int[]{1, 3, 2}},
        {new int[]{1, 2, 3, 4, 5, 6, 7, 8}},
        {new int[]{8, 7, 6, 5, 4, 3, 2, 1}},
        {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}},
    });
  }

  @Override
  protected Runnable getTask() {
    return task;
  }

  @Override
  protected String lastInput() {
    return new StringBuilder()
        .append("Input: tree.add(")
        .append(Common.printArray(input))
        .append(')')
        .toString();
  }
}
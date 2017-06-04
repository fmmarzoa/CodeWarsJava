import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fmmarzoa on 6/4/17.
 */
public class LongestSlideDown {
    private static class TreeNode {
        int data = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("%d", data);
        }

        public String drawSubTree(int indent) {
            StringBuilder rtn = new StringBuilder(String.join("", Collections.nCopies(indent, "-")));
            rtn.append("+");
            rtn.append(data);
            rtn.append("\n");
            if (left != null)
                rtn.append(left.drawSubTree(indent+1));
            if (right != null)
                rtn.append(right.drawSubTree(indent+1));
            return rtn.toString();
        }

        public void genTotals(int parentSum, List<Integer> totals) {
            if ((left == null) && (right == null)) { // leaf!
                totals.add(parentSum + data);
            } else {
                if (left != null) {
                    left.genTotals(parentSum + data, totals);
                }
                if (right != null) {
                    right.genTotals(parentSum + data, totals);
                }
            }
        }

        public String drawSubTree() {
            return drawSubTree(0);
        }

    }

    private static void preorder(TreeNode root) {
        if (root != null) {
            //Visit the node-Printing the node data
            System.out.printf("%d ", root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static int longestSlideDown(int[][] pyramid) {
        // TODO create the fucking tree based on the pyramid
        List<TreeNode> previousRowNodes = new ArrayList<>();
        TreeNode topNode = new TreeNode(pyramid[0][0]);
        previousRowNodes.add(topNode);
        for (int i = 1; i < pyramid.length; i++) {
            List<TreeNode> thisRowNodes = new ArrayList<>();
            for (int j = 0; j < pyramid[i].length; j++) {
                TreeNode currentNode = new TreeNode(pyramid[i][j]);
//                System.out.println("Current node: " + currentNode);
                thisRowNodes.add(currentNode);
                if (j < previousRowNodes.size()) {
                    TreeNode parentNodeLeft = previousRowNodes.get(j);
//                    System.out.println("\tleft node of:  " + parentNodeLeft);
                    parentNodeLeft.left = currentNode;
                }
                if (j > 0) {
                    TreeNode parentNodeRight = previousRowNodes.get(j - 1);
//                    System.out.println("\tright node of: " + parentNodeRight);
                    parentNodeRight.right = currentNode;
                }
            }
            previousRowNodes = thisRowNodes;
        }
        List<Integer> totals = new ArrayList<>();
        topNode.genTotals(0, totals);
        totals.sort(Comparator.naturalOrder());
        System.out.println(totals);
        //System.out.println(topNode.drawSubTree());
        //preorder(topNode);
        // TODO traverse the fucking tree to get all paths' sums
        // TODO return the highest of those sums
        return -1;
    }
}

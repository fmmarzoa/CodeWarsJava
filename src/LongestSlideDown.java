import java.util.ArrayList;
import java.util.List;

public class LongestSlideDown {
    private static class TreeNode {
        int data = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("%d", data);
        }

        void genTotals(int parentSum, List<Integer> totals) {
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

/*        int findHighest() {
            List<Integer> totals = new ArrayList<>();
            genTotals(0, totals);
            totals.sort(Comparator.naturalOrder());
            return totals.get(totals.size() - 1);
        }*/

        void findHighest(int parentSum, int[] highestPath) {
            if ((left == null) && (right == null)) { // leaf!
                int leafTotal = parentSum + data;
                System.out.format("leaf total: %d\n", leafTotal);
                if (leafTotal > highestPath[0]) {
                    highestPath[0] = leafTotal;
                    System.out.format("****************** NEW HIGHEST TOTAL: %d\n",highestPath[0]);
                }
            } else {
                if (left != null) {
                    left.findHighest(parentSum + data, highestPath);
                }
                if (right != null) {
                    right.findHighest(parentSum + data, highestPath);
                }
            }
        }
    }

    public static int longestSlideDown(int[][] pyramid) {
        List<TreeNode> previousRowNodes = new ArrayList<>();
        TreeNode topNode = new TreeNode(pyramid[0][0]);
        previousRowNodes.add(topNode);
        for (int i = 1; i < pyramid.length; i++) {
            List<TreeNode> thisRowNodes = new ArrayList<>();
            for (int j = 0; j < pyramid[i].length; j++) {
                TreeNode currentNode = new TreeNode(pyramid[i][j]);
                thisRowNodes.add(currentNode);
                if (j < previousRowNodes.size()) {
                    TreeNode parentNodeLeft = previousRowNodes.get(j);
                    parentNodeLeft.left = currentNode;
                }
                if (j > 0) {
                    TreeNode parentNodeRight = previousRowNodes.get(j - 1);
                    parentNodeRight.right = currentNode;
                }
            }
            previousRowNodes = thisRowNodes;
        }
        int[] highestPath = new int[1];
        topNode.findHighest(0, highestPath);
        return highestPath[0];
    }
}

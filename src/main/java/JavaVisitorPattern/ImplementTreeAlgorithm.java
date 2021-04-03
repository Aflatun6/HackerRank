package JavaVisitorPattern;

import JavaVisitorPattern.tree.Color;
import JavaVisitorPattern.tree.Tree;
import JavaVisitorPattern.tree.TreeNode;

public class ImplementTreeAlgorithm {

    Tree makeTree(TreeNode current, int depth) {
        if (current.hashCode() == 1 /*IF HAS CHILDDEN*/) {
            TreeNode treeNode = new TreeNode(5, Color.GREEN, depth);
            current.addChild(treeNode);
            return makeTree(treeNode, ++depth);
        }
        return current;
    }

}

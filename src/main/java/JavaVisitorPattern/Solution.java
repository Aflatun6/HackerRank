package JavaVisitorPattern;

import JavaVisitorPattern.tree.Color;
import JavaVisitorPattern.tree.Tree;
import JavaVisitorPattern.tree.TreeNode;
import JavaVisitorPattern.treevis.FancyVisitor;
import JavaVisitorPattern.treevis.ProductOfRedNodesVisitor;
import JavaVisitorPattern.treevis.SumInLeavesVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {


//		Sample Input
//
//			5
//			4 7 2 5 12
//			0 1 0 0 1
//			1 2
//			1 3
//			3 4
//			3 5

//		Sample Output
//
//			24
//			40
//			15

    @Data
    @AllArgsConstructor
    private static class Input {
        List<Integer> values;
        List<Integer> colors;
        List<List<Integer>> edges;
    }

    public static Tree solve() {
        Input inputs = getInput();
        return createTree(inputs);
    }

    private static Tree createTree(Input inputs) {
        int depth = 0;
        Set<Integer> leaves = getLeaves(inputs.getEdges());
        Set<Integer> parents = getParents(inputs.getEdges());
        for (int i = 1; i <= inputs.getValues().size(); i++) {
            Tree tree = new TreeNode(inputs.getValues().get(0), getColor(inputs.getColors().get(0)), calculateDepth(depth));
            if(parents.contains(i)){
                List<Integer> specificLeaves = getSpecificLeaves(i, inputs.getEdges());
            }
        }
        return null;
    }

    private static List<Integer> getSpecificLeaves(int i, List<List<Integer>> edges) {
        return edges.stream().filter(edge -> edge.get(0) == i).map(edge -> edge.get(1)).collect(Collectors.toList());
    }


    private static int calculateDepth(int depth) {
        // SHOULD BE IMPLEMENTED
        return depth;
    }

    private static Set<Integer> getParents(List<List<Integer>> edges) {
        return edges.stream().map(edge -> edge.get(0)).collect(Collectors.toSet());
    }

    private static Set<Integer> getLeaves(List<List<Integer>> edges) {
        Set<Integer> parents = getParents(edges);
        Set<Integer> children = edges.stream().map(edge -> edge.get(1)).collect(Collectors.toSet());
        children.removeAll(parents);
        return children;
    }

    private static Color getColor(int color) {
        return color == 1 ? Color.RED : Color.GREEN;
    }

    private static Input getInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfNodes = Integer.parseInt(br.readLine());
            List<Integer> values = toList(br.readLine());
            List<Integer> colors = toList(br.readLine());
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < (numberOfNodes - 1); i++) {
                List<Integer> edge = toList(br.readLine());
                edges.add(edge);
            }
            return new Input(values, colors, edges);
        } catch (Exception e) {
            throw new RuntimeException("Something happened with Reader");
        }
    }

    private static List<Integer> toList(String readLine) {
        return Arrays.stream(readLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
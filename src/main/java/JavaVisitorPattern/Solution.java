package JavaVisitorPattern;

import JavaVisitorPattern.tree.Tree;
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
        List<Integer> colours;
        List<List<Integer>> edges;
    }

    public static Tree solve() {
        Input inputs = getInput();

        return null;
    }

    private static Input getInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfNodes = Integer.parseInt(br.readLine());
            List<Integer> values = toList(br.readLine());
            List<Integer> colours = toList(br.readLine());
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < numberOfNodes; i++) {
                List<Integer> edge = toList(br.readLine());
                edges.add(edge);
            }
            return new Input(values, colours, edges);
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
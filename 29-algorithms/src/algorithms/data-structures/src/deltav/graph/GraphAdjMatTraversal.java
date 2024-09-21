package deltav.graph;

import java.util.*;

public class GraphAdjMatTraversal {
    public static List<Integer> graphBfsForAdjMat(GraphAdjMat adjMat, int startIndex) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startIndex);
        visited.add(startIndex);

        while (!queue.isEmpty()) {
            Integer vetIndex = queue.poll();
            res.add(vetIndex);

            List<Integer> vetAdjList = adjMat.getAdjMat().get(vetIndex);
            for (int adjVetIndex = 0; adjVetIndex < vetAdjList.size(); adjVetIndex++) {
                if (vetAdjList.get(adjVetIndex) == 1) {
                    if (visited.contains(adjVetIndex))
                        continue;
                    queue.offer(adjVetIndex);
                    visited.add(adjVetIndex);
                }
            }
        }

        return res;
    }

    public static List<Integer> graphDfsForAdjMat(GraphAdjMat graphAdjMat, int startIndex) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        graphDfs(graphAdjMat, startIndex, visited, res);
        return res;
    }

    private static void graphDfs(GraphAdjMat graphAdjMat, int startIndex, Set<Integer> visited, List<Integer> res) {
        res.add(startIndex);
        visited.add(startIndex);
        List<Integer> row = graphAdjMat.getAdjMat().get(startIndex);
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) == 1 && !visited.contains(i)) {
                graphDfs(graphAdjMat, i, visited, res);
            }
        }
    }

    public static void main(String[] args) {
        int[] vertices = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] edges = new int[][]{
                {0, 1}, {0, 3},
                {1, 4}, {1, 2},
                {2, 5},
                {3, 4}, {3, 6},
                {4, 5}, {4, 7},
                {5, 8},
                {6, 7},
                {7, 8}
        };
        GraphAdjMat graphAdjMat = new GraphAdjMat(vertices, edges);
        System.out.println("\n初始化后，图为");
        graphAdjMat.print();

        List<Integer> res = GraphAdjMatTraversal.graphBfsForAdjMat(graphAdjMat, 0);
        System.out.println(res);

        vertices = new int[]{0, 1, 2, 3, 4, 5, 6};
        edges = new int[][]{
                {0, 3}, {0, 1},
                {1, 2},
                {2, 5},
                {5, 4}, {5, 6}
        };

        graphAdjMat = new GraphAdjMat(vertices, edges);
        System.out.println("\n初始化后，图为");
        graphAdjMat.print();
        res = GraphAdjMatTraversal.graphDfsForAdjMat(graphAdjMat, 0);
        System.out.println(res);
    }
}

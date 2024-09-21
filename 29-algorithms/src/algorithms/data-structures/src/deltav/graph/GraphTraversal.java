package deltav.graph;

import deltav.utils.Vertex;

import java.util.*;

public class GraphTraversal {
    public static List<Vertex> graphBfsForAdjList(GraphAdjList adjList, Vertex startVertex) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            res.add(vertex);

            for (Vertex adjVet : adjList.getAdjList().get(vertex)) {
                if (visited.contains(adjVet))
                    continue;
                queue.offer(adjVet);
                visited.add(adjVet);
            }
        }

        return res;
    }

    public static List<Vertex> graphDfsForAdjList(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        List<Vertex> visited = new ArrayList<>();

        dfsForAdjList(graph, startVet, visited, res);
        return res;
    }

    private static void dfsForAdjList(GraphAdjList graph, Vertex startVet, List<Vertex> visited, List<Vertex> res) {
        res.add(startVet);
        visited.add(startVet);

        Set<Vertex> vertices = graph.getAdjList().get(startVet);
        for (Vertex vertex : vertices) {
            if (visited.contains(vertex))
                continue;
            dfsForAdjList(graph, vertex, visited, res);
        }
    }

    public static void main(String[] args) {
        Vertex[] v = Vertex.valsToVets(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        Vertex[][] edges = {
                {v[0], v[1]}, {v[0], v[3]},
                {v[1], v[0]}, {v[1], v[2]}, {v[1], v[4]},
                {v[2], v[1]}, {v[2], v[5]},

                {v[3], v[0]}, {v[3], v[4]}, {v[3], v[6]},
                {v[4], v[1]}, {v[4], v[3]}, {v[4], v[5]},
                {v[5], v[2]}, {v[5], v[4]}, {v[5], v[8]},

                {v[6], v[3]}, {v[6], v[7]},
                {v[7], v[6]}, {v[7], v[4]}, {v[7], v[8]},
                {v[8], v[7]}, {v[8], v[5]}
        };
        GraphAdjList adjList = new GraphAdjList(edges);
        System.out.println("\n初始化后，图为");
        adjList.print();

        List<Vertex> vertices = GraphTraversal.graphBfsForAdjList(adjList, v[0]);
        System.out.println("vertices = " + vertices);

        v = Vertex.valsToVets(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        edges = new Vertex[][]{
                {v[0], v[3]}, {v[0], v[1]},
                {v[1], v[2]},
                {v[2], v[5]},
                {v[5], v[4]}, {v[5], v[6]},
                {v[3], v[0]}
        };

        adjList = new GraphAdjList(edges);

        vertices = GraphTraversal.graphDfsForAdjList(adjList, v[0]);
        System.out.println("vertices = " + vertices);
    }
}

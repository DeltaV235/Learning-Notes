package deltav.graph;

import deltav.utils.Vertex;

import java.util.*;

public class GraphAdjList {
    private final Map<Vertex, Set<Vertex>> adjList;

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    private void addEdge(Vertex vertex1, Vertex vertex2) {
        if (!adjList.containsKey(vertex1) || !adjList.containsKey(vertex2) || vertex1 == vertex2)
            throw new IllegalArgumentException();
        this.adjList.get(vertex1).add(vertex2);
        this.adjList.get(vertex2).add(vertex1);
    }

    private void removeEdge(Vertex vertex1, Vertex vertex2) {
        if (!adjList.containsKey(vertex1) || !adjList.containsKey(vertex2) || vertex1 == vertex2)
            throw new IllegalArgumentException();
        this.adjList.get(vertex1).remove(vertex2);
        this.adjList.get(vertex2).remove(vertex1);
    }


    public int size() {
        return adjList.size();
    }

    private void addVertex(Vertex vertex) {
        if (this.adjList.containsKey(vertex)) return;
        this.adjList.put(vertex, new HashSet<>());
    }

    private void removeVertex(Vertex vertex) {
        if (!this.adjList.containsKey(vertex)) throw new IllegalArgumentException();
        this.adjList.remove(vertex);
        for (Set<Vertex> set : this.adjList.values()) {
            set.remove(vertex);
        }
    }

    public void print() {
        System.out.println("邻接表 =");
        for (Map.Entry<Vertex, Set<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue())
                tmp.add(vertex.val);
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }

    public static void main(String[] args) {
        /* 初始化无向图 */
        Vertex[] v = Vertex.valsToVets(new int[]{1, 3, 2, 5, 4});
        Vertex[][] edges = {{v[0], v[1]}, {v[0], v[3]}, {v[1], v[2]}, {v[2], v[3]}, {v[2], v[4]}, {v[3], v[4]}};
        GraphAdjList graph = new GraphAdjList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 添加边 */
        // 顶点 1, 2 即 v[0], v[2]
        graph.addEdge(v[0], v[2]);
        System.out.println("\n添加边 1-2 后，图为");
        graph.print();

        /* 删除边 */
        // 顶点 1, 3 即 v[0], v[1]
        graph.removeEdge(v[0], v[1]);
        System.out.println("\n删除边 1-3 后，图为");
        graph.print();

        /* 添加顶点 */
        Vertex v5 = new Vertex(6);
        graph.addVertex(v5);
        System.out.println("\n添加顶点 6 后，图为");
        graph.print();

        /* 删除顶点 */
        // 顶点 3 即 v[1]
        graph.removeVertex(v[1]);
        System.out.println("\n删除顶点 3 后，图为");
        graph.print();
    }

}

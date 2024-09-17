package deltav.graph;

import deltav.utils.PrintUtil;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjMat {
    private final List<Integer> vertices;
    private final List<List<Integer>> adjMat;

    public GraphAdjMat(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>(vertices.length);
        this.adjMat = new ArrayList<>(vertices.length);

        for (int vertex : vertices) {
            this.addVertex(vertex);
        }

        for (int[] edge : edges) {
            this.addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex(int val) {
        int size = this.size();
        vertices.add(val);
        List<Integer> newRow = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            newRow.add(0);
        }

        this.adjMat.add(newRow);

        for (List<Integer> vertex : adjMat) {
            vertex.add(0);
        }
    }

    public void removeVertex(int index) {
        if (index > this.size())
            throw new IndexOutOfBoundsException();
        this.vertices.remove(index);
        this.adjMat.remove(index);

        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
    }

    public void addEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();

        this.adjMat.get(i).set(j, 1);
        this.adjMat.get(j).set(i, 1);
    }

    public void removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();

        this.adjMat.get(i).set(j, 0);
        this.adjMat.get(j).set(i, 0);
    }

    public void print() {
        System.out.print("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 =");
        PrintUtil.printMatrix(adjMat);
    }

    public static void main(String[] args) {
        /* 初始化无向图 */
        // 请注意，edges 元素代表顶点索引，即对应 vertices 元素索引
        int[] vertices = {1, 3, 2, 5, 4};
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}, {3, 4}};
        GraphAdjMat graph = new GraphAdjMat(vertices, edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 添加边 */
        // 顶点 1, 2 的索引分别为 0, 2
        graph.addEdge(0, 2);
        System.out.println("\n添加边 1-2 后，图为");
        graph.print();

        /* 删除边 */
        // 顶点 1, 3 的索引分别为 0, 1
        graph.removeEdge(0, 1);
        System.out.println("\n删除边 1-3 后，图为");
        graph.print();

        /* 添加顶点 */
        graph.addVertex(6);
        System.out.println("\n添加顶点 6 后，图为");
        graph.print();

        /* 删除顶点 */
        // 顶点 3 的索引为 1
        graph.removeVertex(1);
        System.out.println("\n删除顶点 3 后，图为");
        graph.print();
    }
}

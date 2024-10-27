package medium;

public class FindRedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] uf = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            uf[i] = i;
        }
        for (int[] edge : edges) {
            int nodeX = edge[0];
            int nodeY = edge[1];
            if (find(uf, nodeX) != find(uf, nodeY))
                union(uf, nodeX, nodeY);
            else
                return edge;
        }
        return new int[0];
    }

    void union(int[] uf, int nodeX, int nodeY) {
        uf[find(uf, nodeY)] = find(uf, nodeX);
    }

    int find(int[] uf, int node) {
        if (uf[node] != node) {
            uf[node] = find(uf, uf[node]);
        }
        return uf[node];
    }

    public static void main(String[] args) {
        FindRedundantConnection findRedundantConnection = new FindRedundantConnection();
        // [[3,4],[1,2],[2,4],[3,5],[2,5]]
        int[][] edges = {{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}};
        int[] res = findRedundantConnection.findRedundantConnection(edges);
        for (int i : res) {
            System.out.println(i);
        }
    }
}

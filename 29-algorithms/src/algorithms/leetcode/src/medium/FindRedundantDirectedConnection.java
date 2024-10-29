package medium;

public class FindRedundantDirectedConnection {
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
}

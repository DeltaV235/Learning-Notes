package medium;

/**
 * <a href="https://leetcode.cn/problems/node-with-highest-edge-score/description">leetcode</a>
 */
public class NodeWithHighestEdgeScore {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] edgeScore = new long[n];
        for(int i = 0; i < n; i++) {
            edgeScore[edges[i]] += i;
        }

        long maxEdgeScore = 0;
        int res = 0;
        for(int i = 0; i < n; i++) {
            if (edgeScore[i] > maxEdgeScore) {
                maxEdgeScore = edgeScore[i];
                res = i;
            }
        }

        return res;
    }

}

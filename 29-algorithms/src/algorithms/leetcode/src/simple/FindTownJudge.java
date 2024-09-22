package simple;

/**
 * <a href="https://leetcode.cn/problems/find-the-town-judge/description/">leetcode</a>
 * <p>
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * <p>
 * 如果小镇法官真的存在，那么：
 * <p>
 * 1. 小镇法官不会信任任何人。<br/>
 * 2. 每个人（除了小镇法官）都信任这位小镇法官。<br/>
 * 3. 只有一个人同时满足属性 1 和属性 2 。<br/>
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * <p>
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 */
public class FindTownJudge {
    public int findJudge(int n, int[][] trust) {
        if (n <= 1)
            return 1;
        int[] trustCount = new int[n + 1];

        for (int i = 0; i < trust.length; ++i) {
            --trustCount[trust[i][0]];
            ++trustCount[trust[i][1]];
        }

        // 如果存在一个入度为 n - 1 的节点，那么意味着其余节点的出度至少为 1。也就是说存在满足条件 1、2 的节点的情况下，条件 3 必定满足。
        // 所以在此找到存在出度为 n - 1 的节点时可以直接返回其编号，而无需考虑存在别的满足条件 1、2 的节点。
        for (int i = 1; i < n + 1; ++i) {
            if (trustCount[i] == n - 1)
                return i;
        }
        return -1;
    }
}

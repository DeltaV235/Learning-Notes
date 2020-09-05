package domain;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className QueryVo
 * @description
 * @date 2020/2/29 15:41
 */
public class QueryVo {
    private List<Integer> ids;
    private User user;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

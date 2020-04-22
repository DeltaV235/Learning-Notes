import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JedisTest
 * @description
 * @date 2020/4/21 23:52
 */
public class JedisTest {
    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.2.200", 6379);
        String k1 = jedis.get("k1");
        System.out.println(k1);
    }
}

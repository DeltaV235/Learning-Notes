import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void test02() {
        Set<HostAndPort> nodes = new HashSet<>();
        String clusterHosts = "192.168.2.200:6379;192.168.2.200:6380;192.168.2.201:6379;192.168.2.201:6380;" +
                "192.168.2.202:6379;192.168.2.202:6380";
        Set<String> hosts = new HashSet<>(Arrays.asList(clusterHosts.split(";")));
        for (String host : hosts) {
            String[] split = host.split(":");
            nodes.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        GenericObjectPoolConfig pools = new GenericObjectPoolConfig();
        pools.setMaxTotal(1000);
        pools.setMaxIdle(800);
        JedisCluster cluster = new JedisCluster(nodes, pools);
        cluster.set("k10", "v10");
        String k1 = cluster.get("k10");
        System.out.println("k10 = " + k1);
    }
}

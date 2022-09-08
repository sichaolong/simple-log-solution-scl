package henu.soft.example.kafka.java;

import henu.soft.example.client.BaseCanalClientTest;

/**
 * @author sichaolong
 * @date 2022/9/7 15:07
 * 配置kafka相关
 */
public abstract class AbstractKafkaTest extends BaseCanalClientTest {

    public static String topic = "example";
    public static Integer partition = null;
    public static String groupId = "test-consumer-group";
    public static String servers = "127.0.0.1:9092";
    public static String zkServers = "127.0.0.1:2181";

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}

package henu.soft.example.kafka.java;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.alibaba.otter.canal.client.kafka.KafkaCanalConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.otter.canal.protocol.Message;
/**
 * @author sichaolong
 * @date 2022/9/7 15:16
 *
 * 测试消费kafka消息，只要监听到canal向kafka发送消息，消费kafka的消息
 * 注意：积压的消息不能被消费！！！
 */


/**
 * 测试失败，kefka编解码错误，消费不了数据
 */
public class KafkaClientRunningTest extends AbstractKafkaTest {

    private Logger  logger  = LoggerFactory.getLogger(KafkaClientRunningTest.class);

    private boolean running = true;

    public static void main(String[] args) {

        new KafkaClientRunningTest().testKafkaConsumer();
    }


    public void testKafkaConsumer() {
        final ExecutorService executor = Executors.newFixedThreadPool(1);
        final KafkaCanalConnector connector = new KafkaCanalConnector(servers, topic, partition, groupId, null, false);
        executor.submit(() -> {
            connector.connect();
            connector.subscribe();
            while (running) {
                List<Message> messages = connector.getList(3L, TimeUnit.SECONDS);
                if (messages != null) {
                    System.out.println("消费======================>");
                    System.out.println(messages.size());
                    for (Message message : messages) {
                        System.out.println(message.toString());
                    }
                }
                connector.ack();
            }
            connector.unsubscribe();
            connector.disconnect();
        });

        sleep(600000);
        running = false;
        executor.shutdown();
        logger.info("shutdown completed");
    }

}

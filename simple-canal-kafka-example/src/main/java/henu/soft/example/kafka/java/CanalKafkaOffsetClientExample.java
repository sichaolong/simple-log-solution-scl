package henu.soft.example.kafka.java;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.otter.canal.client.kafka.KafkaOffsetCanalConnector;
import com.alibaba.otter.canal.client.kafka.protocol.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author sichaolong
 * @date 2022/9/7 15:18
 */
public class CanalKafkaOffsetClientExample {

    protected final static Logger logger = LoggerFactory.getLogger(CanalKafkaOffsetClientExample.class);

    private KafkaOffsetCanalConnector connector;

    private static volatile boolean running = false;

    private Thread thread = null;

    private Thread.UncaughtExceptionHandler handler = (t, e) -> logger.error("parse events has an error", e);

    public CanalKafkaOffsetClientExample(String servers, String topic, Integer partition, String groupId) {
        connector = new KafkaOffsetCanalConnector(servers, topic, partition, groupId, false);
    }

    public static void main(String[] args) {
        try {
            final CanalKafkaOffsetClientExample kafkaCanalClientExample = new CanalKafkaOffsetClientExample(AbstractKafkaTest.servers,
                    AbstractKafkaTest.topic,
                    AbstractKafkaTest.partition,
                    AbstractKafkaTest.groupId);
            logger.info("## start the kafka consumer: {}-{}", AbstractKafkaTest.topic, AbstractKafkaTest.groupId);
            kafkaCanalClientExample.start();
            logger.info("## the canal kafka consumer is running now ......");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    logger.info("## stop the kafka consumer");
                    kafkaCanalClientExample.stop();
                } catch (Throwable e) {
                    logger.warn("##something goes wrong when stopping kafka consumer:", e);
                } finally {
                    logger.info("## kafka consumer is down.");
                }
            }));
            while (running)
                ;
        } catch (Throwable e) {
            logger.error("## Something goes wrong when starting up the kafka consumer:", e);
            System.exit(0);
        }
    }

    public void start() {
        Assert.notNull(connector, "connector is null");
        thread = new Thread(this::process);
        thread.setUncaughtExceptionHandler(handler);
        thread.start();
        running = true;
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    private void process() {
        while (!running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

        while (running) {
            try {
                // ?????? AutoOffsetReset ??????????????????earliest???
                // connector.setAutoOffsetReset(null);
                connector.connect();
                connector.subscribe();
                // ????????????????????????
                long offset = -1;
                // ????????????
                int errorCount = 0;
                while (running) {
                    try {
                        // ????????????????????????3????????????30???????????????
                        if (errorCount > 2) {
                            Thread.sleep((errorCount - 2) * 1000 * 30);
                        }

                        List<KafkaMessage> messages = connector.getListWithoutAck(100L, TimeUnit.MILLISECONDS, offset); // ??????message
                        if (messages == null) {
                            continue;
                        }
                        for (KafkaMessage message : messages) {
                            long batchId = message.getId();
                            int size = message.getEntries().size();

                            if (batchId == -1 || size == 0) {
                                continue;
                            }

                            // ????????????????????????offset????????????????????????????????????????????????????????????
                            if (offset < 0) {
                                offset = message.getOffset();
                            }

                            // printSummary(message, batchId, size);
                            // printEntry(message.getEntries());
                            logger.info(message.toString());
                        }

                        connector.ack(); // ????????????
                        // ??????offset
                        offset = -1;
                        errorCount = 0;
                    } catch (Exception e) {
                        errorCount++;
                        logger.error(e.getMessage(), e);
                        if (errorCount == 3) {
                            // ??????3??????????????????????????????
                            // mailService.sendMail("????????????????????????????????????", "????????????");
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        connector.unsubscribe();
        connector.disconnect();
    }
}

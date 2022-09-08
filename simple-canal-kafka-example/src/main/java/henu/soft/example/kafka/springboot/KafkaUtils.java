package henu.soft.example.kafka.springboot;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * @author sichaolong
 * @date 2022/9/7 22:17
 */
@Component
public class KafkaUtils {

    @Autowired
    AdminClient adminClient;


    /**
     * 创建topic
     * @param topicName
     * @return
     */
    public String createTopic(String topicName) {
        NewTopic topic = new NewTopic(topicName, 2, (short) 1);
        adminClient.createTopics(Arrays.asList(topic));
        return topicName;

    }
    /**
     * 查询topic
     * @param topicName
     * @return
     */
    public String queryTopic(String topicName) {
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
        StringBuffer sb = new StringBuffer("topic信息:");
        try {
            result.all().get().forEach((k,v)->sb.append("key").append(k).append(";v:").append(v));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 删除topic
     * @param topicName
     * @return
     */
    public String deleteTopic(String topicName) {
        adminClient.deleteTopics(Arrays.asList(topicName));
        return topicName;
    }



}

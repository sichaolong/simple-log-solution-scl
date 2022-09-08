package henu.soft.example.kafka.springboot;

/**
 * @author sichaolong
 * @date 2022/9/7 22:20
 */

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka消息发送者
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    /**
     *  发送文字消息
     * @param message
     * @return
     */
    public String sendStr(String message){
        kafkaTemplate.send(KafkaConfig.TOPIC1,message);
        return message;
    }

    /**
     *  发送对象消息
     * @param obj
     * @return
     */
    public String sendObj(Object obj){
        String message = JSON.toJSONString(obj);
        kafkaTemplate.send(KafkaConfig.TOPIC1,message);
        return message;
    }
}


package henu.soft.example.kafka.springboot;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author sichaolong
 * @date 2022/9/7 22:23
 */

@Component
@Log4j2
public class KafkaConsumerListener {

    @KafkaListener(topics = KafkaConfig.TOPIC1)
    public void onMessage1(String message){
        System.out.println(message);
        log.info("kafka-topic1接收结果:{}",message);
    }

    @KafkaListener(topics = "kafka-topic2")
    public void onMessage2(String message){
        System.out.println(message);
        log.info("kafka-topic2接收结果:{}",message);
    }
}



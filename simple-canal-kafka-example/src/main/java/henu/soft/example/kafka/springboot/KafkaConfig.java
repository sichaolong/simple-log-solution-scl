package henu.soft.example.kafka.springboot;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * @author sichaolong
 * @date 2022/9/7 22:14
 */

@Configuration
@ConditionalOnClass(KafkaAdmin.class)
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {


    public static final String TOPIC1 = "example";
    private final KafkaProperties properties;


    public KafkaConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    /**
     * 初始化对kafka执行操作的对象
     * @return
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        KafkaAdmin admin = new KafkaAdmin(this.properties.buildProducerProperties());
        return admin;
    }

    /**
     * 初始化操作连接
     * @return
     */
    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }
}

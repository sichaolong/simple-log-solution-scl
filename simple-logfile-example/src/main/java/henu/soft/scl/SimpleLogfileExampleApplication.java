package henu.soft.scl;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogRecord(tenant = "henu.soft.scl") // 美团开源基于注解记录日志
public class SimpleLogfileExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLogfileExampleApplication.class, args);
    }

}

package henu.soft.elk.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sichaolong
 * @date 2022/9/13 14:40
 */

@RestController
@Slf4j
public class TestController {



    @RequestMapping("/create")
    public boolean createOrder( ){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            while (true) {
                log.info("springboot整合logstash... this is info level ......{}", df.format(new Date()));
                log.warn("springboot整合logstash... this is warn level ......{}", df.format(new Date()));
                log.error("springboot整合logstash... this is error level ......{}", df.format(new Date()));
                log.debug("springboot整合logstash... this is debug level ......{}", df.format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return true;


    }
}

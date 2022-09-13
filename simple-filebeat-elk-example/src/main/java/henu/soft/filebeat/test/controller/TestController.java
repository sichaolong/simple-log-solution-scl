package henu.soft.filebeat.test.controller;

import henu.soft.filebeat.test.util.TestMDCUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sichaolong
 * @date 2022/9/13 16:58
 */

@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        TestMDCUtil.putMDC();

        log.info("我是一条info日志");

        log.warn("我是一条warn日志");

        log.error("我是一条error日志");

        return "idx";
    }


    @RequestMapping(value = "/err")
    public String err() {
        TestMDCUtil.putMDC();
        try {
            int a = 1/0;
        } catch (Exception e) {
            log.error("算术异常", e);
        }
        return "err";
    }

}

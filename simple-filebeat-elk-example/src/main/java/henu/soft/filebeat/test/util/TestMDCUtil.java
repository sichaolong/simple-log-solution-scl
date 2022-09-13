package henu.soft.filebeat.test.util;

import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author sichaolong
 * @date 2022/9/13 17:00
 */
@Component
public class TestMDCUtil implements EnvironmentAware {
    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        TestMDCUtil.environment = environment;
    }

    public static void putMDC() {
        /**
         * 使用sl4j的MDC
         */
        MDC.put("hostName", NetUtil.getLocalHostName());
        MDC.put("ip", NetUtil.getLocalIp());
        MDC.put("applicationName", environment.getProperty("spring.application.name"));
    }

}

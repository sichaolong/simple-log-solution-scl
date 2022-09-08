package henu.soft.scl.simpleway.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sichaolong
 * @date 2022/9/7 22:36
 */

/**
 * 自定义拦截器模拟登陆获取用户信息，保存到sl4j的MDC中，便于后续记录日志到文件
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取到用户标识
        String userName = getUserName(request);
        //把用户 ID 放到 MDC 上下文中,等会再logback-spring.xml取出设置pattern
        log.info("preHandler执行！");
        System.out.println("////");
        logger.info("......");

        MDC.put("name", userName);
        return true;
    }

    private String getUserName(HttpServletRequest request) {

        // ...
        return "scl";
    }

}

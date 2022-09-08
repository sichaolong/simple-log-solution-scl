package henu.soft.scl.simpleway.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author sichaolong
 * @date 2022/9/7 23:19
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new LoginInterceptor()) // 添加拦截器
                .addPathPatterns("/**") // 添加拦截路径
                .excludePathPatterns(// 添加排除拦截路径
                        "/hello").order(0);//执行顺序
        super.addInterceptors(registry);
    }

}

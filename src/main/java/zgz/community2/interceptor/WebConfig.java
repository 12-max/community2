package zgz.community2.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);

        interceptorRegistration.addPathPatterns("/**"); // 拦截所有

        interceptorRegistration.excludePathPatterns( // 这个括号里面是放行的一些配置
                "/**/*.html", // 放i选哪个所有的.html文件
                "/**/*.css",
                "/**/*.js",
                "/toLogin",
                "/register",
                "/login",
                "/comment"
//                "/test/*"  // 放行前缀为test的接口
        );
    }
}

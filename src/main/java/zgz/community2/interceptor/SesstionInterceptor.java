package zgz.community2.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
class SessionInterceptor implements HandlerInterceptor {
    @Autowired
   private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies !=null&& cookies.length!=0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("user")){
                    String value = cookie.getValue();
                    User user = userMapper.userName(value);
                    if (user!=null){
                        request.getSession().setAttribute("user",user.getUsername());
                    }
                }
            }
        }
        System.out.println("执行方法拦截器");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

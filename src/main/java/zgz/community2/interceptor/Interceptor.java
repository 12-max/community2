package zgz.community2.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import zgz.community2.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("进入HandlerInterceptor的preHandle方法" );

        try {
            User user = (User) request.getSession().getAttribute("user");
            // 判断session中是否存在user  因为我们登入的时候会把user存入session  如果session存在user说明用户已经登入 反
            if(user !=null){
                return true;
            }
            // 如果没有登入则重定向到登入页面
            System.out.println(request.getRequestURL().toString());
            response.sendRedirect(request.getContextPath() + "/register");
            return false;
        }catch (IOException e){
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/toLgoin");
        return  false;
    }
}

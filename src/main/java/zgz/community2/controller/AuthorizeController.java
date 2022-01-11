package zgz.community2.controller;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AuthorizeController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("register")
    public String toRegister() {
        return "register";
    }


    @PostMapping("register")
    public String register(String username,
                           String password,
                           String email,
                           String name,
                           String phone,
                           Integer gender,
                           String birthday) {
        User user = new User();

        User username1 = userMapper.userName(username);


        if (username1.getUsername() != null) {
            System.out.println("用户已存在");
            return "register";
        } else if (username1 == null) {
            System.out.println("注册成功");
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setPhone(phone);
            user.setGender(gender);
            user.setBirthday(birthday);
            userMapper.addUser(user);
            System.out.println(user.toString());

            return "redirect:/";

        }
        return "redirect:/";
    }

    @GetMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {

        try {
            User usernamePassword = userMapper.usernamePassword(username, password);
            if (username.equals(usernamePassword.getUsername()) && password.equals(usernamePassword.getPassword())) {

                request.getSession().setAttribute("user",usernamePassword);
                System.out.println("用户登录成功");
                return "redirect:/";
            }
        }catch(NullPointerException e){
            e.getMessage();
            System.out.println("登录失败");
        }
        return "login";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie user = new Cookie("user", null);
        user.setMaxAge(0);
        response.addCookie(user);
        return "redirect:/";
    }
}
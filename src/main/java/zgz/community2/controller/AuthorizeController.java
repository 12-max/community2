package zgz.community2.controller;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.User;

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


        if (username1 != null) {
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
                        @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User usernamePassword=userMapper.usernamePassword(user.getUsername(), user.getPassword());

        String password1 = usernamePassword.getPassword();
        String username1 = usernamePassword.getUsername();
        if (password1.equals(username)&&username1.equals(password)){
            System.out.println("用户成功");
            return "redirect:/";
        }
            System.out.println("密码or用户名错误");
            return "redirect:/";

    }
}
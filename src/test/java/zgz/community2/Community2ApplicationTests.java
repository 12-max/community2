package zgz.community2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.User;

import java.util.List;

@SpringBootTest
class Community2ApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void add(){
        User usernamePassword = userMapper.usernamePassword("zgz123", "1234");
        System.out.println(usernamePassword.toString());

    }
}

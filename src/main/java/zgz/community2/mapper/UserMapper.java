package zgz.community2.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> SelectUser();

    @Select("select * from user where username=#{username}")
    User userName(String username);

    @Insert("INSERT INTO user (birthday, gender,phone,name,email,password,username) VALUES (#{birthday}, #{gender},#{phone},#{name},#{email},#{password},#{username})")
    void addUser(User user);

    @Select("select username,password from user where username=#{username} and password=#{password}")
    User usernamePassword(@Param("username") String username,
                          @Param("password") String password);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer creator);
}

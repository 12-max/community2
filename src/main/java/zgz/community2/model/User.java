package zgz.community2.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    Integer id;
    String birthday;
    Integer gender;
    String phone;
    String name;
    String email;
    String password;
    String username;
    String toxiang;
}

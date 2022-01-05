package zgz.community2.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import zgz.community2.model.User;

@Data
@ToString
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
    private User user;
}

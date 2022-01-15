package zgz.community2.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Question {
    private Long id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Long creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
}

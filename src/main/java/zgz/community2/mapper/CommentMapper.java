package zgz.community2.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import zgz.community2.model.Comment;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (parent_id, type,commentator,gmt_create,gmt_modified,like_count,content) value(#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{like_count},#{content})")
    int inset(Comment comment);

    @Select("select * from comment where parent_id = #{parent_id}")
    Comment selectByPrimaryKey(Long parent_id);


}

package zgz.community2.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import zgz.community2.model.Comment;


public interface CommentMapper {


    int inset(Comment comment);
}

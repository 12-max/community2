package zgz.community2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgz.community2.dto.ResultDTO;
import zgz.community2.enums.CommentTypeEnum;
import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.exception.CustomizeException;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.model.Comment;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public void inset(Comment comment) {
        if (comment.getParent_id()==null||comment.getParent_id()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParent_id());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUNT);
            }
            commentMapper.inset(comment);
        }else {
            Long parent_id =  comment.getParent_id();
            questionMapper.getById(parent_id);
            //回复问题
        }

        commentMapper.inset(comment);
    }
}

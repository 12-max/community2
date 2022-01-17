package zgz.community2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zgz.community2.dto.CommentCreateDTO;
import zgz.community2.dto.CommentDTO;
import zgz.community2.enums.CommentTypeEnum;
import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.exception.CustomizeException;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.model.Question;
import zgz.community2.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    @Transactional
    public void inset(Comment comment) {
        if (comment.getParent_id()==null||comment.getParent_id()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            List dbComment = commentMapper.selectByPrimaryKey(comment.getParent_id());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUNT);
            }
            commentMapper.inset(comment);
        }else {
            Question question = questionMapper.getById(comment.getParent_id());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.inset(comment);
            //question.setComment_count(1);
            //回复问题
            // questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> Comment(Long id ){
        ArrayList<Comment> comments = commentMapper.selectByPrimaryKey(id);
        ArrayList<CommentDTO> commentDTOArrayList = new ArrayList<>();


        for (Comment comment : comments) {
            User byUser = userMapper.findById(comment.getCommentator());
            commentDTOArrayList.add(new CommentDTO(comment.getId(),comment.getParent_id(),comment.getType(),comment.getCommentator(),comment.getGmt_create()
                    ,comment.getGmt_modified(),comment.getLike_count(),comment.getContent(),byUser));

        }
        //倒序
        Collections.reverse(commentDTOArrayList);
        return commentDTOArrayList;
    }

    public ArrayList<CommentDTO> Comment(){
        List<Comment> comment1 = commentMapper.comment();
        List<Comment> comment2 = commentMapper.comment();
        List<User> users = userMapper.SelectUser();
        ArrayList<CommentDTO> commentDTOArrayList = new ArrayList<>();

        for (Comment comment : comment1) {
            if (comment.getType()==2) {
                for (Comment comment3 : comment2) {
                    if (comment.getParent_id()==comment3.getId()){
                        for (User user : users) {
                            if (user.getId()==comment.getCommentator()){
                                commentDTOArrayList.add(new CommentDTO(comment.getId(), comment.getParent_id(), comment.getType(),comment.getCommentator(), comment.getGmt_create(), comment.getGmt_modified(), null,comment.getContent(),user));

                            }
                        }
                    }
                }
            }
        }



        return commentDTOArrayList;
    }

}

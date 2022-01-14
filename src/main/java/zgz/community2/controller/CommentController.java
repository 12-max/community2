package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.ResultDTO;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;


    // 此方法含带参数 所以我们在浏览器执行 /test/comment 是执行的  一个
    // @GetMappeer（“comment）
    // puvlic xxx xxx（）{
    // }
//    这样的方法，所有一直重定向
    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO post(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");


        if (user==null){
            return new ResultDTO(201,"用户未登录请先登录");
        }
        Integer id = userMapper.userName(user.getUsername()).getId();
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setCommentator(id);
        comment.setLike_count(1L);
        commentMapper.inset(comment);


        return new ResultDTO(200,"操作成功");
    }


}

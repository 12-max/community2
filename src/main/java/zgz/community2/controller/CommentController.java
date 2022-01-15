package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.ResultDTO;
import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.model.User;
import zgz.community2.service.CommentService;

import javax.servlet.http.HttpServletRequest;
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO post(@RequestBody CommentDTO commentDTO
                          /*HttpServletRequest request*/){
        //User user = (User) request.getSession().getAttribute("user");
      /*  if (user==null){
            return ResultDTO.error(CustomizeErrorCode.NO_LOGIN);
        }*/
        //Integer id = userMapper.userName(user.getUsername()).getId();
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setCommentator(1L);
        comment.setLike_count(1L);
        commentService.inset(comment);


        return ResultDTO.ok();
    }


}

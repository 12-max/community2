package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zgz.community2.dto.CommentCreateDTO;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.ResultDTO;
import zgz.community2.enums.CommentTypeEnum;
import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.model.User;
import zgz.community2.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO post(@RequestBody CommentCreateDTO commentDTO,
                         HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUsername()==null){
            return ResultDTO.error(CustomizeErrorCode.NO_LOGIN);
        }
        Long id = userMapper.userName(user.getUsername()).getId();
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setCommentator(id);
        commentService.inset(comment);


        return ResultDTO.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id,
                              Model model){
        ArrayList<CommentDTO> commentDTOList =  commentService.Comment();
        ArrayList<CommentDTO> commentDTOArrayList = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOList) {
            if (commentDTO.getParentId()==id) {
                commentDTOArrayList.add(commentDTO);

            }
        }

        return ResultDTO.ok();
    }


}

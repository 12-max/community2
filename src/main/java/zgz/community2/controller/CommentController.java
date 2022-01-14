package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.ResultDTO;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.model.Comment;

import java.util.HashMap;

@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;



    // 此方法含带参数 所以我们在浏览器执行 /test/comment 是执行的  一个
    // @GetMappeer（“comment）
    // puvlic xxx xxx（）{
    // }
//    这样的方法，所有一直重定向
    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLike_count(1L);
        commentMapper.inset(comment);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","成功");
        return new ResultDTO(0,"操作失败",null);
    }


}

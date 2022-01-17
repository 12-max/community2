package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zgz.community2.dto.CommentCreateDTO;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.model.User;
import zgz.community2.service.CommentService;
import zgz.community2.service.QuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public CommentService commentService;

    @GetMapping("/questions/{id}")
    public String question(@PathVariable("id") Long  id,
                            Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        model.addAttribute("question",questionDTO);

        //阅读数
        questionService.inView(id);

        //评论功能
        List<CommentDTO> commentDTOList = commentService.Comment(id);
        int size = commentDTOList.size();
        //回复数
        questionDTO.setComment_count(size);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);

        //相关问题
        ArrayList<QuestionDTO> related = questionService.related(id, questionDTO.getTag());
        model.addAttribute("relatedList",related);

        return "question";
    }
}

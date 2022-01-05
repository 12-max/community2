package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.UserMapper;
import zgz.community2.service.QuestionService;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String Index(Model model){

        List<QuestionDTO> questions = questionService.listQuestion();

        model.addAttribute("questions",questions);
        return "index";
    }

}

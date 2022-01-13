package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions/{id}")
    public String question(@PathVariable("id") Integer  id,
                            Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        //阅读数
        questionService.inView(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}

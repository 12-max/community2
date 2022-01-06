package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.dto.PaginationDTO;
import zgz.community2.mapper.UserMapper;
import zgz.community2.service.QuestionService;

import javax.servlet.http.HttpServlet;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String Index(Model model,
                        @RequestParam(name = "page",defaultValue = "1",required = false) Integer page,
                        @RequestParam(name = "size",defaultValue = "5",required = false) Integer size){



        PaginationDTO questions = questionService.listQuestion(page,size);

        model.addAttribute("questions",questions);
        return "index";
    }

}

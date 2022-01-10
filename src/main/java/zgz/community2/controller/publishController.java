package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Question;
import zgz.community2.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/toPublish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title, String description, String tag,
                            HttpServletRequest request){

        String user = (String) request.getSession().getAttribute("user");


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmt_modified(System.currentTimeMillis());
        question.setGmt_create(System.currentTimeMillis());

        questionMapper.addQuestion(question);
        return "redirect:/";
    }


}
